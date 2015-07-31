package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class AttributeSimpleSerializer implements JsonSerializer<Attribute> {

	public JsonElement serialize(final Attribute attribute, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(attribute.getNameFromDataset()));
		String href = "/" + Constant.API_URL + "/" + Attribute.ENDPOINT + "/" + attribute.getUrlEncodedNameFromDataset();
		result.add(Constant.REST_LOCATION_PROP, new JsonPrimitive(href));
		result.add(Constant.REST_METHOD_PROP, new JsonPrimitive(Constant.REST_METHOD_GET));
		return result;
	}
}