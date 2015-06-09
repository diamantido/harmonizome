package edu.mssm.pharm.maayanlab.Harmonizome.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.pojo.JsonSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.serializer.DatasetDeserializer;

public class TestAPI extends Mockito {

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
		gson = gsonBuilder.create();
	}
	
	@Test
	public void testResponseFields() throws IOException, ServletException {
		new QueryAPI().doGet(request, response);
		writer.flush();
		assertTrue(output.toString().contains("dataset"));
	}

	@Test
	public void testDataset() throws IOException, ServletException {
		new QueryAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		System.out.println(json);
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<Dataset> datasets = jsonSchema.getDatasets();
		assertEquals(datasets.size(), 119);
	}

	@Test
	public void testDatasetNameParameter() throws IOException, ServletException {
		when(request.getParameter("dataset")).thenReturn("Achilles_Cell_Line_Gene_Essentiality_Profiles");
		new QueryAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<Dataset> datasets = jsonSchema.getDatasets();
		assertEquals(datasets.get(0).getName(), "Achilles Cell Line Gene Essentiality Profiles");
	}
	
	@Test
	public void testDatasetCombinedParameters() throws IOException, ServletException {
		when(request.getParameter("datasetType")).thenReturn("omics");
		when(request.getParameter("attributeType")).thenReturn("cell");
		new QueryAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<Dataset> datasets = jsonSchema.getDatasets();
		assertEquals(datasets.size(), 12);
		ArrayList<String> validDatasetNames = new ArrayList<String>();
		validDatasetNames.add("Achilles Cell Line Gene Essentiality Profiles");
		validDatasetNames.add("BioGPS Cell Line Gene Expression Profiles");
		validDatasetNames.add("CCLE Cell Line Gene CNV Profiles");
		validDatasetNames.add("CCLE Cell Line Gene Expression Profiles");
		validDatasetNames.add("COSMIC Cell Line Gene CNV Profiles");
		validDatasetNames.add("COSMIC Cell Line Gene Mutation Profiles");
		validDatasetNames.add("GDSC Cell Line Gene Expression Profiles");
		validDatasetNames.add("Heiser Cell Line Gene Expression Profiles");
		validDatasetNames.add("HPA Cell Line Gene Expression Profiles");
		validDatasetNames.add("Klijn Cell Line Gene CNV Profiles");
		validDatasetNames.add("Klijn Cell Line Gene Expression Profiles");
		validDatasetNames.add("Klijn Cell Line Gene Mutation Profiles");
		for (Dataset ds : datasets) {
			assertTrue(validDatasetNames.contains(ds.getName()));
		}
	}

	@After
	public void tearDown() {
		request = null;
		response = null;
	}
}