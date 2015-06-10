package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;

public class DatasetGroupDeserializer implements JsonDeserializer<DatasetGroup>  {

	public DatasetGroup deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		JsonObject jsonObject = json.getAsJsonObject();
		DatasetGroup datasetGroup = new DatasetGroup();
		String name = jsonObject.get("name").getAsString();
		datasetGroup.setName(name);
		return datasetGroup;
	}
}