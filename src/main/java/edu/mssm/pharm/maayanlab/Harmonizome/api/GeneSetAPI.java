package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

	private static final Gson gson;

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
		GeneSetSchema gss = new GeneSetSchema();
		if (query.length == 2) {
			String datasetNameFromDataset = query[0];
			String datasetName = query[1];
			Attribute attribute = null;
			Dataset dataset = null;
			List<Feature> features = null;
			try {
				HibernateUtil.beginTransaction();
				attribute = AttributeDAO.getByNameAndDataset(datasetNameFromDataset, datasetName);
				features = FeatureDAO.getByAttribute(datasetNameFromDataset, datasetName);
				dataset = DatasetDAO.getByName(datasetName);

				gss.setAttribute(attribute);
				gss.setDataset(dataset);
				gss.setFeatures(features);
				
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				he.printStackTrace();
				HibernateUtil.rollbackTransaction();
			}
		}
		
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(gss, GeneSetSchema.class));
		out.flush();
	}
}