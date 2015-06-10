package edu.mssm.pharm.maayanlab.Harmonizome.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetType;
import edu.mssm.pharm.maayanlab.Harmonizome.pojo.JsonSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.serializer.DatasetDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serializer.DatasetGroupDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serializer.DatasetTypeDeserializer;

public class TestSearchAPI extends Mockito {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private StringWriter output;
	private PrintWriter writer;
	private Gson gson;

	@Before
	public void setup() throws IOException {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		output = new StringWriter();
		writer = new PrintWriter(output);
		when(response.getWriter()).thenReturn(writer);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetDeserializer());
		gsonBuilder.registerTypeAdapter(DatasetGroup.class, new DatasetGroupDeserializer());
		gsonBuilder.registerTypeAdapter(DatasetType.class, new DatasetTypeDeserializer());
		gson = gsonBuilder.create();
	}

	@Test
	public void testResponseFields() throws IOException, ServletException {
		new SearchAPI().doGet(request, response);
		writer.flush();
		assertTrue(output.toString().contains("dataset"));
	}

	@Test
	public void testDatasetAll() throws IOException, ServletException {
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		System.out.println(json);
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<Dataset> datasets = jsonSchema.getDatasets();
		assertEquals(datasets.size(), 119);
		// TODO: Add checks for other classes.
	}
}
