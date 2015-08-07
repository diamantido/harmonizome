package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Feature;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.AttributeInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.FeatureSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSetInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSetSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT + "/*" })
public class GeneSetAPI extends HttpServlet {

	private static final long serialVersionUID = -3627764278583624301L;

	private static Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
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
			doGetByAttributeAndDataset(request, response, attributeName, datasetName);
		}
	}

	public void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cursor = request.getParameter(Constant.API_CURSOR);
		int startAt = cursor == null ? 0 : Integer.parseInt(cursor);
		
		Map<String, Object> geneSetSchema = new HashMap<String, Object>();
		List<GeneSet> geneSets = new ArrayList<GeneSet>();
		List<Attribute> attributes = null;
		String query = request.getParameter("q");
		try {
			HibernateUtil.beginTransaction();
			attributes = AttributeDAO.getAll(query, startAt);	
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		
		if (attributes != null) {
			for (Attribute attribute : attributes) {
				GeneSet gs = new GeneSet(attribute, attribute.getDataset());
				geneSets.add(gs);
			}
			int next = startAt + Constant.API_MAX_RESULTS;
			String nextString = "/" + Constant.API_URL + "/" + Attribute.ENDPOINT + "?" + Constant.API_CURSOR + "=" + next;
			geneSetSchema.put("next", nextString);
		}
		geneSetSchema.put("gene_sets", geneSets);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeInfoSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetInfoSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetInfoSerializer());
		gson = gsonBuilder.create();
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(geneSetSchema));
		out.flush();
	}
	
	public void doGetByAttributeAndDataset(HttpServletRequest request, HttpServletResponse response, String attributeName, String datasetName) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetInfoSerializer());
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeInfoSerializer());
		gsonBuilder.registerTypeAdapter(Feature.class, new FeatureSerializer());
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneInfoSerializer());
		gson = gsonBuilder.create();
		
		GeneSet geneSet = new GeneSet();
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
		
		geneSet.setAttribute(attribute);
		geneSet.setDataset(dataset);
		geneSet.setFeatures(features);
		out.write(gson.toJson(geneSet, GeneSet.class));
		out.write("");
		out.flush();
	}
}
