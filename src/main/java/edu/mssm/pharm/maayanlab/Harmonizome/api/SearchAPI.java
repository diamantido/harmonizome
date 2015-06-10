package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetType;
import edu.mssm.pharm.maayanlab.Harmonizome.pojo.JsonSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.serializer.DatasetGroupSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serializer.DatasetSerializer;
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
		gsonBuilder.registerTypeAdapter(DatasetGroup.class, new DatasetGroupSerializer());
		//gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeSerializer());
		//gsonBuilder.registerTypeAdapter(Feature.class, new FeatureSerializer());
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

		// Datasets
		List<Dataset> datasets = new ArrayList<Dataset>();
		List<DatasetGroup> datasetGroups = new ArrayList<DatasetGroup>();
		List<DatasetType> datasetTypes = new ArrayList<DatasetType>();
		
		// Attributes
		//List<Attribute> attributeResults = new ArrayList<Attribute>();
		//List<AttributeGroup> attributeGroupResults = new ArrayList<AttributeGroup>();
		//List<AttributeType> attributeTypeResults = new ArrayList<AttributeType>();

		// Genes
		//List<Gene> geneResults = new ArrayList<Gene>();
		//List<IdgFamily> idgFamilyResults = new ArrayList<IdgFamily>();

		JsonSchema results = new JsonSchema();
		PrintWriter out = response.getWriter();
		String json = "";
		try {
			HibernateUtil.beginTransaction();
			
			datasets = DAO.filterDataset(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, idgFamily);
			datasetGroups = DAO.filterDatasetGroup(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, idgFamily);
			datasetTypes = DAO.filterDatasetType(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, idgFamily);
			
			//attributeResults = DAO.getAttributeByFilter(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, idgFamily);
			//attributeGroupResults = DAO.getAttributeGroupByFilter(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, idgFamily);
			
			results.setDatasets(datasets);
			results.setDatasetGroups(datasetGroups);
			results.setDatasetTypes(datasetTypes);
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