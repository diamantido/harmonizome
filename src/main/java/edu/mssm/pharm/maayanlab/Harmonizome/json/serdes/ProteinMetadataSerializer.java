package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;

public class ProteinMetadataSerializer implements JsonSerializer<Protein> {

	public JsonElement serialize(final Protein protein, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		
		SerDesUtil.addIfNotNull(result, "symbol", protein.getSymbol());
		SerDesUtil.addIfNotNull(result, "uniprotId", protein.getUniprotId());
		SerDesUtil.addIfNotNull(result, "name", protein.getName());
		SerDesUtil.addIfNotNull(result, "description", protein.getDescription());
		SerDesUtil.addIfNotNull(result, "uniprotUrl", protein.getUniprotUrl());
		
		if (protein.getGene() != null) {
			JsonElement gene = context.serialize(protein.getGene(), Gene.class);
			result.add("gene", gene);
		}
		
		return result;
	}

}