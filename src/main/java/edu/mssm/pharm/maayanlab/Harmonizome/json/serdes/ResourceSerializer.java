package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;

public class ResourceSerializer implements JsonSerializer<Resource> {

	public JsonElement serialize(final Resource resource, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(resource.getName()));
		String datasetGroup = null;
		for (Dataset d : resource.getDatasets()) {
			datasetGroup = d.getDatasetGroup().getName();
		}
		result.add("group", new JsonPrimitive(datasetGroup));

		int size = 1;
		for (Dataset ds : resource.getDatasets()) {
			size += ds.getAttributes().size();
		}

		result.add("size", new JsonPrimitive(size));
		return result;
	}
}