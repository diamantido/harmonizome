package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;

public class DatasetSimpleSerializer implements JsonSerializer<Dataset> {

	public JsonElement serialize(final Dataset dataset, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		// TODO: Change this to just a list of names; we aren't doing it now because it will break all the unit tests.
		result.add("name", new JsonPrimitive(dataset.getName()));
		return result;
	}
}