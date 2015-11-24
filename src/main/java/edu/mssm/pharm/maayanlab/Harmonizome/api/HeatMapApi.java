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

@WebServlet(urlPatterns = {"/" + Constant.API_URL + "/" + Constant.HEAT_MAPS_URL + "/*"})
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
        if (query.length == 2) {
            doGetDatasetPairsVisualization(response, query);
        } else if (query.length == 1) {
            doGetIndividualDatasetVisualization(request, response);
        } else {
            doErrorSchema(response);
        }
    }

    private void doGetDatasetPairsVisualization(HttpServletResponse response, String[] query) throws ServletException, IOException {
        try {
            HibernateUtil.beginTransaction();
            Map<String, String> schema = new HashMap<String, String>();
            DatasetPairVisualization dvp = HeatMapDao.getDatasetPairVisualization(query[0], query[1]);
            if (dvp == null) {
                doErrorSchema(response);
                return;
            }
            String attributeType1 = formatAttributeType(dvp.getDataset1());
            String attributeType2 = formatAttributeType(dvp.getDataset2());
            schema.put("attributeType1", attributeType1);
            schema.put("attributeType2", attributeType2);
            addClustergrammerLink(schema, dvp, attributeType1, attributeType2);
            addImage(schema, dvp);
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
        String query = UrlUtil.getPath(request);
        String type = UrlUtil.getParameter(request, "type");
        try {
            HibernateUtil.beginTransaction();
            Map<String, String> schema = new HashMap<String, String>();
	        DatasetVisualization dv = HeatMapDao.getDatasetVisualization(query, type);
            if (dv == null) {
                doErrorSchema(response);
                return;
            }
            schema.put("type", type);

            if (type.equals("gene-attribute")) {
                String attribute = formatAttributeType(dv.getDataset());
                addClustergrammerLink(schema, dv, "Genes", attribute);
            } else if (type.equals("gene-gene")) {
                addClustergrammerLink(schema, dv, "Genes", "Genes");
            } else if (type.equals("attribute-attribute")) {
                String attribute = formatAttributeType(dv.getDataset());
                addClustergrammerLink(schema, dv, attribute, attribute);
            }

            addImage(schema, dv);
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

    private void addClustergrammerLink(Map<String, String> schema, DatasetVisualizationAbstract dv, String rowLabel, String colLabel) {
        if (dv.getClustergrammerLink() != null) {
            String queryString = "?preview=true&row_label=" + rowLabel + "&col_label=" + colLabel;
            schema.put("clustergrammerLink", dv.getClustergrammerLink() + queryString);
        }
    }

    private void addImage(Map<String, String> schema, DatasetVisualizationAbstract dv) {
        if (dv.getImage() != null) {
            System.out.println(dv.getImage());
            String staticServerUrl = "http://amp.pharm.mssm.edu/static/harmonizome/heat_maps/dataset_pairs/";
            String imageLink = staticServerUrl + dv.getImage();
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