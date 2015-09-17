package edu.mssm.pharm.maayanlab.Harmonizome.json.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.NamingAuthority;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class BaseSchema {

	public double version = 1;
	
	public List<Map<String, String>> entities = new ArrayList<Map<String, String>>();
	
	public BaseSchema() {
		
		Map<String, String> attributeData = new TreeMap<String, String>();
		String attributeHref = "/" + Constant.API_URL + "/" + Attribute.ENDPOINT;
		attributeData.put(Constant.ENTITY_TYPE, "attribute");
		attributeData.put(Constant.REST_LOCATION_PROP, attributeHref);
		entities.add(attributeData);
		
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
		
		Map<String, String> geneSetData = new TreeMap<String, String>();
		String geneSetHref = "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT;
		geneSetData.put(Constant.ENTITY_TYPE, "gene set");
		geneSetData.put(Constant.REST_LOCATION_PROP, geneSetHref);
		entities.add(geneSetData);
		
		Map<String, String> hgncRootFamilyData = new TreeMap<String, String>();
		String hgncRootFamilyHref = "/" + Constant.API_URL + "/" + HgncRootFamily.ENDPOINT;
		hgncRootFamilyData.put(Constant.ENTITY_TYPE, "hgnc root family");
		hgncRootFamilyData.put(Constant.REST_LOCATION_PROP, hgncRootFamilyHref);
		entities.add(hgncRootFamilyData);
		
		Map<String, String> namingAuthorityData = new TreeMap<String, String>();
		String namingAuthorityHref = "/" + Constant.API_URL + "/" + NamingAuthority.ENDPOINT;
		namingAuthorityData.put(Constant.ENTITY_TYPE, "naming authority");
		namingAuthorityData.put(Constant.REST_LOCATION_PROP, namingAuthorityHref);
		entities.add(namingAuthorityData);
		
		Map<String, String> proteinData = new TreeMap<String, String>();
		String proteinHref = "/" + Constant.API_URL + "/" + Protein.ENDPOINT;
		proteinData.put(Constant.ENTITY_TYPE, "protein");
		proteinData.put(Constant.REST_LOCATION_PROP, proteinHref);
		entities.add(proteinData);
		
		Map<String, String> resourceData = new TreeMap<String, String>();
		String resourceHref = "/" + Constant.API_URL + "/" + Resource.ENDPOINT;
		resourceData.put(Constant.ENTITY_TYPE, "resource");
		resourceData.put(Constant.REST_LOCATION_PROP, resourceHref);
		entities.add(resourceData);
	}
}