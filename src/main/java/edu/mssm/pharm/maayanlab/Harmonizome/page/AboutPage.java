package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.StatsDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Stats;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/about" })
public class AboutPage extends HttpServlet {

	private static final long serialVersionUID = -4641583897568170335L;
	
	private static String introTextBase = "Thanks to technological advances in genomics, "
		+ "transcriptomics, proteomics, metabolomics, and related fields, projects that "
		+ "generate a large number of measurements of the properties of cells, tissues, "
		+ "model organisms, and patients are becoming commonplace in biomedical "
		+ "research. In addition, curation projects are making great progress mining "
		+ "biomedical literature to extract and aggregate decades worth of research "
		+ "findings into online databases. Such projects are generating a wealth of "
		+ "information that potentially can guide research toward novel biomedical "
		+ "discoveries and advances in healthcare. To facilitate access to and "
		+ "learning from biomedical Big Data, we created the Harmonizome: a collection "
		+ "of information about genes and proteins from {0} datasets provided by {1} "
		+ "online resources.";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateUtil.beginTransaction();
			Stats stats = StatsDao.get();
			request.setAttribute("stats", stats);
			String introText = introTextBase
				.replace("{0}", String.valueOf(stats.getNumDatasets()))
				.replace("{1}", String.valueOf(stats.getNumResources()));
			request.setAttribute("introText", introText);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "about.jsp").forward(request, response);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}
	}
}