package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class SerDesUtil {
	
	public static void add(JsonObject result, String key, String value) {
		if (value == null) {
			value = "";
		}
		result.add(key, new JsonPrimitive(value));
	}
	
	public static void add(JsonObject result, String key, Integer value) {
		if (value == null) {
			value = 0;
		}
		result.add(key, new JsonPrimitive(value));
	}
	
	public static void add(JsonObject result, String key, Long value) {
		if (value == null) {
			value = new Long(0);
		}
		result.add(key, new JsonPrimitive(value));
	}

	public static void add(JsonObject result, String key, JsonArray array) {
		result.add(key, array);
	}
}