package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Measurement;

public class MeasurementDeserializer implements JsonDeserializer<Measurement>  {

	public Measurement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		JsonObject jsonObject = json.getAsJsonObject();
		Measurement measurement = new Measurement();
		String name = jsonObject.get("name").getAsString();
		measurement.setName(name);
		return measurement;
	}
}