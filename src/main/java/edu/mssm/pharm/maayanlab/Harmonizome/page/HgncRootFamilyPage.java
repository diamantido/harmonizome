package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.HgncRootFamilyDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + HgncRootFamily.ENDPOINT + "/*" })
public class HgncRootFamilyPage extends HttpServlet {

	private static final long serialVersionUID = -594042157095699907L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = URLUtil.getPath(req);
		HgncRootFamily geneFamily = null;
		try {
			HibernateUtil.beginTransaction();
			geneFamily = HgncRootFamilyDAO.getByName(query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
					
		if (geneFamily == null) {
			req.setAttribute("query", query);
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.jsp").forward(req, resp);				
		} else {				
			req.setAttribute("geneFamily", geneFamily);
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "hgncRootFamily.jsp").forward(req, resp);
		}
	}
}