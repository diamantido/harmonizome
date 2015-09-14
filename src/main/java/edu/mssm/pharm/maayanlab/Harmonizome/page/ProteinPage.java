package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;

@WebServlet(urlPatterns = { "/" + Protein.ENDPOINT + "/*" })
public class ProteinPage extends HttpServlet {

	private static final long serialVersionUID = 264508702310729565L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BioEntityPage.doGet(request, response, Protein.class);
	}
}