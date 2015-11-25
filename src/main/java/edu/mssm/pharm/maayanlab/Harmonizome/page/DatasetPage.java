package edu.mssm.pharm.maayanlab.Harmonizome.page;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetVisualization;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/" + Dataset.ENDPOINT + "/*" })
public class DatasetPage extends HttpServlet {

	private static final long serialVersionUID = -652055513491031817L;

	public void doGetTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response, true);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response, false);
	}
	
	private void doGet(HttpServletRequest request, HttpServletResponse response, boolean isTest) throws ServletException, IOException {
		String query = UrlUtil.getPath(request);
		if (query == null) {
			doNotFound(request, response);
		}
		try {
			HibernateUtil.beginTransaction();
			
			Long numGenes = null;
			Long numGeneAttributeAssociations = null;
			Dataset dataset = GenericDao.get(Dataset.class, query);
			if (dataset != null) {
				if (!isTest) {
					dataset.setNumPageViews(dataset.getNumPageViews() + 1);
				}
				numGenes = GeneDao.getCountByDataset(query);
				numGeneAttributeAssociations = DatasetDao.getCountGeneAttributeAssocations(query);

				for (DatasetVisualization dsv : dataset.getDatasetVisualizations()) {
					System.out.println(dsv);
				}

			}
			if (dataset == null) {
				doNotFound(request, response);
			} else {
				request.setAttribute("numGenes", numGenes);
				request.setAttribute("numGeneAttributeAssociations", numGeneAttributeAssociations);
				request.setAttribute("dataset", dataset);
				RequestDispatcher r = request.getRequestDispatcher(Constant.TEMPLATE_DIR + "dataset.jsp");
				r.forward(request, response);
			}

			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}
	}
	
	private void doNotFound(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
}