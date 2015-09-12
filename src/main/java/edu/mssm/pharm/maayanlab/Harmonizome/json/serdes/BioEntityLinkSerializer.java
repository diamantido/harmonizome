package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.BioEntity;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class BioEntityLinkSerializer implements JsonSerializer<BioEntity> {

	public JsonElement serialize(final BioEntity entity, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add(entity.getKey(), new JsonPrimitive(entity.getValue()));
		String href = "/" + Constant.API_URL + "/" + entity.getEndpoint() + "/" + entity.getUrlEncodedValue();
		result.add(Constant.REST_LOCATION_PROP, new JsonPrimitive(href));
		return result;
	}
}