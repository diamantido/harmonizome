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

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.IdgFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.serializer.AttributeSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serializer.DatasetSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Query;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.API_BASE_URL + "/query/*" })
public class QueryAPI extends HttpServlet {

	private static final long serialVersionUID = 778955897675398125L;

	private static final Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetSerializer());
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeSerializer());
		//gsonBuilder.registerTypeAdapter(Feature.class, new FeatureSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String dataset = Query.get(request, "dataset");
		String datasetGroup = Query.get(request, "datasetGroup");
		String datasetType = Query.get(request, "datasetType");
		String attribute = Query.get(request, "attribute");
		String attributeGroup = Query.get(request, "attributeGroup");
		String attributeType = Query.get(request, "attributeType");
		String gene = Query.get(request, "gene");
		String geneFamily = Query.get(request, "geneFamily");

		// Datasets
		List<Dataset> datasetResults = new ArrayList<Dataset>();
		List<DatasetGroup> datasetGroupResults = new ArrayList<DatasetGroup>();
		List<DatasetType> datasetTypeResults = new ArrayList<DatasetType>();
		
		// Attributes
		List<Attribute> attributeResults = new ArrayList<Attribute>();
		List<AttributeGroup> attributeGroupResults = new ArrayList<AttributeGroup>();
		List<AttributeType> attributeTypeResults = new ArrayList<AttributeType>();

		// Genes
		List<Gene> geneResults = new ArrayList<Gene>();
		List<IdgFamily> idgFamilyResults = new ArrayList<IdgFamily>();		
		
		Map<String, Object> results = new HashMap<String, Object>();
		PrintWriter out = response.getWriter();
		String json = "";
		try {
			HibernateUtil.beginTransaction();
			
			datasetResults = DAO.filterDataset(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, geneFamily);
			datasetGroupResults = DAO.filterDatasetGroup(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, geneFamily);
			datasetTypeResults = DAO.filterDatasetType(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, geneFamily);
			
			//attributeResults = DAO.getAttributeByFilter(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, geneFamily);
			//attributeGroupResults = DAO.getAttributeGroupByFilter(dataset, datasetGroup, datasetType, attribute, attributeGroup, attributeType, gene, geneFamily);
			
			results.put("dataset", datasetResults);
			results.put("datasetGroup", datasetGroupResults);
			results.put("datasetType", datasetTypeResults);
			
			results.put("attribute", attributeResults);
			results.put("attributeGroup", attributeGroupResults);
			results.put("attributeType", attributeTypeResults);
			
			results.put("gene", geneResults);
			results.put("idgFamily", idgFamilyResults);
			
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