package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class ResourceLinkSerializer implements JsonSerializer<Resource> {

	public JsonElement serialize(final Resource resource, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(resource.getName()));
		String href = "/" + Constant.API_URL + "/" + Resource.ENDPOINT + "/" + resource.getUrlEncodedName();
		result.add(Constant.REST_LOCATION_PROP, new JsonPrimitive(href));
		return result;
	}
}