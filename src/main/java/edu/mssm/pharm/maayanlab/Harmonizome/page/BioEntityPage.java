package edu.mssm.pharm.maayanlab.Harmonizome.page;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.BioEntityMetadata;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BioEntityPage {

	public static <T> void doGet(HttpServletRequest request, HttpServletResponse response, Class<T> klass) throws ServletException, IOException {
		try {
			HibernateUtil.beginTransaction();
			
			String query = UrlUtil.getPath(request);
			T bioEntity = null;
			bioEntity = GenericDao.get(klass, query);
			if (bioEntity == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				BioEntityMetadata anno = klass.getAnnotation(BioEntityMetadata.class);
				request.setAttribute(anno.name(), bioEntity);
				request.getRequestDispatcher(Constant.TEMPLATE_DIR + anno.jsp()).forward(request, response);
			}
			
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}		
	}
}