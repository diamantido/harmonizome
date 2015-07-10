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
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSimpleDeserializer;

public class TestSearchAPIDataset extends Mockito {

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
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneSimpleDeserializer());
		gson = gsonBuilder.create();
	}

	@Test
	public void testByDataset() throws IOException, ServletException {
		when(request.getParameter("dataset")).thenReturn("Achilles_Cell_Line_Gene_Essentiality_Profiles");
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<Dataset> datasets = jsonSchema.getDataset();
		assertEquals(datasets.get(0).getName(), "Achilles Cell Line Gene Essentiality Profiles");
	}

	@Test
	public void testByDatasetType() throws IOException, ServletException {
		when(request.getParameter("datasetType")).thenReturn("prediction");
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<Dataset> datasets = jsonSchema.getDataset();
		assertEquals(datasets.size(), 6);
		List<String> validDatasetNames = new ArrayList<String>();
		validDatasetNames.add("JASPAR Transcription Factor Targets");
		validDatasetNames.add("MotifMap Transcription Factor Targets");
		validDatasetNames.add("Recon X Biomolecular Interactions");
		validDatasetNames.add("TargetScan microRNA Targets");
		validDatasetNames.add("TRANSFAC Predicted Transcription Factor Targets");
		validDatasetNames.add("UniProt Protein Domain Annotations");
		for (Dataset ds : datasets) {
			assertTrue(validDatasetNames.contains(ds.getName()));
		}
	}

	@Test
	public void testByDatasetGroup() throws IOException, ServletException {
		when(request.getParameter("datasetGroup")).thenReturn("omics");
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<Dataset> datasets = jsonSchema.getDataset();
		assertEquals(datasets.size(), 2);
		List<String> validDatasetNames = new ArrayList<String>();
		validDatasetNames.add("ESCAPE Omics Signatures of Genes and Proteins for Stem Cells");
		validDatasetNames.add("GeneSigDB Published Gene Signatures");
		for (Dataset ds : datasets) {
			assertTrue(validDatasetNames.contains(ds.getName()));
		}
	}

	@Test
	public void testByAttributeGroup() throws ServletException, IOException {
		when(request.getParameter("attributeGroup")).thenReturn("chemical");
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<Dataset> datasets = jsonSchema.getDataset();
		assertEquals(datasets.size(), 9);
	}

	@Test
	public void testByAttributeType() throws ServletException, IOException {
		when(request.getParameter("attributeType")).thenReturn("biological_process");
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		SuggestSchema jsonSchema = gson.fromJson(json, SuggestSchema.class);
		List<Dataset> datasets = jsonSchema.getDataset();
		assertEquals(datasets.size(), 1);
		assertEquals(datasets.get(0).getName(), "GO Biological Process Annotations");
	}
}