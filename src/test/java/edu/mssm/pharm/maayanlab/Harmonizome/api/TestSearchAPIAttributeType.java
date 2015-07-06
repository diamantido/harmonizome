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

import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.pojo.JsonSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetDeserializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSimpleDeserializer;

public class TestSearchAPIAttributeType extends Mockito {

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
		when(request.getParameter("dataset")).thenReturn("CCLE_Cell_Line_Gene_Expression_Profiles"); 
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<AttributeType> attributeTypes = jsonSchema.getAttributeType();
		assertEquals(attributeTypes.size(), 1);
		assertEquals(attributeTypes.get(0).getName(), "cell");
	}
	
	@Test
	public void testByDatasetGroup() throws IOException, ServletException {
		when(request.getParameter("datasetGroup")).thenReturn("proteomics"); 
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<AttributeType> attributeTypes = jsonSchema.getAttributeType();
		List<String> validAttribueTypeNames = new ArrayList<String>();
		validAttribueTypeNames.add("cellular component");
		validAttribueTypeNames.add("tissue");
		validAttribueTypeNames.add("cell or tissue");
		validAttribueTypeNames.add("protein complex");
		validAttribueTypeNames.add("drug");
		validAttribueTypeNames.add("gene");
		validAttribueTypeNames.add("ligand (protein)");
		for (AttributeType at : attributeTypes) {
			assertTrue(validAttribueTypeNames.contains(at.getName()));
		}
	}

	@Test
	public void testByDatasetType() throws IOException, ServletException {
		when(request.getParameter("datasetType")).thenReturn("genetic_association"); 
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<AttributeType> attributeTypes = jsonSchema.getAttributeType();
		List<String> validAttribueTypeNames = new ArrayList<String>();
		validAttribueTypeNames.add("phenotype");
		validAttribueTypeNames.add("disease");
		validAttribueTypeNames.add("trait");
		for (AttributeType at : attributeTypes) {
			assertTrue(validAttribueTypeNames.contains(at.getName()));
		}
	}

	@Test
	public void testByAttributeGroup() throws ServletException, IOException {
		when(request.getParameter("attributeGroup")).thenReturn("organism"); 
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<AttributeType> attributeTypes = jsonSchema.getAttributeType();
		assertEquals(attributeTypes.size(), 1);
		assertEquals(attributeTypes.get(0).getName(), "virus");
	}

	@Test
	public void testByAttributeType() throws ServletException, IOException {
		when(request.getParameter("attributeType")).thenReturn("molecular_function"); 
		new SearchAPI().doGet(request, response);
		writer.flush();
		String json = output.toString();
		JsonSchema jsonSchema = gson.fromJson(json, JsonSchema.class);
		List<AttributeType> attributeTypes = jsonSchema.getAttributeType();
		assertEquals(attributeTypes.size(), 1);
		assertEquals(attributeTypes.get(0).getName(), "molecular function");
	}
}
