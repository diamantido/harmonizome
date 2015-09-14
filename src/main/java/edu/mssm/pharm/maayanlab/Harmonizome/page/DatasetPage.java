package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Dataset.ENDPOINT, "/" + Dataset.ENDPOINT + "/*" })
public class DatasetPage extends HttpServlet {

	private static final long serialVersionUID = -652055513491031817L;

	public void doGetTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response, true);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response, false);
	}
	
	private void doGet(HttpServletRequest request, HttpServletResponse response, boolean isTest) throws ServletException, IOException {
		String query = UrlUtil.getPath(request);
		if (query == null) {
			doNotFound(request, response);
		}
		try {
			Dataset dataset = null;
			List<Attribute> attributesFromDataset = null;
			Long numGenes = null;
			Long numGeneAttributeAssociations = null;

			HibernateUtil.beginTransaction();
			dataset = DatasetDAO.getFromName(query);
			if (dataset != null) {
				if (!isTest) {
					dataset.setNumPageViews(dataset.getNumPageViews() + 1);
				}
				numGenes = GeneDAO.getCountByDataset(query);
				numGeneAttributeAssociations = DatasetDAO.getCountGeneAttributeAssocations(query);
				attributesFromDataset = AttributeDAO.getFromDataset(query);
			}
			if (dataset == null) {
				doNotFound(request, response);
			} else {
				request.setAttribute("numGenes", numGenes);
				request.setAttribute("numGeneAttributeAssociations", numGeneAttributeAssociations);
				request.setAttribute("attributesFromDataset", attributesFromDataset);
				request.setAttribute("dataset", dataset);
				RequestDispatcher r = request.getRequestDispatcher(Constant.TEMPLATE_DIR + "dataset.jsp");
				r.forward(request, response);
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}
	
	private void doNotFound(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.jsp").forward(request, response);
	}
}