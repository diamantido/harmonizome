package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.SearchResults;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { "/" + Constant.SEARCH_URL, "/" + Constant.SEARCH_URL + "/*" })
public class SearchPage extends HttpServlet {

	private static final long serialVersionUID = 8241820002853425713L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getParameter(request, "q");
		String type = URLUtil.getParameter(request, "t");
		SearchResults searchResults = new SearchResults(query, type);
		if (searchResults.noMatches()) {
			showNotFound(request, response, query);
		} else if (searchResults.onlySuggestions()) {
			Map<String, List<String>> suggestions = searchResults.getSuggestions();
			request.setAttribute("datasetSuggestions", suggestions.get("datasets"));
			request.setAttribute("geneSuggestions", suggestions.get("genes"));
			request.setAttribute("attributeSuggestions", suggestions.get("attributes"));
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "suggest.jsp").forward(request, response);
		} else {
			Set<Dataset> datasets = searchResults.getDatasets();
			Set<Gene> genes = searchResults.getGenes();
			Set<Attribute> attributes = searchResults.getAttributes();
			String summary = buildSummary(datasets, genes, attributes);
			if (type != null) {
				/* This configures the view to show a "clear" filter. */
				request.setAttribute("isFilteredPage", true);
			}
			request.setAttribute("query", query);
			request.setAttribute("summary", summary);
			request.setAttribute("datasets", datasets);
			request.setAttribute("genes", genes);
			request.setAttribute("attributes", attributes);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "search.jsp").forward(request, response);
		}
	}

	private void showNotFound(HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
		request.setAttribute("query", query);
		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "notFound.jsp").forward(request, response);
	}

	private String buildSummary(Set<Dataset> datasets, Set<Gene> genes, Set<Attribute> attributes) {
		String datasetCount = datasets.size() == 0 ? "" : Integer.toString(datasets.size()) + " datasets";
		String geneCount = genes.size() == 0 ? "" : Integer.toString(genes.size()) + " genes";
		String attributeCount = attributes.size() == 0 ? "" : Integer.toString(attributes.size()) + " gene sets";
		String[] counts = { datasetCount, geneCount, attributeCount };
		StringBuilder countSb = new StringBuilder();
		boolean first = true;
		for (int i = 0; i < counts.length; i++) {
			String c = counts[i];
		    if (c.equals("")) {
		    	continue;
		    }
		    if (first) {
		        first = false;
		    } else {
		    	countSb.append(", ");
		    }
		    countSb.append(c);
		}
		return countSb.toString();
	}
}