package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.NamingAuthority;

public class NamingAuthorityMetadataSerializer implements JsonSerializer<NamingAuthority> {

	public JsonElement serialize(final NamingAuthority namingAuthority, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		SerDesUtil.add(result, "name", namingAuthority.getName());
		SerDesUtil.add(result, "acronym", namingAuthority.getAcronym());
		SerDesUtil.add(result, "description", namingAuthority.getDescription());
		SerDesUtil.add(result, "url", namingAuthority.getUrl());
		SerDesUtil.add(result, "publication", namingAuthority.getPublication().getUrl());
		return result;
	}
}