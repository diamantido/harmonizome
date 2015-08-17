package edu.mssm.pharm.maayanlab.Harmonizome.api.gene_set;

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
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.FeatureSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneSetSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.AttributeInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.DatasetInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.GeneInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Feature;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.HttpStatusCode;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT + "/*" })
public class GeneSetEntityAPI extends HttpServlet {

	private static final long serialVersionUID = -3627764278583624301L;

	private static Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetInfoSerializer());
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeInfoSerializer());
		gsonBuilder.registerTypeAdapter(Feature.class, new FeatureSerializer());
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneInfoSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] query = URLUtil.getPathAsArray(request, true);
		PrintWriter out = response.getWriter();
		if (query == null) {
			response.sendRedirect("/" + Constant.HARMONIZOME + "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT);
		} else if (query.length != 2) {
			ErrorSchema errorSchema = new ErrorSchema(HttpStatusCode.NOT_FOUND);
			out.write(gson.toJson(errorSchema));
			out.flush();
		} else {
			String attributeName = query[0];
			String datasetName = query[1];

			GeneSet geneSet = new GeneSet();
			Attribute attribute = null;
			Dataset dataset = null;
			List<Feature> features = null;

			try {
				HibernateUtil.beginTransaction();
				attribute = AttributeDAO.getByNameAndDataset(attributeName, datasetName);
				dataset = DatasetDAO.getFromName(datasetName);
				features = FeatureDAO.getByGeneSet(attributeName, datasetName);
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
			
			if (attribute == null && dataset == null) {
				ErrorSchema errorSchema = new ErrorSchema(HttpStatusCode.NOT_FOUND);
				out.write(gson.toJson(errorSchema));
				out.flush();
			} else {
				geneSet.setAttribute(attribute);
				geneSet.setDataset(dataset);
				geneSet.setFeatures(features);
				out.write(gson.toJson(geneSet, GeneSet.class));
				out.flush();
			}
		}
	}
}
