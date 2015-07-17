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
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Download;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.AttributeGroupSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.AttributeSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.AttributeTypeSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetGroupSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetTypeSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DownloadSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { Constant.API_URL + "/search/*" })
public class SearchAPI extends HttpServlet {

	private static final long serialVersionUID = 418339934899492310L;

	private static final Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetSerializer());
		gsonBuilder.registerTypeAdapter(DatasetGroup.class, new DatasetGroupSerializer());
		gsonBuilder.registerTypeAdapter(DatasetType.class, new DatasetTypeSerializer());
		gsonBuilder.registerTypeAdapter(Download.class, new DownloadSerializer());

		gsonBuilder.registerTypeAdapter(Gene.class, new GeneSerializer());
		
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeSerializer());		
		gsonBuilder.registerTypeAdapter(AttributeGroup.class, new AttributeGroupSerializer());
		gsonBuilder.registerTypeAdapter(AttributeType.class, new AttributeTypeSerializer());

		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getParameter(request, "q");
		String type = URLUtil.getParameter(request, "t");
		String json;
		if (query == null) {
			BaseJSON baseJson = new BaseJSON();
			json = gson.toJson(baseJson);
		} else {
			SearchResults searchResults = new SearchResults(query, type);
			json = gson.toJson(searchResults, SearchResults.class);
		}
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
	}
}