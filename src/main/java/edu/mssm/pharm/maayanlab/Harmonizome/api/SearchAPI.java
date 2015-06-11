package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Download;
import edu.mssm.pharm.maayanlab.Harmonizome.pojo.JsonSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.AttributeGroupSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.AttributeTypeSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetGroupSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetTypeSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DownloadSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.API_BASE_URL + "/q/*" })
public class SearchAPI extends HttpServlet {

	private static final long serialVersionUID = 778955897675398125L;

	private static final Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetSerializer());
		gsonBuilder.registerTypeAdapter(Download.class, new DownloadSerializer());
		gsonBuilder.registerTypeAdapter(DatasetGroup.class, new DatasetGroupSerializer());
		gsonBuilder.registerTypeAdapter(DatasetType.class, new DatasetTypeSerializer());
		// gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeSerializer());
		gsonBuilder.registerTypeAdapter(AttributeGroup.class, new AttributeGroupSerializer());
		gsonBuilder.registerTypeAdapter(AttributeType.class, new AttributeTypeSerializer());
		// gsonBuilder.registerTypeAdapter(Feature.class, new FeatureSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String dataset = URLUtil.get(request, "dataset");
		String datasetGroup = URLUtil.get(request, "datasetGroup");
		String datasetType = URLUtil.get(request, "datasetType");
		String attribute = URLUtil.get(request, "attribute");
		String attributeGroup = URLUtil.get(request, "attributeGroup");
		String attributeType = URLUtil.get(request, "attributeType");
		String gene = URLUtil.get(request, "gene");
		String idgFamily = URLUtil.get(request, "idgFamily");

		JsonSchema results = new JsonSchema();
		PrintWriter out = response.getWriter();
		String json = "";
		try {
			HibernateUtil.beginTransaction();

			results.setDataset(DAO.filterDataset(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, idgFamily));
			results.setDatasetGroup(DAO.filterDatasetGroup(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, idgFamily));
			results.setDatasetType(DAO.filterDatasetType(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, idgFamily));

			results.setAttributeGroup(DAO.filterAttributeGroup(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, idgFamily));
			results.setAttributeType(DAO.filterAttributeTypes(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, idgFamily));

			json = gson.toJson(results);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		out.write(json);
		out.flush();
	}
}