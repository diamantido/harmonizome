package edu.mssm.pharm.maayanlab.Harmonizome.util;

public class Constant {
	
	public static final String API_URL = "api/1.0";
	
	// This has no preceding slash because we typically use it as a relative URL in JSP templates.
	public static final String SEARCH_URL = "search";
	
	public static final String SUGGEST_URL = "suggest";
	
	public static final String getSEARCH_URL() {
		return SEARCH_URL;
	}
	
	public static final String API_CURSOR = "start";
	
	public static final String TEMPLATE_DIR = "/template/";
	
	public static final String REST_LOCATION_PROP = "href";
	
	public static final String REST_METHOD_PROP = "method";
	
	public static final String ENTITY_TYPE = "entity";
	
	//public static final String REST_METHOD_GET = "GET";
	
	public static final int API_MAX_RESULTS = 100;
}