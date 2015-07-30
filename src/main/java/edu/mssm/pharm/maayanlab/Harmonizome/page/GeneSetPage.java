package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
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
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/gene_set", "/gene_set/*" })
public class GeneSetPage extends HttpServlet {

	private static final long serialVersionUID = 6236670940063554419L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] query = URLUtil.getPathAsArray(request, true);
		String attributeName = query[0];
		String datasetName = query[1];
		Attribute attribute = null;
		Dataset dataset = null;
		Pair<List<Gene>, List<Gene>> genesByAttribute = null;

		try {
			HibernateUtil.beginTransaction();
			attribute = AttributeDAO.getByNameAndDataset(attributeName, datasetName);
			dataset = DatasetDAO.getByName(datasetName);
			genesByAttribute = GeneDAO.getFromAttributeByValue(attributeName, datasetName);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		if (attribute == null) {
			request.setAttribute("query", query);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "notFound.jsp").forward(request, response);
		} else {
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
	}
}