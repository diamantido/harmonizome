package edu.mssm.pharm.maayanlab.Harmonizome.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;

public class DatasetSerializer implements JsonSerializer<Dataset> {

	public JsonElement serialize(final Dataset dataset, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(dataset.getName()));
		result.add("association", new JsonPrimitive(dataset.getAssociation()));
		result.add("description", new JsonPrimitive(dataset.getDescription()));
		result.add("datasetGroup", new JsonPrimitive(dataset.getDatasetGroup().getName()));
		result.add("datasetType", new JsonPrimitive(dataset.getDatasetType().getName()));
		result.add("attributeGroup", new JsonPrimitive(dataset.getAttributeGroup().getName()));
		result.add("attributeType", new JsonPrimitive(dataset.getAttributeType().getName()));
		return result;
	}
}