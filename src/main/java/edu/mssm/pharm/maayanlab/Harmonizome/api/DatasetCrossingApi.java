package edu.mssm.pharm.maayanlab.Harmonizome.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetCrossingDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;


import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.EntityListSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.DatasetCrossingListSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.DatasetCrossingSerializer;


import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetCrossing;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;

import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;

import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.server.ServerCloneException;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/" + Constant.DATASET_CROSSING_API_URL + "/*"})
public class DatasetCrossingApi extends HttpServlet {

    private static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new BioEntityLinkSerializer());
        gsonBuilder.registerTypeAdapter(Gene.class, new BioEntityLinkSerializer());
        gsonBuilder.registerTypeAdapter(GeneSet.class, new BioEntityLinkSerializer());
        gsonBuilder.registerTypeAdapter(DatasetCrossing.class, new DatasetCrossingSerializer());
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] query = UrlUtil.getPathAsArray(request);
        if (query.length == 2) {
            doGetDatasetCrossing(response, query);
        } else if (query.length == 4) {
            doGetOverlappingGenes(response, query);
        } else {
            doErrorSchema(response);
        }
    }

    private void doGetDatasetCrossing(HttpServletResponse response, String[] query) throws ServletException, IOException {
        try {
            HibernateUtil.beginTransaction();
            List <DatasetCrossing> dc = DatasetCrossingDao.getDatasetCrossing(query[0], query[1]);
            if (dc == null) {
                doErrorSchema(response);
                return;
            }
            Dataset dataset1 = GenericDao.get(Dataset.class, query[0]);
            Dataset dataset2 = GenericDao.get(Dataset.class, query[1]);
            DatasetCrossingListSchema schema = new DatasetCrossingListSchema(dataset1, dataset2, dc);
            String json = gson.toJson(schema);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
            HibernateUtil.commitTransaction();
        } catch (HibernateException he) {
            he.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
    }

    private void doGetOverlappingGenes(HttpServletResponse response, String[] query) throws ServletException, IOException {
        try {
            HibernateUtil.beginTransaction();
            List <Gene> overlappingGenes = DatasetCrossingDao.getOverlap(query[0], query[1], query[2], query[3]);
            if (overlappingGenes == null) {
                doErrorSchema(response);
                return;
            }
            String json = gson.toJson(overlappingGenes);
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

    private void doErrorSchema(HttpServletResponse response) throws ServletException, IOException {
        ErrorSchema schema = new ErrorSchema();
        String json = gson.toJson(schema);
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
    }
}