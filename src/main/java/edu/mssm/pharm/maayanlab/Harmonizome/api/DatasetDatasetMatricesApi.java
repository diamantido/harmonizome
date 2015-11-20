package edu.mssm.pharm.maayanlab.Harmonizome.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDatasetMatricesDao;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.ProteinMetadataSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/" + Constant.API_URL + "/dataset_dataset/*"})
public class DatasetDatasetMatricesApi extends HttpServlet {

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
        	try {
    			HibernateUtil.beginTransaction();
                Map<String, String> schema = new HashMap<String, String>();
                String image = DatasetDatasetMatricesDao.get(query[0], query[1]);
                schema.put("image", image);
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
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}