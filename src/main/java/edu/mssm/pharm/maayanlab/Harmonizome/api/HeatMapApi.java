package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.HeatMapDao;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ClustergrammerColumnSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ClustergrammerPayloadSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ClustergrammerRowSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.ProteinMetadataSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetPairVisualization;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetVisualization;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetVisualizationAbstract;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = {"/" + Constant.HEAT_MAPS_API_URL + "/*"})
public class HeatMapApi extends HttpServlet {

	private static final long serialVersionUID = -7885353436714737700L;

	private static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Protein.class, new ProteinMetadataSerializer());
        gsonBuilder.registerTypeAdapter(Gene.class, new BioEntityLinkSerializer());
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] query = UrlUtil.getPathAsArray(request);
        if (query.length == 3) {
            doGetDatasetPairsVisualization(response, query);
        } else if (query.length == 2) {
            doGetIndividualDatasetVisualization(request, response);
        } else {
            doErrorSchema(response);
        }
    }

    private void doGetDatasetPairsVisualization(HttpServletResponse response, String[] query) throws ServletException, IOException {
        try {
            HibernateUtil.beginTransaction();
            Map<String, String> schema = new HashMap<String, String>();
            DatasetPairVisualization dvp = HeatMapDao.getDatasetPairVisualization(query[1], query[2]);
            if (dvp == null) {
                doErrorSchema(response);
                return;
            }
            String attributeType1 = formatAttributeType(dvp.getDataset1());
            String attributeType2 = formatAttributeType(dvp.getDataset2());
            schema.put("attributeType1", attributeType1);
            schema.put("attributeType2", attributeType2);
            addClustergrammerLink(schema, dvp, attributeType1, attributeType2);
            addImage(schema, dvp, query[0]);
            String json = gson.toJson(schema);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
            HibernateUtil.commitTransaction();
        } catch (HibernateException he) {
            he.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.close();
        }
    }

    private void doGetIndividualDatasetVisualization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] query = UrlUtil.getPathAsArray(request);
        String type = query[0];
        String dataset = query[1];
        try {
            HibernateUtil.beginTransaction();
            Map<String, String> schema = new HashMap<String, String>();
	        DatasetVisualization dv = HeatMapDao.getDatasetVisualization(dataset, type);
            if (dv == null) {
                doErrorSchema(response);
                return;
            }

            schema.put("type", type);
            if (type.equals("dataset")) {
                String attribute = formatAttributeType(dv.getDataset());
                addClustergrammerLink(schema, dv, "Genes", attribute);
            } else if (type.equals("gene_similarity")) {
                addClustergrammerLink(schema, dv, "Genes", "Genes");
            } else if (type.equals("attribute_similarity")) {
                String attribute = formatAttributeType(dv.getDataset());
                addClustergrammerLink(schema, dv, attribute, attribute);
            }
            addLabels(schema, dv, type);
            addImage(schema, dv, type);
	        String json = gson.toJson(schema);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
            HibernateUtil.commitTransaction();
        } catch (HibernateException he) {
            he.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.close();
        }
    }

    private void addLabels(Map<String, String> schema, DatasetVisualization dv, String type) {
        if (type.equals("gene_similarity")) {
            schema.put("colLabel", "Genes");
            schema.put("rowLabel", "Genes");
        } else if (type.equals("attribute_similarity")) {
            String attributeType = formatAttributeType(dv.getDataset());
            schema.put("colLabel", attributeType);
            schema.put("rowLabel", attributeType);
        } else if (type.equals("dataset")) {
            String attributeType = formatAttributeType(dv.getDataset());
            schema.put("colLabel", "Genes");
            schema.put("rowLabel", attributeType);
        }
    }

    private void addClustergrammerLink(Map<String, String> schema, DatasetVisualizationAbstract dv, String rowLabel, String colLabel) {
        if (dv.getClustergrammerLink() != null) {
            String queryString = "?row_label=" + rowLabel + "&col_label=" + colLabel;
            schema.put("clustergrammerLink", dv.getClustergrammerLink() + queryString);
        }
    }

    private void addImage(Map<String, String> schema, DatasetVisualizationAbstract dv, String type) {
        if (dv.getImage() != null) {
            String imageLink = Constant.HEAT_MAP_IMAGES_DIR() + type + "/" + dv.getImage();
            schema.put("imageLink", imageLink);
        }
    }

    private String formatAttributeType(Dataset dataset) {
        return StringUtils.capitalize(dataset.getAttributeType().getPluralizedName());
    }

    private void doErrorSchema(HttpServletResponse response) throws ServletException, IOException {
        ErrorSchema schema = new ErrorSchema();
        String json = gson.toJson(schema);
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dataset = request.getParameter("dataset");
		String[] inputGenes = request.getParameterValues("genes[]");

		try {
            HibernateUtil.beginTransaction();
            List<Object[]> overlappingGenesWithAssociations = GeneDao.getWithAssociationsByDatasetAndInputGenes(dataset, inputGenes);

            Set<String> assocsAdded = new HashSet<String>();
            List<ClustergrammerColumnSchema> columns = new ArrayList<ClustergrammerColumnSchema>();
            for (Object[] tuple : overlappingGenesWithAssociations) {
            	String assoc = (String) tuple[0];
            	List<ClustergrammerRowSchema> data = null;
            	if (assocsAdded.contains(assoc)) {
            		for (ClustergrammerColumnSchema c : columns) {
            			if (c.getCol_name().equals(assoc)) {
            				data = c.getData();
            				break;
            			}
            		}
            	} else {
            		assocsAdded.add(assoc);
            		ClustergrammerColumnSchema c = new ClustergrammerColumnSchema(assoc);
            		columns.add(c);
            		data = c.getData();
            	}

        		String geneSymbol = (String) tuple[1];
        		// First check for standardized (i.e. continuous) value.
				Float geneValue = (Float) tuple[2];
				if (geneValue == null) {
					// If no standardized value, use threshold value.
					int unboxed = (int) tuple[3];
					geneValue = new Float(unboxed);
				}
				ClustergrammerRowSchema row = new ClustergrammerRowSchema(geneSymbol, geneValue);
				data.add(row);
            }

            ClustergrammerPayloadSchema schema = new ClustergrammerPayloadSchema(columns);

            JsonElement results = sendHttpRequestToClustergrammer(schema);
            PrintWriter out = response.getWriter();
            String json = gson.toJson(results);
			out.write(json);
			out.flush();

	        HibernateUtil.commitTransaction();
        } catch (HibernateException he) {
            he.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.close();
        }
    }

	private JsonElement sendHttpRequestToClustergrammer(ClustergrammerPayloadSchema schema) throws ClientProtocolException, IOException {
		String url = Constant.CLUSTERGRAMMER_URL() + "/vector_upload/";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost request = new HttpPost(url);
		StringEntity entity = new StringEntity(gson.toJson(schema));
		request.setEntity(entity);
		HttpResponse response = httpClient.execute(request);
		InputStream ips = response.getEntity().getContent();

		BufferedReader buf = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String s;
		while (true) {
			s = buf.readLine();
			if (s == null || s.length() == 0) {
				break;
			}
			sb.append(s);
		}
		buf.close();
		ips.close();

		JsonObject jsonObject = new JsonParser().parse(sb.toString()).getAsJsonObject();
		return jsonObject;
	}
}
