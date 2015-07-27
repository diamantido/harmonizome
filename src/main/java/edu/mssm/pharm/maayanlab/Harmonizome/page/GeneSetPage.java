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
		
		String query = URLUtil.getPath(request, true);
		Attribute attribute = null;
		Dataset dataset = null;
		List<Gene> genesByAttribute = null;
		System.out.println(query);

		try {
			HibernateUtil.beginTransaction();
			attribute = AttributeDAO.getByName(query);
			dataset = DatasetDAO.getByAttribute(query);
			genesByAttribute = GeneDAO.getByAttribute(query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		if (attribute == null) {
			request.setAttribute("query", query);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "notFound.jsp").forward(request, response);
		} else {
			request.setAttribute("attributeName", attribute.getNameFromDataset());
			request.setAttribute("datasetDescription", dataset.getDescription());
			request.setAttribute("attributeDescription", attribute.getDescriptionFromDataset());
			request.setAttribute("attributeGroup", attribute.getAttributeType().getAttributeGroup().getName());
			request.setAttribute("attributeType", attribute.getAttributeType().getName());
			request.setAttribute("namingAuthority", attribute.getNamingAuthority());
			request.setAttribute("idFromNamingAuthority", attribute.getIdFromNamingAuthority());
			request.setAttribute("url", attribute.getUrlFromDataset());
			request.setAttribute("genesByAttribute", genesByAttribute);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "geneSet.jsp").forward(request, response);
		}
	}
}