package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.SearchResults;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { "/" + Constant.SEARCH_URL, "/" + Constant.SEARCH_URL + "/*" })
public class SearchPage extends HttpServlet {

	private static final long serialVersionUID = 8241820002853425713L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request, true);
		SearchResults searchResults = new SearchResults(query);
		if (searchResults.noMatches()) {
			showNotFound(request, response, query);
		} else if (searchResults.onlySuggestions()) {
			Map<String, List<String>> suggestions = searchResults.getSuggestions();
			request.setAttribute("datasetSuggestions", suggestions.get("datasets"));
			request.setAttribute("geneSuggestions", suggestions.get("genes"));
			request.setAttribute("attributeSuggestions", suggestions.get("attributes"));
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "suggest.jsp").forward(request, response);
		} else {
			request.setAttribute("datasets", searchResults.getDatasets());
			request.setAttribute("genes", searchResults.getGenes());
			request.setAttribute("attributes", searchResults.getAttributes());
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "search.jsp").forward(request, response);
		}
	}
	
	private void showNotFound(HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
		request.setAttribute("queryType", "entity");
		request.setAttribute("query", query);
		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "notFound.jsp").forward(request, response);
	}
}