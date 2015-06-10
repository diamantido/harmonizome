package edu.mssm.pharm.maayanlab.Harmonizome.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetType;

public class DatasetTypeDeserializer implements JsonDeserializer<DatasetType>  {

	public DatasetType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		JsonObject jsonObject = json.getAsJsonObject();
		DatasetType datasetType = new DatasetType();
		String name = jsonObject.get("name").getAsString();
		datasetType.setName(name);
		return datasetType;
	}
}