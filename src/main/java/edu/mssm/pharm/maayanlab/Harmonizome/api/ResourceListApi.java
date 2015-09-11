package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.ResourceLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = {
	"/" + Constant.API_URL + "/" + Resource.ENDPOINT,
	"/" + Constant.API_URL + "/" + Resource.ENDPOINT + "/"
})
public class ResourceListApi extends HttpServlet {
	
	private static final long serialVersionUID = -519155299260378318L;
	
	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Resource.class, new ResourceLinkSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListApi.doGet(request, response, Resource.class, Resource.ENDPOINT, gson);
	}
}
