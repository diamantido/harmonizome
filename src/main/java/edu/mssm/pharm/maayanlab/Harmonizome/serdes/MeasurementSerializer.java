package edu.mssm.pharm.maayanlab.Harmonizome.serdes;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Measurement;

public class MeasurementSerializer implements JsonSerializer<Measurement> {

	public JsonElement serialize(final Measurement measurement, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("name", new JsonPrimitive(measurement.getName()));
		return result;
	}
}