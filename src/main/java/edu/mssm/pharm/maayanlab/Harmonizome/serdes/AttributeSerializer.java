package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;

public class AttributeSerializer implements JsonSerializer<Attribute> {

	public JsonElement serialize(final Attribute attribute, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(attribute.getName()));
		
		String description = attribute.getDescription();
		if (description != "") {
			result.add("description", new JsonPrimitive(description));
		}
		
		String url = attribute.getUrl();
		if (url != "") {
			result.add("url", new JsonPrimitive(url));
		}

		String idFromNamingAuthority = attribute.getIdFromNamingAuthority();
		if (idFromNamingAuthority != null) {
			result.add("idFromNamingAuthority", new JsonPrimitive(idFromNamingAuthority));
		}
		
		return result;
	}
}