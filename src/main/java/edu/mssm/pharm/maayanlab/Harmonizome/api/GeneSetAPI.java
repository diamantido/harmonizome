package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.FeatureDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.GeneSetSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Feature;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.AttributeSimpleSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetSimpleSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.FeatureSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/gene_set/*" })
public class GeneSetAPI extends HttpServlet {

	private static final long serialVersionUID = -3627764278583624301L;

	private static Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeSimpleSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetSimpleSerializer());
		gsonBuilder.registerTypeAdapter(Feature.class, new FeatureSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] query = URLUtil.getPathAsArray(request, true);
		if (query == null || query.length != 2) {
			doGetAll(request, response);
		} else {
			String attributeName = query[0];
			String datasetName = query[1];
			doGetBySymbol(request, response, attributeName, datasetName);
		}
	}

	public void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeSimpleSerializer());
		gson = gsonBuilder.create();
		
		String cursor = request.getParameter("cursor");
		
		Map<String, Object> geneSetSchema = new HashMap<String, Object>();
		List<Attribute> attributes = null;
		int next = 0;

		PrintWriter out = response.getWriter();
		try {
			HibernateUtil.beginTransaction();
			if (cursor == null) {
				next = Constant.API_MAX_RESULTS;
				attributes = AttributeDAO.getByCursor(0, next);
			} else {
				Integer c = Integer.parseInt(cursor);
				next = c + Constant.API_MAX_RESULTS;
				attributes = AttributeDAO.getByCursor(c, next);
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		
		String nextString = "/" + Constant.API_URL + "/" + Attribute.ENDPOINT + "?cursor=" + next;
		geneSetSchema.put("next", nextString);
		geneSetSchema.put("gene_sets", attributes);
		out.write(gson.toJson(geneSetSchema));
		out.flush();
	}
	
	public void doGetBySymbol(HttpServletRequest request, HttpServletResponse response, String attributeName, String datasetName) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeSimpleSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetSimpleSerializer());
		gsonBuilder.registerTypeAdapter(Feature.class, new FeatureSerializer());
		gson = gsonBuilder.create();
		
		GeneSetSchema gss = new GeneSetSchema();
		Attribute attribute = null;
		Dataset dataset = null;
		List<Feature> features = null;
		try {
			HibernateUtil.beginTransaction();
			attribute = AttributeDAO.getByNameAndDataset(attributeName, datasetName);
			dataset = DatasetDAO.getByName(datasetName);
			features = FeatureDAO.getByGeneSet(attributeName, datasetName);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
		
		gss.setAttribute(attribute);
		gss.setDataset(dataset);
		gss.setFeatures(features);
		out.write(gson.toJson(gss, GeneSetSchema.class));
		out.flush();
	}
}
