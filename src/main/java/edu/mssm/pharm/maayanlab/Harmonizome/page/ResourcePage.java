package edu.mssm.pharm.maayanlab.Harmonizome.page;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.PublicationDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.BioEntityMetadata;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Publication;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/" + Resource.ENDPOINT + "/*" })
public class ResourcePage extends HttpServlet {

	private static final long serialVersionUID = -6863403853881795787L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateUtil.beginTransaction();
			
			String query = UrlUtil.getPath(request);
			Resource resource = null;
			List<Publication> publications = null;
			resource = GenericDao.get(Resource.class, query);
			// Use a query so we don't have to remove duplicates manually.
			publications = PublicationDao.getFromResource(query);
			if (resource == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				BioEntityMetadata anno = Resource.class.getAnnotation(BioEntityMetadata.class);
				request.setAttribute("publications", publications);
				request.setAttribute(anno.name(), resource);
				request.getRequestDispatcher(Constant.TEMPLATE_DIR + anno.jsp()).forward(request, response);
			}

			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}
	}
}