package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetDeserializer;

public class TestSearchAPIAttributeGroup extends Mockito {

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
	public void testByDataset() throws IOException, ServletException {
	}

	@Test
	public void testByDatasetType() throws IOException, ServletException {
	}

	@Test
	public void testByDatasetGroup() throws IOException, ServletException {
	}

	@Test
	public void testByAttributeGroup() throws ServletException, IOException {
	}

	@Test
	public void testByAttributeType() throws ServletException, IOException {
	}
}
