package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Download;

public class DownloadSerializer implements JsonSerializer<Download> {

	public JsonElement serialize(final Download download, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(download.getName()));
		result.add("directory", new JsonPrimitive(download.getDirectory()));
		result.add("count", new JsonPrimitive(download.getCount()));
		return result;
	}
}