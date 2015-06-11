package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.PAGES_BASE_URL + "/page/*" })
public class Page extends HttpServlet {

	private static final long serialVersionUID = -652055513491031817L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String queriedName = URLUtil.get(req, "dataset");
		System.out.println(queriedName);
		if (queriedName.equals("")) {
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "search_landing.jsp").forward(req, resp);
		} else {
			Dataset dataset = null;
			try {
				HibernateUtil.beginTransaction();
				dataset = DAO.getDatasetByName(queriedName);
				if (dataset != null) {
					req.setAttribute("useDataset", "true");
					req.setAttribute("datasetName", dataset.getName());
					req.setAttribute("datasetResource", dataset.getResource().getName());
					req.setAttribute("datasetDescription", dataset.getDescription());
					req.setAttribute("datasetAssociation", dataset.getAssociation());
					req.setAttribute("datasetAttribute", dataset.getAttributeType().getName());
					
				}
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}

			if (dataset == null) {
				req.setAttribute("query", queriedName);
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "not_found.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "page.jsp").forward(req, resp);
			}
		}
	}
}
