package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;

public class GeneSimpleDeserializer implements JsonDeserializer<Gene>  {

	public Gene deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		Gene gene = new Gene();
		gene.setName(json.getAsString());
		return gene;
	}
}