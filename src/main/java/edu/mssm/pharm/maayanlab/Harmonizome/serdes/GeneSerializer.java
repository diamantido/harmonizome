package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class GeneSerializer implements JsonSerializer<Gene> {

	public JsonElement serialize(final Gene gene, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("symbol", new JsonPrimitive(gene.getSymbol()));

		// Synonyms
		JsonArray synonyms = new JsonArray();
		for (GeneSynonym syn : gene.getSynonyms()) {
			synonyms.add(new JsonPrimitive(syn.getSymbol()));
		}
		result.add("synonyms", synonyms);

		result.add("name", new JsonPrimitive(gene.getName()));
		result.add("description", new JsonPrimitive(gene.getDescription()));
		if (gene.getNcbiEntrezGeneId() != null) {
			result.add("ncbiEntrezGeneId", new JsonPrimitive(gene.getNcbiEntrezGeneId()));
		}
		if (gene.getNcbiEntrezGeneUrl() != "") {
			result.add("ncbiEntrezGeneUrl", new JsonPrimitive(gene.getNcbiEntrezGeneUrl()));
		}
		String href = Constant.API_URL + gene.getEndpoint() + "/" + gene.getSymbol();
		result.add(Constant.REST_LOCATION_PROP, new JsonPrimitive(href));

		return result;
	}
}