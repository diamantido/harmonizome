package edu.mssm.pharm.maayanlab.Harmonizome.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class BaseSchema {

	public double version = 1.0;
	
	public List<Map<String, String>> links = new ArrayList<Map<String, String>>();
	
	public BaseSchema() {
		Map<String, String> datasetData = new HashMap<String, String>();
		String datasetHref = "/" + Constant.API_URL + "/" + Dataset.ENDPOINT;
		datasetData.put(Constant.REST_LOCATION_PROP, datasetHref);
		datasetData.put(Constant.REST_METHOD_PROP, Constant.REST_METHOD_GET);
		links.add(datasetData);
	
		Map<String, String> geneData = new HashMap<String, String>();
		String geneHref = "/" + Constant.API_URL + "/" + Gene.ENDPOINT;
		geneData.put(Constant.REST_LOCATION_PROP, geneHref);
		geneData.put(Constant.REST_METHOD_PROP, Constant.REST_METHOD_GET);
		links.add(geneData);
		
		Map<String, String> attributeData = new HashMap<String, String>();
		String attributeHref = "/" + Constant.API_URL + "/" + Attribute.ENDPOINT;
		attributeData.put(Constant.REST_LOCATION_PROP, attributeHref);
		attributeData.put(Constant.REST_METHOD_PROP, Constant.REST_METHOD_GET);
		links.add(attributeData);
	}
}
