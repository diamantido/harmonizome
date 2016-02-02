package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;

public class GeneSetMetadataSerializer implements JsonSerializer<GeneSet> {

	public JsonElement serialize(final GeneSet geneSet, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		Attribute attribute = geneSet.getAttribute();
		Dataset dataset = geneSet.getDataset();
		result.add("attribute", context.serialize(attribute));
		result.add("dataset", context.serialize(dataset));

		JsonArray jsonArray = new JsonArray();
		for (Association association : geneSet.getAssociations()) {
			jsonArray.add(context.serialize(association, Association.class));
		}
		result.add("associations", jsonArray);		

		return result;
	}
}