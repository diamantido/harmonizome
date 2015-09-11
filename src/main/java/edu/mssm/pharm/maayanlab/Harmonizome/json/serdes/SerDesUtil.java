package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class SerDesUtil {
	
	public static void add(JsonObject result, String key, String value) {
		if (value != null) {
			result.add(key, new JsonPrimitive(value));
		}
	}
	
	public static void add(JsonObject result, String key, Integer value) {
		if (value != null) {
			result.add(key, new JsonPrimitive(value));
		}
	}

	public static void add(JsonObject result, String key, JsonArray array) {
		if (array.size() > 0) {
			result.add(key, array);
		}
	}
}