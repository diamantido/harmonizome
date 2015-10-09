package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;

public class ResourceStatsSerializer implements JsonSerializer<Resource> {

	public JsonElement serialize(final Resource resource, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(resource.getName()));
		SerDesUtil.add(result, "acronym", resource.getAcronym());
		SerDesUtil.add(result, "description", resource.getLongDescription());
		SerDesUtil.add(result, "url", resource.getUrl());
		SerDesUtil.add(result, "numGeneSets", resource.getNumGeneSets());
		SerDesUtil.add(result, "link", Resource.ENDPOINT + "/" + resource.getUrlEncodedValue());
		return result;
	}
}