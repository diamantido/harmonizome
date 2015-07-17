package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
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

@WebServlet(urlPatterns = { "/gene_set", "/gene_set/*" })
public class GeneSetPage extends HttpServlet {

	private static final long serialVersionUID = 6236670940063554419L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] query = URLUtil.getPathAsArray(request, true);
		Attribute attribute = null;
		Dataset dataset = null;
		List<Gene> upGenes = null;
		//List<Gene> downGenes = null;

		try {
			HibernateUtil.beginTransaction();
			attribute = AttributeDAO.getByName(query[0]);
			dataset = DatasetDAO.getByName(query[1]);
			upGenes = GeneDAO.getByAttributeAndDatasetAndValue(query[0], query[1], 1);
			//downGenes = GeneDAO.getByAttributeAndDatasetAndValue(query[0], query[1], -1);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}

		if (attribute == null || dataset == null) {
			request.setAttribute("queryType", "gene set");
			request.setAttribute("query", query[0] + " + " + query[1]);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "notFound.jsp").forward(request, response);
		} else {
			request.setAttribute("attributeName", attribute.getName());
			request.setAttribute("datasetName", dataset.getName());
			request.setAttribute("datasetDescription", dataset.getDescription());
			request.setAttribute("attributeDescription", attribute.getDescription());
			request.setAttribute("attributeGroup", attribute.getAttributeGroup().getName());
			request.setAttribute("attributeType", attribute.getAttributeType().getName());
			request.setAttribute("namingAuthority", attribute.getNamingAuthority());
			request.setAttribute("idFromNamingAuthority", attribute.getIdFromNamingAuthority());
			request.setAttribute("url", attribute.getUrl());
			request.setAttribute("upGenes", upGenes);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "geneSet.jsp").forward(request, response);
		}
	}
}