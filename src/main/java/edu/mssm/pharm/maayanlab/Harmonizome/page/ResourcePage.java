package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.PublicationDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Publication;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Resource.ENDPOINT + "/*" })
public class ResourcePage extends HttpServlet {

	private static final long serialVersionUID = -6863403853881795787L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = UrlUtil.getPath(request);
		Resource resource = null;
		List<Publication> publications = null;
		try {
			HibernateUtil.beginTransaction();
			resource = GenericDAO.getBioEntityFromKeyColumn(Resource.class, query);
			// Use a query so we don't have to remove duplicates manually.
			publications = PublicationDAO.getFromResource(query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
					
		if (resource == null) {
			request.setAttribute("query", query);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.jsp").forward(request, response);				
		} else {
			request.setAttribute("publications", publications);
			request.setAttribute("resource", resource);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "resource.jsp").forward(request, response);
		}
	}
}