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
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/attribute", "/attribute/*" })
public class AttributePage extends HttpServlet {

	private static final long serialVersionUID = -2620457476165309368L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.get(request, true);		
		Attribute attribute = null;
		List<Pair<Dataset, Pair<List<Gene>, List<Gene>>>> genesByDataset = null;

		try {
			HibernateUtil.beginTransaction();
			attribute = AttributeDAO.getByName(query);
			genesByDataset = GeneDAO.getByDatasetsFromAttribute(query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}

		if (attribute == null) {
			request.setAttribute("query", query);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "notFound.jsp").forward(request, response);
		} else {
			request.setAttribute("name", attribute.getName());
			request.setAttribute("description", attribute.getDescription());
			request.setAttribute("attributeGroup", attribute.getAttributeGroup().getName());
			request.setAttribute("attributeType", attribute.getAttributeType().getName());
			request.setAttribute("namingAuthority", attribute.getNamingAuthority());
			request.setAttribute("idFromNamingAuthority", attribute.getIdFromNamingAuthority());
			request.setAttribute("url", attribute.getUrl());
			request.setAttribute("genesByDataset", genesByDataset);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "attributePage.jsp").forward(request, response);
		}
	}
}