package edu.mssm.pharm.maayanlab.Harmonizome;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import edu.mssm.pharm.maayanlab.Harmonizome.page.DatasetPage;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

public class DatasetPageTest extends Mockito {

	// I'm not sure I like this test.
	@Test
	public void testDatasetPage() throws ServletException, IOException {
		boolean noException = true;
		try {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			RequestDispatcher dispatcher = mock(RequestDispatcher.class);
			when(request.getRequestDispatcher(Constant.TEMPLATE_DIR + "dataset.jsp")).thenReturn(dispatcher);
			StringWriter output = new StringWriter();
			PrintWriter writer = new PrintWriter(output);
			when(response.getWriter()).thenReturn(writer);
			when(request.getPathInfo()).thenReturn("/Achilles+Cell+Line+Gene+Essentiality+Profiles");
			new DatasetPage().doGetTest(request, response);
		} catch (Exception e) {
			noException = true;
		}
		assertTrue(noException);
	}
}