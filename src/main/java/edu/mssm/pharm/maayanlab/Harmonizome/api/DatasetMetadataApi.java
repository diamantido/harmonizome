package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.DatasetSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneSetLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Dataset.ENDPOINT + "/*" })
public class DatasetMetadataApi extends HttpServlet {

	private static final long serialVersionUID = 2013021100694227509L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetLinkSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MetadataApi.doGet(request, response, Dataset.class, gson);
	}
}
