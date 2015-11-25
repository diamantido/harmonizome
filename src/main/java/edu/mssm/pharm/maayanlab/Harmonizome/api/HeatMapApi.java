package edu.mssm.pharm.maayanlab.Harmonizome.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.HeatMapDao;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.ProteinMetadataSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.*;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/" + Constant.HEAT_MAPS_API_URL + "/*"})
public class HeatMapApi extends HttpServlet {

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
            String queryString = "?preview=true&row_label=" + rowLabel + "&col_label=" + colLabel;
            schema.put("clustergrammerLink", dv.getClustergrammerLink() + queryString);
        }
    }

    private void addImage(Map<String, String> schema, DatasetVisualizationAbstract dv, String type) {
        if (dv.getImage() != null) {
            String imageLink = Constant.HEAT_MAP_IMAGES + type + "/" + dv.getImage();
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
}