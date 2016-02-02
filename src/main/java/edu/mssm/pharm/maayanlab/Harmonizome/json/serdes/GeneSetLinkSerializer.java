package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class GeneSetLinkSerializer implements JsonSerializer<GeneSet> {

	public JsonElement serialize(final GeneSet geneSet, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add(geneSet.getKey(), new JsonPrimitive(geneSet.getValue()));
		String href = "/" + Constant.API_URL + "/" + geneSet.getEndpoint() + "/" + geneSet.getUrlEncodedValue();
		result.add(Constant.REST_LOCATION_PROP, new JsonPrimitive(href));
		for (Association f : geneSet.getAssociations()) {
			System.out.println(f.getStandardizedValue());
		}
		return result;
	}
}