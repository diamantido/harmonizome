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

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetType;
import edu.mssm.pharm.maayanlab.Harmonizome.pojo.JsonSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetGroupDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetTypeDeserializer;

public class TestSearchAPIDatasetType extends Mockito {

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
	public void testByDataset() throws ServletException, IOException {
		when(request.getParameter("dataset")).thenReturn("PC_Protein-Protein_Interactions");
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<DatasetType> datasetTypes = jsonSchema.getDatasetType();
		assertEquals(datasetTypes.get(0).getName(), "data aggregation");
	}

	@Test
	public void testByDatasetGroup() throws ServletException, IOException {
		when(request.getParameter("datasetGroup")).thenReturn("disease_or_phenotype_associations");
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<DatasetType> datasetTypes = jsonSchema.getDatasetType();
		assertEquals(datasetTypes.size(), 5);
		List<String> validDatasetTypeNames = new ArrayList<String>();
		validDatasetTypeNames.add("data aggregation");
		validDatasetTypeNames.add("genetic association");
		validDatasetTypeNames.add("literature curation");
		validDatasetTypeNames.add("phenotyping");
		validDatasetTypeNames.add("text-mining");
		for (DatasetType dst : jsonSchema.getDatasetType()) {
			assertTrue(validDatasetTypeNames.contains(dst.getName()));
		}
	}

	@Test
	public void testByDatasetType() throws ServletException, IOException {
		when(request.getParameter("datasetType")).thenReturn("DNA_methylation_bisulfite_sequencing,_MeDIP-seq,_or_MRE-seq"); 
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<DatasetType> datasetTypes = jsonSchema.getDatasetType();
		assertEquals(datasetTypes.size(), 1);
		assertEquals(datasetTypes.get(0).getName(), "DNA methylation bisulfite sequencing, MeDIP-seq, or MRE-seq");
	}

	@Test
	public void testByAttributeGroup() throws ServletException, IOException {
		when(request.getParameter("attributeGroup")).thenReturn("sequence_feature"); 
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<DatasetType> datasetTypes = jsonSchema.getDatasetType();
		assertEquals(datasetTypes.size(), 1);
		assertEquals(datasetTypes.get(0).getName(), "eQTL mapping");
	}

	@Test
	public void testByAttributeType() throws ServletException, IOException {
		when(request.getParameter("attributeType")).thenReturn("ligand_(protein)"); 
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<DatasetType> datasetTypes = jsonSchema.getDatasetType();
		assertEquals(datasetTypes.size(), 2);
		List<String> validDatasetTypeNames = new ArrayList<String>();
		validDatasetTypeNames.add("literature curation");
		validDatasetTypeNames.add("protein phosphorylation SILAC");
		for (DatasetType dst : jsonSchema.getDatasetType()) {
			assertTrue(validDatasetTypeNames.contains(dst.getName()));
		}
	}
}