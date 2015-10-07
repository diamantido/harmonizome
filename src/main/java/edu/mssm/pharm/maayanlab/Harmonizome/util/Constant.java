package edu.mssm.pharm.maayanlab.Harmonizome.util;

public class Constant {
	
	public static final String HARMONIZOME = "Harmonizome";
	
	public static final String API_URL = "api/1.0";
	
	public static final String DATA_DIRECTORY = "http://amp.pharm.mssm.edu/static/hdfs/harmonizome/data";
	
	// This has no preceding slash because we typically use it as a relative URL in JSP templates.
	public static final String SEARCH_URL = "search";
	
	public static final String SUGGEST_URL = "suggest";
	
	public static final String getSEARCH_URL() {
		return SEARCH_URL;
	}
	
	public static final String ADMIN_URL = "admin";
	
	public static final String API_CURSOR = "cursor";
	
	public static final String TEMPLATE_DIR = "/template/";
	
	public static final String REST_LOCATION_PROP = "href";
	
	public static final String REST_METHOD_PROP = "method";
	
	public static final String ENTITY_TYPE = "entity";
	
	//public static final String REST_METHOD_GET = "GET";
	
	public static final int API_MAX_RESULTS = 100;
	
	public static final String MACHINE_LEARNING_URL = "learn";
	
	public static final String META_KEYWORDS = "" +
		"harmonizome, " +
		"lincs, bd2k, dcic, bd2k-lincs dcic, data coordination and integration center, " +
		"biomedical, systems biology, drug discovery, gene signature, " +
		"genomics, transcriptomics, proteomics, metabolomics, " +
		"genes, proteins, cell lines, tissues, perturbations, diseases, phenotypes, drugs, " +
		"maayan lab, avi ma'ayan, avi maayan";
}