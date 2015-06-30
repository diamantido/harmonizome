package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.PAGE_BASE_URL + "/hgnc_root_family/*" })
public class HgncFamilyRootPage extends HttpServlet {

	private static final long serialVersionUID = -594042157095699907L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String queriedSymbol = URLUtil.get(req, true);
		if (queriedSymbol.equals("")) {
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "search_landing.jsp").forward(req, resp);
		} else {
			List<String> genes = new ArrayList<String>();
			HgncRootFamily hrf = null;
			try {
				HibernateUtil.beginTransaction();
				hrf = DAO.getHgncRootFamilyByName(queriedSymbol);
				if (hrf != null) {
					for (Gene g : hrf.getGenes()) {
						genes.add(g.getSymbol());
					}
				}
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
						
			if (hrf == null) {
				req.setAttribute("query", queriedSymbol);
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "not_found.jsp").forward(req, resp);				
			} else {				
				req.setAttribute("name", hrf.getName());
				req.setAttribute("genes", genes.toArray(new String[genes.size()]));
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "hgnc_root_family.jsp").forward(req, resp);
			}
		}
	}
}