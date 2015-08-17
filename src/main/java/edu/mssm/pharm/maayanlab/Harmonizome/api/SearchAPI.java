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
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.SearchResultsSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.AttributeTypeSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.DownloadSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.AttributeInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.DatasetInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.GeneInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Download;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.HttpStatusCode;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Constant.SEARCH_URL + "/*" })
public class SearchAPI extends HttpServlet {

	private static final long serialVersionUID = 418339934899492310L;

	private static final Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetInfoSerializer());
		gsonBuilder.registerTypeAdapter(Download.class, new DownloadSerializer());
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneInfoSerializer());
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeInfoSerializer());
		gsonBuilder.registerTypeAdapter(AttributeType.class, new AttributeTypeSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getParameter(request, "q");
		String type = URLUtil.getParameter(request, "t");
		PrintWriter out = response.getWriter();
		String json;
		if (query == null) {
			ErrorSchema errorSchema = new ErrorSchema(HttpStatusCode.NOT_FOUND);
			json = gson.toJson(errorSchema, ErrorSchema.class);
		} else {
			SearchResultsSchema searchResultsSchema = new SearchResultsSchema();
			searchResultsSchema.setSearchResults(new SearchResults(query, type));
			json = gson.toJson(searchResultsSchema, SearchResultsSchema.class);
		}
		out.write(json);
		out.flush();
	}
}