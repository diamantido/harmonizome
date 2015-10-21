package edu.mssm.pharm.maayanlab.Harmonizome.page;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.SearchResults;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.UserSearchDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns = { "/" + Constant.SEARCH_URL, "/" + Constant.SEARCH_URL + "/*" })
public class SearchResultsPage extends HttpServlet {

	private static final long serialVersionUID = 8241820002853425713L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = UrlUtil.getParameter(request, "q");
		String type = UrlUtil.getParameter(request, "t");
        String meta = UrlUtil.getParameter(request, "m");
		
		if (query == null || query == "") {
			showNoSearchResults(request, response, "");
		} else {
			try {
				HibernateUtil.beginTransaction();

                // The example links send "m=example" so we don't save a bunch of "STAT3" queries.
                if (meta == null) {
                    UserSearchDao.save(query);
                }

				SearchResults searchResults = new SearchResults(query, type);
				if (searchResults.noMatches()) {
					showNoSearchResults(request, response, query);
				} else if (searchResults.onlySuggestions()) {
					Map<String, List<String>> suggestions = searchResults.getSuggestions();
					request.setAttribute("datasetSuggestions", suggestions.get("datasets"));
					request.setAttribute("geneSuggestions", suggestions.get("genes"));
					request.setAttribute("geneSetSuggestions", suggestions.get("geneSets"));
					request.getRequestDispatcher(Constant.TEMPLATE_DIR + "suggest.jsp").forward(request, response);
				} else {
					Set<Dataset> datasets = searchResults.getDatasets();
					Set<Gene> genes = searchResults.getGenes();
					Set<GeneSet> geneSets = searchResults.getGeneSets();
					String summary = buildSummary(query, datasets, genes, geneSets);
					if (type != null && !type.equals("all")) {
						/* This configures the view to show a "clear" filter. */
						request.setAttribute("isFilteredPage", true);
					} else {
						request.setAttribute("isFilteredPage", false);
					}
					request.setAttribute("query", query);
					request.setAttribute("summary", summary);
					request.setAttribute("datasets", datasets);
					request.setAttribute("genes", genes);
					request.setAttribute("geneSets", geneSets);
					request.getRequestDispatcher(Constant.TEMPLATE_DIR + "searchResults.jsp").forward(request, response);
				}

                HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				he.printStackTrace();
				HibernateUtil.rollbackTransaction();
			} finally {
				HibernateUtil.close();
			}
		}
	}

	private void showNoSearchResults(HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
		request.setAttribute("query", query);
		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "noSearchResults.jsp").forward(request, response);
	}

	private String buildSummary(String query, Set<Dataset> datasets, Set<Gene> genes, Set<GeneSet> geneSets) {
		StringBuilder sb = new StringBuilder();
		sb.append("Results for ").append("\"").append(query).append("\": ");
		if (datasets.size() > 0) {
			sb.append(datasets.size()).append(datasets.size() == 1 ? " dataset, " : " datasets, ");
		}
		if (genes.size() > 0) {
            int count = genes.size();
			if (count == Constant.DB_MAX_RESULTS) {
                String countStr = Integer.toString(count) + "+";
                sb.append(countStr);
            } else {
                sb.append(count);
            }
            sb.append(count == 1 ? " gene, " : " genes, ");
		}		
		if (geneSets.size() > 0) {
            int count = geneSets.size();
            if (count == Constant.DB_MAX_RESULTS) {
                String countStr = Integer.toString(count) + "+";
                sb.append(countStr);
            } else {
                sb.append(count);
            }
            sb.append(count == 1 ? " gene set, " : " gene sets, ");
		}
		return StringUtils.removeEnd(sb.toString(), ", ");
	}
}