package edu.mssm.pharm.maayanlab.Harmonizome.json.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;

public class HgncRootFamilyMetadataSerializer implements JsonSerializer<HgncRootFamily> {

	public JsonElement serialize(final HgncRootFamily hgncRootFamily, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		SerDesUtil.add(result, "name", hgncRootFamily.getName());
		SerDesUtil.add(result, "familyId", hgncRootFamily.getFamilyId());
		SerDesUtil.add(result, "url", hgncRootFamily.getUrl());
		
		JsonArray genes = new JsonArray();
		for (Gene gene : hgncRootFamily.getGenes()) {
			genes.add(context.serialize(gene));
		}
		SerDesUtil.add(result, "genes", genes);
		
		return result;
	}
}