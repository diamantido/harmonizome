package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.SEARCH_BASE_URL, "/" + Constant.SEARCH_BASE_URL + "/*" })
public class Search extends HttpServlet {

	private static final long serialVersionUID = 8241820002853425713L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request, true);

		Dataset exactDataset = null;
		Gene exactGene = null;
		Attribute exactAttribute = null;

		int datasetIdToIgnore;
		int geneIdToIgnore;
		int attributeIdToIgnore;

		List<Dataset> datasets = new ArrayList<Dataset>();
		List<Gene> genes = new ArrayList<Gene>();
		List<Attribute> attributes = new ArrayList<Attribute>();

		List<String> datasetSuggestions = new ArrayList<String>();
		List<String> geneSuggestions = new ArrayList<String>();
		List<String> attributeSuggestions = new ArrayList<String>();

		try {
			HibernateUtil.beginTransaction();

			// Datasets
			// --------
			exactDataset = DatasetDAO.getByName(query);
			if (exactDataset != null) {
				datasetIdToIgnore = exactDataset.getId();
				datasets.addAll(DatasetDAO.getByWordInNameButIgnoreExactMatch(query, datasetIdToIgnore));
				datasets.add(exactDataset);
			} else {
				datasets.addAll(DatasetDAO.getByWordInName(query));
			}
			if (genes.size() == 0) {
				datasetSuggestions.addAll(DatasetDAO.getSuggestions(query));
			}

			// Genes
			// -----
			exactGene = GeneDAO.getBySymbol(query);
			if (exactGene != null) {
				geneIdToIgnore = exactGene.getId();
				genes.addAll(GeneDAO.getByWordInSymbolButIgnoreExactMatch(query, geneIdToIgnore));
				genes.add(exactGene);
			} else {
				genes.addAll(GeneDAO.getByWordInSymbol(query));
			}
			if (genes.size() == 0) {
				geneSuggestions.addAll(GeneDAO.getSuggestions(query));
			}

			// Attributes
			// ----------
			exactAttribute = AttributeDAO.getByName(query);
			if (exactAttribute != null) {
				attributeIdToIgnore = exactAttribute.getId();
				attributes.addAll(AttributeDAO.getByWordInNameButIgnoreExactMatch(query, attributeIdToIgnore));
				attributes.add(0, exactAttribute);
			} else {
				attributes.addAll(AttributeDAO.getByWordInName(query));
			}
			if (attributes.size() == 0) {
				attributeSuggestions.addAll(AttributeDAO.getSuggestions(query));
			}

			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		if (query == null || (noExactMatchesFound(datasets, genes, attributes) && noSuggestionsFound(datasetSuggestions, geneSuggestions, attributeSuggestions))) {
			showNotFound(request, response, query);
		} else if (noExactMatchesFound(datasets, genes, attributes) && !noSuggestionsFound(datasetSuggestions, geneSuggestions, attributeSuggestions)) {
			request.setAttribute("datasetSuggestions", datasetSuggestions);
			request.setAttribute("geneSuggestions", geneSuggestions);
			request.setAttribute("attributeSuggestions", attributeSuggestions);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "suggestPage.jsp").forward(request, response);
		} else {
			request.setAttribute("datasets", datasets);
			request.setAttribute("genes", genes);
			request.setAttribute("attributes", attributes);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "searchPage.jsp").forward(request, response);
		}
	}
	
	private void showNotFound(HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
		request.setAttribute("queryType", "entity");
		request.setAttribute("query", query);
		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "notFound.jsp").forward(request, response);
	}
	
	private boolean noExactMatchesFound(List<Dataset> d, List<Gene> g, List<Attribute> a) {
		return d.size() == 0 && g.size() == 0 && a.size() == 0;
	}
	
	private boolean noSuggestionsFound(List<String> d, List<String> g, List<String> a) {
		return d.size() == 0 && g.size() == 0 && a.size() == 0;
	}
}