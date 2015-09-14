package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.BioEntityAlphabetizer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/gene_set", "/gene_set/*" })
public class GeneSetPage extends HttpServlet {

	private static final long serialVersionUID = 6236670940063554419L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] query = UrlUtil.getPathAsArray(request, true);
		if (query.length == 2) {
			try {
				HibernateUtil.beginTransaction();
				String attributeName = query[0];
				String datasetName = query[1];
				Attribute attribute = AttributeDAO.getByNameAndDataset(attributeName, datasetName);
				Dataset dataset = DatasetDAO.getFromName(datasetName);
				Pair<List<Gene>, List<Gene>> genesByAttribute = GeneDAO.getFromAttributeByValue(attributeName, datasetName);
				if (attribute == null) {
					request.setAttribute("query", query);
					request.getRequestDispatcher(Constant.TEMPLATE_DIR + "noSearchResults.jsp").forward(request, response);
				} else {
					Collections.sort(genesByAttribute.getRight(), new BioEntityAlphabetizer());
					Collections.sort(genesByAttribute.getLeft(), new BioEntityAlphabetizer());
					int numGenes = genesByAttribute.getLeft().size() + genesByAttribute.getRight().size();
					String geneSetDescription = dataset.getGeneSetDescription();
					geneSetDescription = geneSetDescription.replace("{0}", attribute.getNameFromDataset());
					geneSetDescription = numGenes + " " + geneSetDescription;
					request.setAttribute("geneSetDescription", geneSetDescription);
					request.setAttribute("attribute", attribute);
					request.setAttribute("dataset", dataset);
					request.setAttribute("genesByAttribute", genesByAttribute);
					request.getRequestDispatcher(Constant.TEMPLATE_DIR + "geneSet.jsp").forward(request, response);
				}
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				he.printStackTrace();
				HibernateUtil.rollbackTransaction();
			}
		} else {
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.jsp").forward(request, response);
		}
	}
}