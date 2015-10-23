package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import com.google.gson.*;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;

import java.lang.reflect.Type;

public class ResourceStatsSerializer implements JsonSerializer<Resource> {

	public JsonElement serialize(final Resource resource, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(resource.getName()));
		SerDesUtil.add(result, "acronym", resource.getAcronym());
		SerDesUtil.add(result, "description", resource.getLongDescription());
		SerDesUtil.add(result, "url", resource.getUrl());
		SerDesUtil.add(result, "numGeneSets", resource.getNumGeneSets());
		SerDesUtil.add(result, "numDatasets", resource.getNumDatasets());
		SerDesUtil.add(result, "link", Resource.ENDPOINT + "/" + resource.getUrlEncodedValue());
		SerDesUtil.add(result, "image", resource.getImage());
		return result;
	}
}