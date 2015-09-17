package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.NamingAuthority;

public class AttributeMetadataSerializer implements JsonSerializer<Attribute> {

	public JsonElement serialize(final Attribute attribute, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(attribute.getNameFromNamingAuthority()));
		
		SerDesUtil.add(result, "nameFromNamingAuthority", attribute.getNameFromNamingAuthority());
		SerDesUtil.add(result, "idFromDataset", attribute.getNameFromNamingAuthority());
		SerDesUtil.add(result, "idFromNamingAuthority", attribute.getIdFromNamingAuthority());
//		SerDesUtil.add(result, "descriptionFromDataset", attribute.getDescriptionFromDataset());
		SerDesUtil.add(result, "descriptionFromNamingAuthority", attribute.getDescriptionFromNamingAuthority());
//		SerDesUtil.add(result, "urlFromDataset", attribute.getUrlFromDataset());
//		SerDesUtil.add(result, "urlFromNamingAuthority", attribute.getUrlFromNamingAuthority());
		SerDesUtil.add(result, "attributeType", attribute.getAttributeType().getName());

		NamingAuthority namingAuthority = attribute.getNamingAuthority();
		if (namingAuthority != null) {
			result.add("namingAuthority", context.serialize(namingAuthority));
		}

		return result;
	}
}