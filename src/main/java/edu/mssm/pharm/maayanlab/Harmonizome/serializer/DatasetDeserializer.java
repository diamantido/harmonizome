package edu.mssm.pharm.maayanlab.Harmonizome.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;

public class DatasetDeserializer implements JsonDeserializer<Dataset>  {

	public Dataset deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		JsonObject jsonObject = json.getAsJsonObject();
		Dataset dataset = new Dataset();
		String name = jsonObject.get("name").getAsString();
		dataset.setName(name);
		return dataset;
	}
}