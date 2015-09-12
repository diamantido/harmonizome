package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.NamingAuthority;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/naming_authority/*" })
public class NamingAuthorityPage extends HttpServlet {

	private static final long serialVersionUID = -3138571504349534938L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = UrlUtil.getPath(request);
		NamingAuthority namingAuthority = null;
		
		try {
			HibernateUtil.beginTransaction();
			namingAuthority = GenericDAO.getBioEntityFromKeyColumn(NamingAuthority.class, query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		if (namingAuthority == null) {
		} else {
			request.setAttribute("namingAuthority", namingAuthority);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "namingAuthority.jsp").forward(request, response);
		}
	}
}