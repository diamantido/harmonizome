package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/gene", "/gene/*" })
public class GenePage extends HttpServlet {

	private static final long serialVersionUID = 4256183225988457817L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request);
		Gene gene = null;
		boolean isSynonym = false;
		int numAssociations = 0;
		int numCategories = 0;
		int numDatasets = 0;
		String entityList = "";
		List<Pair<Dataset, Pair<List<Attribute>, List<Attribute>>>> attributesByDataset = null;

		try {
			HibernateUtil.beginTransaction();
			if (query != null) {
				gene = GeneDAO.getBySymbol(query);
				if (gene == null) {
					gene = GeneDAO.getBySynonymSymbol(query);
					if (gene != null) {
						isSynonym = true;
					}
				}
				if (gene != null) {
					attributesByDataset = AttributeDAO.getByDatasetsFromGene(query);
					// We could make another DB query, but the number datasets
					// should never be greater than ~100.
					Set<String> uniqueDatasetGroups = new HashSet<String>();
					for (Pair<Dataset, Pair<List<Attribute>, List<Attribute>>> pair : attributesByDataset) {
						uniqueDatasetGroups.add(pair.getLeft().getDatasetGroup().getName());
						numAssociations += pair.getRight().getRight().size() + pair.getRight().getRight().size();
					}
					numCategories = uniqueDatasetGroups.size();
					numDatasets = attributesByDataset.size();
				}
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		if (gene == null) {
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.jsp").forward(request, response);
		} else {
			String allAssociationsSummary = numAssociations + " associations covering " + numCategories + " categories of biological entities " + entityList + " from " + numDatasets + " datasets";
			request.setAttribute("allAssociationsSummary", allAssociationsSummary);
			request.setAttribute("note", isSynonym ? "Gene; redirected from " + query : "Gene");
			request.setAttribute("gene", gene);
			request.setAttribute("attributesByDataset", attributesByDataset);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "gene.jsp").forward(request, response);
		}
	}
}
