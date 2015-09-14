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

import org.apache.commons.lang3.StringUtils;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.SearchResults;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { "/" + Constant.SEARCH_URL, "/" + Constant.SEARCH_URL + "/*" })
public class SearchResultsPage extends HttpServlet {

	private static final long serialVersionUID = 8241820002853425713L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = UrlUtil.getParameter(request, "q");
		String type = UrlUtil.getParameter(request, "t");
		
		if (query == null || query == "") {
			showNoSearchResults(request, response, "");
		} else {
			SearchResults searchResults = new SearchResults(query, type);
			if (searchResults.noMatches()) {
				showNoSearchResults(request, response, query);
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
				String summary = buildSummary(query, datasets, genes, attributes);
				if (type != null) {
					/* This configures the view to show a "clear" filter. */
					request.setAttribute("isFilteredPage", true);
				}
				request.setAttribute("query", query);
				request.setAttribute("summary", summary);
				request.setAttribute("datasets", datasets);
				request.setAttribute("genes", genes);
				request.setAttribute("attributes", attributes);
				request.getRequestDispatcher(Constant.TEMPLATE_DIR + "searchResults.jsp").forward(request, response);
			}
		}
	}

	private void showNoSearchResults(HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
		request.setAttribute("query", query);
		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "noSearchResults.jsp").forward(request, response);
	}

	private String buildSummary(String query, Set<Dataset> datasets, Set<Gene> genes, Set<Attribute> attributes) {
		StringBuilder sb = new StringBuilder();
		sb.append("Results for ").append("\"").append(query).append("\": ");
		if (datasets.size() != 0) {
			sb.append(datasets.size()).append(datasets.size() == 1 ? " dataset, " : " datasets, ");
		}
		if (genes.size() != 0) {
			sb.append(genes.size()).append(genes.size() == 1 ? " gene, " : " genes, ");
		}		
		if (attributes.size() != 0) {
			sb.append(attributes.size()).append(attributes.size() == 1 ? " gene set, " : " gene sets, ");
		}
		return StringUtils.removeEnd(sb.toString(), ", ");
	}
}