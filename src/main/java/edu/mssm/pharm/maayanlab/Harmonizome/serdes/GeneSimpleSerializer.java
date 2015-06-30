package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;

public class GeneSimpleSerializer implements JsonSerializer<Gene> {

	public JsonElement serialize(final Gene gene, final Type type, final JsonSerializationContext context) {
		return new JsonPrimitive(gene.getSymbol());
	}
}