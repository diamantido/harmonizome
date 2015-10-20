package edu.mssm.pharm.maayanlab.Harmonizome.page;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.BioEntityMetadata;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.BioEntityAlphabetizer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = { "/" + HgncRootFamily.ENDPOINT + "/*" })
public class HgncRootFamilyPage extends HttpServlet {
	
	private static final long serialVersionUID = -1555076002089558768L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateUtil.beginTransaction();

			String query = UrlUtil.getPath(request);
			HgncRootFamily family = null;
			family = GenericDao.get(HgncRootFamily.class, query);
			if (family == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				BioEntityMetadata anno = HgncRootFamily.class.getAnnotation(BioEntityMetadata.class);
				Collections.sort(family.getGenes(), new BioEntityAlphabetizer());
				request.setAttribute(anno.name(), family);
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