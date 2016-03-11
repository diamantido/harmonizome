package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.GeneWithAssociationsSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;

public class GeneWithAssociationsSerializer implements JsonSerializer<GeneWithAssociationsSchema> {

	public JsonElement serialize(final GeneWithAssociationsSchema geneWAssoc, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		
		Gene gene = geneWAssoc.getGene();
		result.add("symbol", new JsonPrimitive(gene.getSymbol()));
		
		JsonArray synonyms = new JsonArray();
		for (GeneSynonym syn : gene.getSynonyms()) {
			synonyms.add(new JsonPrimitive(syn.getSymbol()));
		}
		SerDesUtil.add(result, "synonyms", synonyms);
		
		SerDesUtil.add(result, "name", gene.getName());
		SerDesUtil.add(result, "description", gene.getDescription());
		SerDesUtil.add(result, "ncbiEntrezGeneId", gene.getNcbiEntrezGeneId());
		SerDesUtil.add(result, "ncbiEntrezGeneUrl", gene.getNcbiEntrezGeneUrl());
		
		JsonArray proteins = new JsonArray();
		for (Protein protein : gene.getProteins()) {
			proteins.add(context.serialize(protein, Protein.class));
		}
		SerDesUtil.add(result, "proteins", proteins);

		JsonArray hgncRootFamilies = new JsonArray();
		for (HgncRootFamily family : gene.getHgncRootFamilies()) {
			hgncRootFamilies.add(context.serialize(family, HgncRootFamily.class));
		}
		SerDesUtil.add(result, "hgncRootFamilies", hgncRootFamilies);
		
		JsonArray associations = new JsonArray();
		for (Association assoc : geneWAssoc.getAssociations()) {
			associations.add(context.serialize(assoc, Association.class));
		}
		SerDesUtil.add(result, "associations", associations);
		
		return result;
	}
}