package edu.mssm.pharm.maayanlab.Harmonizome.util;

public class Constant {
	
	public static final String HARMONIZOME = "Harmonizome";
	
	public static final String API_URL = "api/1.0";
	
	// Undocumented API for administrative tasks.
	public static final String API_DARK = "api/dark";

	public static final String DATA_DIRECTORY = "http://amp.pharm.mssm.edu/static/hdfs/harmonizome/data";

	public static final String HEAT_MAP_IMAGES = "http://amp.pharm.mssm.edu/static/harmonizome/heat_maps/";
	
	// This has no preceding slash because we typically use it as a relative URL in JSP templates.
	public static final String SEARCH_URL = "search";
	
	public static final String SUGGEST_URL = "suggest";

    public static final String VISUALIZE = "visualize";

	public static final String HEAT_MAPS_URL = VISUALIZE + "/heat_map";

    public static final String HEAT_MAPS_API_URL = API_URL + "/" + VISUALIZE + "/heat_map";
	
	public static final String getSEARCH_URL() {
		return SEARCH_URL;
	}
	
	public static final String ADMIN_URL = "admin";
	
	public static final String DOWNLOAD_COUNT_URL = API_DARK + "/download_count";
	
	public static final String API_CURSOR = "cursor";
	
	public static final String TEMPLATE_DIR = "/template/";
	
	public static final String REST_LOCATION_PROP = "href";

	public static final String ENTITY_TYPE = "entity";

	public static final int API_MAX_RESULTS = 100;

    public static final int DB_MAX_RESULTS = 1000;
}