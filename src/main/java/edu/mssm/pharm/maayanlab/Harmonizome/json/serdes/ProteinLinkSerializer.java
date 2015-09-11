package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class ProteinLinkSerializer implements JsonSerializer<Protein> {

	public JsonElement serialize(final Protein protein, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("symbol", new JsonPrimitive(protein.getSymbol()));
		String href = "/" + Constant.API_URL + "/" + Protein.ENDPOINT + "/" + protein.getUrlEncodedSymbol();
		result.add(Constant.REST_LOCATION_PROP, new JsonPrimitive(href));
		return result;
	}
}