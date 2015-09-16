package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;

public class DatasetStatsSerializer implements JsonSerializer<Dataset> {

	public JsonElement serialize(final Dataset dataset, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(dataset.getName()));
		//result.add("size", new JsonPrimitive(dataset.getAttributes().size()));
		return result;
	}
}