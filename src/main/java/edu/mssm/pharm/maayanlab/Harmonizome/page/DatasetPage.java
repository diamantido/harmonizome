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
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/dataset", "/dataset/*" })
public class DatasetPage extends HttpServlet {

	private static final long serialVersionUID = -652055513491031817L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request);
		Dataset dataset = null;
		List<Attribute> attributesFromDataset = null;
		Long numGenes = null;
		Long numGeneAttributeAssociations = null;

		try {
			HibernateUtil.beginTransaction();
			dataset = DatasetDAO.getByName(query);
			if (dataset != null) {
				dataset.setNumPageViews(dataset.getNumPageViews() + 1);
				numGenes = GeneDAO.getCountByDataset(query);
				numGeneAttributeAssociations = DatasetDAO.getCountGeneAttributeAssocations(query);
				attributesFromDataset = AttributeDAO.getFromDataset(query);
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		
		if (query == null || dataset == null) {
			request.setAttribute("query", query);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.jsp").forward(request, response);
		} else {
			request.setAttribute("numGenes", numGenes);
			request.setAttribute("numGeneAttributeAssociations", numGeneAttributeAssociations);
			request.setAttribute("attributesFromDataset", attributesFromDataset);
			request.setAttribute("dataset", dataset);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "dataset.jsp").forward(request, response);
		}
	}
}