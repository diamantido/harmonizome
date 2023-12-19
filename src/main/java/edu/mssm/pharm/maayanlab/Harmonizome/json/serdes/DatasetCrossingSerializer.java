package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetCrossing;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class DatasetCrossingSerializer implements JsonSerializer<DatasetCrossing> {

	public JsonElement serialize(final DatasetCrossing datasetCrossing, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("geneSet1", context.serialize(datasetCrossing.getGeneSet1(), GeneSet.class));
		result.add("geneSet1Dir", new JsonPrimitive(datasetCrossing.getGeneSet1Dir()));
		result.add("geneSet1Size", new JsonPrimitive(datasetCrossing.getGeneSet1().getAssociations().stream().filter(association -> association.getThresholdValue() == datasetCrossing.getGeneSet1Dir()).count()));
        result.add("geneSet2", context.serialize(datasetCrossing.getGeneSet2(), GeneSet.class));
		result.add("geneSet2Dir", new JsonPrimitive(datasetCrossing.getGeneSet2Dir()));
		result.add("geneSet2Size", new JsonPrimitive(datasetCrossing.getGeneSet2().getAssociations().stream().filter(association -> association.getThresholdValue() == datasetCrossing.getGeneSet2Dir()).count()));
		result.add("pValue", new JsonPrimitive(datasetCrossing.getPValue()));
        result.add("jaccardIndex", new JsonPrimitive(datasetCrossing.getJaccardIndex()));
		result.add("overlappingGenes", new JsonPrimitive(datasetCrossing.getOverlappingGenes()));
		return result;
	}
}