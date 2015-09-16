package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Attribute.ENDPOINT + "/*" })
public class AttributeMetadataApi extends HttpServlet {

	private static final long serialVersionUID = -5505560500020374363L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Attribute.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new BioEntityLinkSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MetadataApi.doGet(request, response, Attribute.class, gson);
	}
}