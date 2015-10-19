package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.model.NamingAuthority;

public class AttributeMetadataSerializer implements JsonSerializer<Attribute> {

	public JsonElement serialize(final Attribute attribute, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(attribute.getNameFromNamingAuthority()));
		
		SerDesUtil.add(result, "nameFromNamingAuthority", attribute.getNameFromNamingAuthority());
		SerDesUtil.add(result, "nameFromDataset", attribute.getNameFromNamingAuthority());
		SerDesUtil.add(result, "idFromNamingAuthority", attribute.getIdFromNamingAuthority());
		SerDesUtil.add(result, "descriptionFromNamingAuthority", attribute.getDescriptionFromNamingAuthority());
		SerDesUtil.add(result, "urlFromNamingAuthority", attribute.getUrlFromNamingAuthority());
		
		JsonArray geneSets = new JsonArray();
		for (GeneSet geneSet : attribute.getGeneSets()) {
			geneSets.add(context.serialize(geneSet));
		}
		SerDesUtil.add(result, "geneSets", geneSets);
		
		NamingAuthority namingAuthority = attribute.getNamingAuthority();
		if (namingAuthority != null) {
			result.add("namingAuthority", context.serialize(namingAuthority));
		}

		return result;
	}
}