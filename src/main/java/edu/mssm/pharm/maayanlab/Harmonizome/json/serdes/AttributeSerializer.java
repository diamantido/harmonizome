package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class AttributeSerializer implements JsonSerializer<Attribute> {

	public JsonElement serialize(final Attribute attribute, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		String nameFromDataset = attribute.getNameFromDataset();
		result.add("name", new JsonPrimitive(nameFromDataset));
		Dataset dataset = attribute.getDataset();
		String href = "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT + "/" + attribute.getUrlEncodedValue() + "/" + dataset.getUrlEncodedValue();
		result.add("dataset", context.serialize(dataset));
		result.add(Constant.REST_LOCATION_PROP, new JsonPrimitive(href));
		return result;
	}
}