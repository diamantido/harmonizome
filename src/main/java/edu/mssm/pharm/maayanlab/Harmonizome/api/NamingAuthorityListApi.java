package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mssm.pharm.maayanlab.Harmonizome.model.NamingAuthority;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = {
	"/" + Constant.API_URL + "/" + NamingAuthority.ENDPOINT,
	"/" + Constant.API_URL + "/" + NamingAuthority.ENDPOINT + "/"
})
public class NamingAuthorityListApi extends HttpServlet {

	private static final long serialVersionUID = -331437958947059414L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListApi.doGet(request, response, NamingAuthority.class, NamingAuthority.ENDPOINT);
	}
}
