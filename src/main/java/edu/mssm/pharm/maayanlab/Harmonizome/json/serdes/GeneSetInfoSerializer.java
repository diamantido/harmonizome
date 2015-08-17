package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

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

public class GeneSetInfoSerializer implements JsonSerializer<GeneSet> {

	public JsonElement serialize(final GeneSet geneset, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		Attribute attribute = geneset.getAttribute();
		Dataset dataset = geneset.getDataset();
		
		// TODO: This is a hack because there are NULL foreign keys in the database.
		// There shouldn't be.
		if (dataset != null) {
			String nameInUrl = attribute.getUrlEncodedNameFromDataset() + "/" + dataset.getUrlEncodedName();
			String href = "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT + "/" + nameInUrl;
			result.add("attribute", new JsonPrimitive(attribute.getNameFromDataset()));
			result.add("dataset", new JsonPrimitive(dataset.getName()));
			result.add(Constant.REST_LOCATION_PROP, new JsonPrimitive(href));
		}
		return result;
	}
}