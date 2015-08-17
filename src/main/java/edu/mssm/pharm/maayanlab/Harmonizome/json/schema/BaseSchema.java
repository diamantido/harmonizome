package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.net.HttpStatusCode;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class BaseSchema {
	
	public int code = HttpStatusCode.OK.getValue();
	
	public String message = HttpStatusCode.OK.getMessage();
	
	public double version = 1;
	
	public List<Map<String, String>> entities = new ArrayList<Map<String, String>>();
	
	public BaseSchema() {
		Map<String, String> datasetData = new TreeMap<String, String>();
		String datasetHref = "/" + Constant.API_URL + "/" + Dataset.ENDPOINT;
		datasetData.put(Constant.ENTITY_TYPE, "dataset");
		datasetData.put(Constant.REST_LOCATION_PROP, datasetHref);
		entities.add(datasetData);
	
		Map<String, String> geneData = new TreeMap<String, String>();
		String geneHref = "/" + Constant.API_URL + "/" + Gene.ENDPOINT;
		geneData.put(Constant.ENTITY_TYPE, "gene");
		geneData.put(Constant.REST_LOCATION_PROP, geneHref);
		entities.add(geneData);
		
		Map<String, String> attributeData = new TreeMap<String, String>();
		String attributeHref = "/" + Constant.API_URL + "/" + Attribute.ENDPOINT;
		attributeData.put(Constant.ENTITY_TYPE, "attribute");
		attributeData.put(Constant.REST_LOCATION_PROP, attributeHref);
		entities.add(attributeData);
		
		Map<String, String> geneSetData = new TreeMap<String, String>();
		String geneSetHref = "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT;
		geneSetData.put(Constant.ENTITY_TYPE, "gene set");
		geneSetData.put(Constant.REST_LOCATION_PROP, geneSetHref);
		entities.add(geneSetData);
		
		Map<String, String> proteinData = new TreeMap<String, String>();
		String proteinHref = "/" + Constant.API_URL + "/" + Protein.ENDPOINT;
		proteinData.put(Constant.ENTITY_TYPE, "protein");
		proteinData.put(Constant.REST_LOCATION_PROP, proteinHref);
		entities.add(proteinData);
	}
}