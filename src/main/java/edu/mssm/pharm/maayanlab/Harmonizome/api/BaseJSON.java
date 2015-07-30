package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class BaseJSON {

	public double version = 1.0;
	
	public List<Map<String, String>> links = new ArrayList<Map<String, String>>();
	
	public BaseJSON() {
		Map<String, String> geneData = new HashMap<String, String>();
		geneData.put(Constant.REST_LOCATION_PROP, new Gene().getEndpoint());
		geneData.put(Constant.REST_METHOD_PROP, Constant.REST_METHOD_GET);
		links.add(geneData);
	}
}
