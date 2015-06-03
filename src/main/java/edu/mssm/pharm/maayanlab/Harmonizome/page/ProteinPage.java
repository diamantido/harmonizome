package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncTerminalFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.PAGES_BASE_URL + "/protein/*" })
public class ProteinPage extends HttpServlet {

	private static final long serialVersionUID = 264508702310729565L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getPathInfo();
		if (path.equals("/")) {
			// TODO.
		} else {
			String queriedSymbol = path.substring(1);
			Protein protein = null;
			try {
				HibernateUtil.beginTransaction();
				protein = DAO.getProteinBySymbol(queriedSymbol);
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
						
			if (protein != null) {
				req.getSession().setAttribute("symbol", protein.getSymbol());
				req.getSession().setAttribute("name", protein.getName());
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "protein_page.jsp").forward(req, resp);
			}
		}
	}
}
