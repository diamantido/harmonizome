package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;

public class AssociationMetadataSerializer implements JsonSerializer<Association> {

	public JsonElement serialize(final Association association, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("thresholdValue", new JsonPrimitive(association.getThresholdValue()));
		if (association.getStandardizedValue() != null) {
			result.add("standardizedValue", new JsonPrimitive(association.getStandardizedValue()));
		}
		return result;
	}
}