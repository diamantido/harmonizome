package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.ProteinLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = {
	"/" + Constant.API_URL + "/" + Protein.ENDPOINT,
	"/" + Constant.API_URL + "/" + Protein.ENDPOINT + "/"
})
public class ProteinListApi extends HttpServlet {

	private static final long serialVersionUID = -2597515820484732283L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Protein.class, new ProteinLinkSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListApi.doGet(request, response, Protein.class, Protein.ENDPOINT, gson);
	}
}