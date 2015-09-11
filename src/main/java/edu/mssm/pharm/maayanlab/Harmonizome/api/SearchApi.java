package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.SearchResults;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.AttributeLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.DatasetLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Constant.SEARCH_URL + "/*" })
public class SearchApi extends HttpServlet {

	private static final long serialVersionUID = 418339934899492310L;

	private static final Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetLinkSerializer());
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneLinkSerializer());
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeLinkSerializer());
		gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = UrlUtil.getParameter(request, "q");
		String type = UrlUtil.getParameter(request, "t");
		PrintWriter out = response.getWriter();
		String json;
		if (query == null) {
			json = gson.toJson(new ErrorSchema());
		} else {
			SearchResults searchResults = new SearchResults(query, type);
			json = gson.toJson(searchResults, SearchResults.class);
		}
		out.write(json);
		out.flush();
	}
}