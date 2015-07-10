package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneralDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/attribute/*" })
public class AttributePage extends HttpServlet {

	private static final long serialVersionUID = -2620457476165309368L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String queriedSymbol = URLUtil.get(req);
		String[] queriedSymbols = queriedSymbol.split("\\+");
		String dataset = queriedSymbols[0];
		String gene = queriedSymbols[1];
		
		if (queriedSymbol.equals("") || queriedSymbols.length != 2) {
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "search_landing.jsp").forward(req, resp);
		} else {
			Attribute attribute = null;

			try {
				HibernateUtil.beginTransaction();
				attribute = GeneralDAO.getAttributeByDatasetAndGene(dataset, gene);
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}

			if (attribute == null) {
				req.setAttribute("query", queriedSymbol);
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "not_found.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "attribute_page.jsp").forward(req, resp);
			}
		}
	}
}