package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Publication;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;

public class ResourceMetadataSerializer implements JsonSerializer<Resource> {

	public JsonElement serialize(final Resource resource, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(resource.getName()));
		SerDesUtil.add(result, "acronym", resource.getAcronym());
		
		JsonArray datasets = new JsonArray();
		JsonArray pubMedIds = new JsonArray();
		for (Dataset dataset : resource.getDatasets()) {
			datasets.add(context.serialize(dataset));
			for (Publication publication : dataset.getPublications()) {
				JsonPrimitive el = new JsonPrimitive(publication.getPmid());
				if (!pubMedIds.contains(el)) {
					pubMedIds.add(el);
				}
			}
		}
		SerDesUtil.add(result, "datasets", datasets);
		SerDesUtil.add(result, "pubMedIds", pubMedIds);
		
		SerDesUtil.add(result, "description", resource.getLongDescription());
		SerDesUtil.add(result, "url", resource.getUrl());
		return result;
	}
}