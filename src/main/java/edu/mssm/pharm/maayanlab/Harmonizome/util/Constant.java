package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class Constant {

	public static final Properties props = new Properties();

	public static final String getProperty(String key, String _default) {
		String value = System.getenv(key);
		if (value == null || value.isEmpty()) {
			value = System.getProperty(key);
		}
		if (value == null || value.isEmpty()) {
			value = Constant.props.getProperty(key);
		}
		if (value == null || value.isEmpty()) {
			value = _default;
		}
		//System.out.println("Constant." + key + " = " + value);
		return value;
	}

	static {
    try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream resource = loader.getResourceAsStream("constant.properties");
			if (resource != null) {
				System.out.println(resource);
				props.load(resource);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final String ORIGIN() {
		return Constant.getProperty("ORIGIN", "https://amp.pharm.mssm.edu");
	}

	public static final String HARMONIZOME_PREFIX() {
		return Constant.getProperty("HARMONIZOME_PREFIX", "Harmonizome");
	}

	public static final String HARMONIZOME = "Harmonizome";


/* URLs
 *---------------------------------------------------------------------------*/

	public static final String API_URL = "api/1.0";

	// Undocumented API for administrative tasks.
	public static final String API_DARK_URL = "api/dark";

	public static final String API_CURSOR = "cursor";

	public static final String DOWNLOAD_COUNT_URL = API_DARK_URL + "/download_count";

	public static final String DOWNLOAD_ASSOCIATIONS_URL = API_URL + "/download/associations";

  public static final String VISUALIZE_URL = "visualize";

	public static final String HEAT_MAPS_URL = VISUALIZE_URL + "/heat_map";

  public static final String HEAT_MAPS_API_URL = API_URL + "/" + VISUALIZE_URL + "/heat_map";

	public static final String ADMIN_URL = "admin";

	public static final String ML_URL = "machine_learning";

	// This has no preceding slash because we typically use it as a relative URL in JSP templates.
	public static final String SEARCH_URL = "search";

	public static final String SUGGEST_URL = "suggest";

	static final String getSEARCH_URL() {
		return SEARCH_URL;
	}

/* Remotes
 *---------------------------------------------------------------------------*/

	public static final String HARMONIZOME_ML_URL() {
		return Constant.getProperty("HARMONIZOME_ML_URL", Constant.ORIGIN() + "/Harmonizome-ML");
	}

	public static final String CLUSTERGRAMMER_URL() {
		return Constant.getProperty("CLUSTERGRAMMER_URL", Constant.ORIGIN() + "/clustergrammer");
	}

	public static final String ENRICHR_URL() {
		return Constant.getProperty("ENRICHR_URL", Constant.ORIGIN() + "/Enrichr");
	}

	public static final String ARCHS4_URL() {
		return Constant.getProperty("ARCHS4_URL", Constant.ORIGIN() + "/archs4");
	}

	public static final String HARMONOGRAM_URL() {
		return Constant.getProperty("HARMONOGRAM_URL", Constant.ORIGIN() + "/harmonogram");
	}

	public static final String STATIC_URL() {
		return  Constant.getProperty("STATIC_URL", Constant.ORIGIN() + "/static");
	}

/* Directories
 *---------------------------------------------------------------------------*/

	public static final String DATA_DIR() {
		return Constant.getProperty("DATA_DIR", Constant.STATIC_URL() + "/hdfs/harmonizome/data");
	}

	public static final String ML_DATA_DIR() {
		return Constant.getProperty("ML_DATA_DIR", Constant.STATIC_URL() + "/hdfs/harmonizome/data/machine_learning");
	}

	public static final String HEAT_MAP_IMAGES_DIR() {
		return Constant.getProperty("HEAT_MAP_IMAGES_DIR", Constant.STATIC_URL() + "/harmonizome/heat_maps");
	}

	public static final String TEMPLATE_DIR = "/template/";


/* Miscellaneous
 *---------------------------------------------------------------------------*/

	public static final String REST_LOCATION_PROP = "href";

	public static final String ENTITY_TYPE = "entity";

	public static final int API_MAX_RESULTS = 100;

  public static final int DB_MAX_RESULTS = 1000;

  public static final int ML_PREDICTIONS_MAX_RESULTS = 10000;
}
