package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/attribute", "/attribute/*" })
public class AttributePage extends HttpServlet {

	private static final long serialVersionUID = -2620457476165309368L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = URLUtil.get(req, true);		
		Attribute attribute = null;
		try {
			HibernateUtil.beginTransaction();
			attribute = AttributeDAO.getAttributeByName(query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}

		if (attribute == null) {
			req.setAttribute("query", query);
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "notFound.jsp").forward(req, resp);
		} else {
			req.setAttribute("name", attribute.getName());
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "attributePage.jsp").forward(req, resp);
		}
	}
}