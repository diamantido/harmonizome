package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = {
	"/" + Constant.API_URL + "/" + GeneSet.ENDPOINT,
	"/" + Constant.API_URL + "/" + GeneSet.ENDPOINT + "/"
})
public class GeneSetListApi extends HttpServlet {

	private static final long serialVersionUID = 3857700637067563773L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListApi.doGet(request, response, GeneSet.class, GeneSet.ENDPOINT);
	}
}
