package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { Constant.PAGES_BASE_URL + "/gene/*" })
public class GenePage extends HttpServlet {

	private static final long serialVersionUID = 4256183225988457817L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getPathInfo();
		if (path.equals("/")) {
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "gene_landing.jsp").forward(req, resp);
			//return;
		} else {
			/*String symbol = path.substring(1);
			Gene gene = null;
			String canonicalSymbol = "";
		
			try {
				HibernateUtil.beginTransaction();
				gene = DAO.getGeneBySymbol(symbol);
				if (gene == null) {
					gene = DAO.getGeneBySynonymSymbol(symbol);
				}
				canonicalSymbol = gene.getSymbol();
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
			
			req.getSession().setAttribute("symbol", canonicalSymbol);
			req.getSession().setAttribute("name", gene.getName());
			req.getSession().setAttribute("description", gene.getDescription());
			req.getSession().setAttribute("synonyms", gene.getSynonyms());
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "gene_page.jsp").forward(req, resp);*/
		}
	}
}
