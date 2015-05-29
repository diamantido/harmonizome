package edu.mssm.pharm.maayanlab.Harmonizome.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Synonym;

public class GeneSerializer implements JsonSerializer<Gene> {

	public JsonElement serialize(final Gene gene, final Type type, final JsonSerializationContext context) {
		System.out.println("serializer called");
		JsonObject result = new JsonObject();
		result.add("symbol", new JsonPrimitive(gene.getSymbol()));

		// Synonyms
		JsonArray synonyms = new JsonArray();
		for (Synonym syn : gene.getSynonyms()) {
			synonyms.add(new JsonPrimitive(syn.getSymbol()));
		}
		result.add("synonyms", synonyms);

		result.add("name", new JsonPrimitive(gene.getName()));
		result.add("description", new JsonPrimitive(gene.getDescription()));
		result.add("ncbiEntrezGeneId", new JsonPrimitive(gene.getNcbiEntrezGeneId()));
		result.add("ncbiEntrezGeneUrl", new JsonPrimitive(gene.getNcbiEntrezGeneUrl()));

		// Proteins
		JsonArray proteins = new JsonArray();
		for (Protein prot : gene.getProtein()) {
			proteins.add(new JsonPrimitive(prot.getSymbol()));
		}
		result.add("protein", proteins);

		return result;
	}
}