package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneralDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.PAGE_BASE_URL + "/protein/*" })
public class ProteinPage extends HttpServlet {

	private static final long serialVersionUID = 264508702310729565L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String queriedSymbol = URLUtil.get(req);
		if (queriedSymbol.equals("")) {
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "search_landing.jsp").forward(req, resp);
		} else {
			Protein protein = null;
			try {
				HibernateUtil.beginTransaction();
				protein = GeneralDAO.getProteinBySymbol(queriedSymbol);
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
						
			if (protein == null) {
				req.setAttribute("query", queriedSymbol);
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "not_found.jsp").forward(req, resp);				
			} else {
				req.setAttribute("note", "protein");
				req.setAttribute("symbol", protein.getSymbol());
				req.setAttribute("name", protein.getName());
				req.setAttribute("uniprotUrl", protein.getUniprotUrl());
				req.setAttribute("gene", protein.getGene().getSymbol());
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "protein_page.jsp").forward(req, resp);
			}
		}
	}
}