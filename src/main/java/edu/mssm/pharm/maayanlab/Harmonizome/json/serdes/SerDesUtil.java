package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class SerDesUtil {
	
	public static void addIfNotNull(JsonObject result, String key, String value) {
		if (value != null) {
			result.add(key, new JsonPrimitive(value));
		}
	}
}