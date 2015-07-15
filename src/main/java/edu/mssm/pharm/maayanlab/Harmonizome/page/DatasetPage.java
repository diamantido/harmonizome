package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/dataset", "/dataset/*" })
public class DatasetPage extends HttpServlet {

	private static final long serialVersionUID = -652055513491031817L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request, true);
		Dataset dataset = null;
		List<Dataset> datasets = null;
		
		try {
			HibernateUtil.beginTransaction();
			if (query == null) {
				datasets = DatasetDAO.getAllDatasets();
			} else {
				dataset = DatasetDAO.getByName(query);
				if (dataset != null) {
					dataset.setNumPageViews(dataset.getNumPageViews() + 1);
				}
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		
		if (query == null) {
			request.setAttribute("datasets", datasets);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "datasetSearch.jsp").forward(request, response);
		} else if (dataset == null) {
			request.setAttribute("query", query);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "notFound.jsp").forward(request, response);
		} else {
			request.setAttribute("name", dataset.getName());
			request.setAttribute("resource", dataset.getResource().getName());
			request.setAttribute("description", dataset.getDescription());
			request.setAttribute("association", dataset.getAssociation());
			request.setAttribute("attribute", dataset.getAttributeType().getName());
			request.setAttribute("citation", "");
			Timestamp downloadDate = dataset.getDownloadDate();
			if (downloadDate == null) {
				request.setAttribute("download_date", "");
			} else {
				request.setAttribute("download_date", new SimpleDateFormat("MM/dd/yyyy").format(downloadDate));
			}
			request.setAttribute("downloads", dataset.getDownloads());
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "datasetPage.jsp").forward(request, response);
		}
	}
}