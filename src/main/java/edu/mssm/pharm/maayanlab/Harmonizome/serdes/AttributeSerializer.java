package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class AttributeSerializer implements JsonSerializer<Attribute> {

	public JsonElement serialize(final Attribute attribute, final Type type, final JsonSerializationContext context) {
		
		JsonObject result = new JsonObject();

		String nameFromDataset = attribute.getNameFromDataset();
		result.add("name", new JsonPrimitive(nameFromDataset));
		Dataset dataset = attribute.getDataset();
		String href = "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT + "/" + attribute.getUrlEncodedNameFromDataset() + "/" + dataset.getUrlEncodedName();
		result.add("dataset", context.serialize(dataset));
		result.add(Constant.REST_LOCATION_PROP, new JsonPrimitive(href));
				
		/*String nameFromNamingAuthority = attribute.getNameFromNamingAuthority();
		if (nameFromNamingAuthority != null) {
			result.add("name_from_naming_authority", new JsonPrimitive(nameFromNamingAuthority));
		}
		
		String idFromDataset = attribute.getIdFromDataset();
		if (idFromDataset != null) {
			result.add("id_from_dataset", new JsonPrimitive(idFromDataset));
		}
		
		String idFromNamingAuthority = attribute.getIdFromNamingAuthority();
		if (idFromNamingAuthority != null) {
			result.add("id_from_naming_authority", new JsonPrimitive(idFromNamingAuthority));
		}
		
		String descriptionFromDataset = attribute.getDescriptionFromDataset();
		if (descriptionFromDataset != null) {
			result.add("description_from_dataset", new JsonPrimitive(descriptionFromDataset));
		}
		
		String descriptionFromNamingAuthority = attribute.getDescriptionFromNamingAuthority();
		if (descriptionFromNamingAuthority != null) {
			result.add("description_from_naming_authority", new JsonPrimitive(descriptionFromNamingAuthority));
		}
		
		String urlFromDataset = attribute.getUrlFromDataset();
		if (urlFromDataset != null) {
			result.add("url_from_dataset", new JsonPrimitive(urlFromDataset));
		}
		
		String urlFromNamingAuthority = attribute.getUrlFromNamingAuthority();
		if (urlFromNamingAuthority != null) {
			result.add("url_from_naming_authority", new JsonPrimitive(urlFromNamingAuthority));
		}

		String attributeType = attribute.getAttributeType().getName();
		if (attributeType != null) {
			result.add("attribute_type", new JsonPrimitive(attributeType));
		}

		NamingAuthority namingAuthority = attribute.getNamingAuthority();
		if (namingAuthority != null) {
			String namingAuthorityName = namingAuthority.getName();
			result.add("naming_authority", new JsonPrimitive(namingAuthorityName));
		}*/
		
		return result;
	}
}