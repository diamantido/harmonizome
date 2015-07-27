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

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.json.SuggestSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Measurement;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetGroupDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.MeasurementDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSimpleDeserializer;

public class TestSearchAPIMeasurement extends Mockito {

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
		gsonBuilder.registerTypeAdapter(Measurement.class, new MeasurementDeserializer());
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneSimpleDeserializer());
		gson = gsonBuilder.create();
	}

	@Test
	public void testByDataset() throws ServletException, IOException {
		when(request.getParameter("dataset")).thenReturn("PC_Protein-Protein_Interactions");
		new SuggestAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<Measurement> measurements = jsonSchema.getMeasurements();
		assertEquals(measurements.get(0).getName(), "data aggregation");
	}

	@Test
	public void testByDatasetGroup() throws ServletException, IOException {
		when(request.getParameter("datasetGroup")).thenReturn("disease_or_phenotype_associations");
		new SuggestAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<Measurement> measurements = jsonSchema.getMeasurements();
		assertEquals(measurements.size(), 5);
		List<String> validMeasurementNames = new ArrayList<String>();
		validMeasurementNames.add("data aggregation");
		validMeasurementNames.add("genetic association");
		validMeasurementNames.add("literature curation");
		validMeasurementNames.add("phenotyping");
		validMeasurementNames.add("text-mining");
		for (Measurement measurement : jsonSchema.getMeasurements()) {
			assertTrue(validMeasurementNames.contains(measurement.getName()));
		}
	}

	@Test
	public void testByMeasurement() throws ServletException, IOException {
		when(request.getParameter("measurement")).thenReturn("DNA_methylation_bisulfite_sequencing,_MeDIP-seq,_or_MRE-seq"); 
		new SuggestAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<Measurement> measurements = jsonSchema.getMeasurements();
		assertEquals(measurements.size(), 1);
		assertEquals(measurements.get(0).getName(), "DNA methylation bisulfite sequencing, MeDIP-seq, or MRE-seq");
	}

	@Test
	public void testByAttributeGroup() throws ServletException, IOException {
		when(request.getParameter("attributeGroup")).thenReturn("sequence_feature"); 
		new SuggestAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<Measurement> measurements = jsonSchema.getMeasurements();
		assertEquals(measurements.size(), 1);
		assertEquals(measurements.get(0).getName(), "eQTL mapping");
	}

	@Test
	public void testByAttributeType() throws ServletException, IOException {
		when(request.getParameter("attributeType")).thenReturn("ligand_(protein)"); 
		new SuggestAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<Measurement> measurements = jsonSchema.getMeasurements();
		assertEquals(measurements.size(), 2);
		List<String> validDatasetTypeNames = new ArrayList<String>();
		validDatasetTypeNames.add("literature curation");
		validDatasetTypeNames.add("protein phosphorylation SILAC");
		for (Measurement measurement : jsonSchema.getMeasurements()) {
			assertTrue(validDatasetTypeNames.contains(measurement.getName()));
		}
	}
}