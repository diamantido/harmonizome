package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;

public class GeneSynonymSerializer implements JsonSerializer<GeneSynonym> {

	public JsonElement serialize(final GeneSynonym synonym, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("symbol", new JsonPrimitive(synonym.getSymbol()));
		return result;
	}
}