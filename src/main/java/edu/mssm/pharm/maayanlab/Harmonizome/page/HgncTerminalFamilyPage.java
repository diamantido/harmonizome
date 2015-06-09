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
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncTerminalFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.PAGES_BASE_URL + "/hgnc_terminal_family/*" })
public class HgncTerminalFamilyPage extends HttpServlet {

	private static final long serialVersionUID = 8780184441176782877L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String queriedSymbol = URLUtil.get(req, true);
		if (queriedSymbol.equals("")) {
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "search_landing.jsp").forward(req, resp);
		} else {
			List<String> genes = new ArrayList<String>();
			HgncTerminalFamily htf = null;
			try {
				HibernateUtil.beginTransaction();
				htf = DAO.getHgncTerminalFamilyByName(queriedSymbol);
				if (htf != null) {
					for (Gene g : htf.getGenes()) {
						genes.add(g.getSymbol());
					}
				}
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
						
			if (htf == null) {
				req.setAttribute("query", queriedSymbol);
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "not_found.jsp").forward(req, resp);				
			} else {				
				req.setAttribute("name", htf.getName());
				req.setAttribute("genes", genes.toArray(new String[genes.size()]));
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "hgnc_root_family.jsp").forward(req, resp);
			}
		}
	}
}