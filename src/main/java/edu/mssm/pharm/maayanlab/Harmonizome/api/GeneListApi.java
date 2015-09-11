package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = {
	"/" + Constant.API_URL + "/" + Gene.ENDPOINT,
	"/" + Constant.API_URL + "/" + Gene.ENDPOINT + "/"
})
public class GeneListApi extends HttpServlet {

	private static final long serialVersionUID = -8924091688621192218L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneLinkSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListApi.doGet(request, response, Gene.class, Gene.ENDPOINT, gson);
	}
}
