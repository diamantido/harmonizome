package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.ProteinDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Protein.ENDPOINT + "/*" })
public class ProteinPage extends HttpServlet {

	private static final long serialVersionUID = 264508702310729565L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request);
		System.out.println(query);
		Protein protein = null;
		try {
			HibernateUtil.beginTransaction();
			protein = ProteinDAO.getByName(query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
					
		if (protein == null) {
			request.setAttribute("query", query);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.jsp").forward(request, response);				
		} else {
			request.setAttribute("protein", protein);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "protein.jsp").forward(request, response);
		}
	}
}