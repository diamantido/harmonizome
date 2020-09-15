package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.PredictionDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GlPrediction;
import edu.mssm.pharm.maayanlab.Harmonizome.model.IcPrediction;
import edu.mssm.pharm.maayanlab.Harmonizome.model.KsPrediction;
import edu.mssm.pharm.maayanlab.Harmonizome.model.MpPrediction;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.ML_URL + "/*" })
public class MachineLearningPages extends HttpServlet {

	private static final long serialVersionUID = 257547618358285174L;

	private static Map<String, Class<?>> urlToClass;
	static {
		urlToClass = new HashMap<String, Class<?>>();
		urlToClass.put("gl_predictions", GlPrediction.class);
		urlToClass.put("ic_predictions", IcPrediction.class);
		urlToClass.put("ks_predictions", KsPrediction.class);
		urlToClass.put("mp_predictions", MpPrediction.class);
	}

	private static String note = "Machine Learning Case Study";

	private static String filename = "allpredictions.txt.gz";

	// TODO: When the Harmonizome is properly published, GREP for this variable
	// and fix all links.
	private static String pubWarning = "This link will be available upon publication.";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = UrlUtil.getPath(request);
		if (!isValidPath(path)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		try {
			HibernateUtil.beginTransaction();
			if (path != null) {
				request.setAttribute("title", pathToTitle(path));
				Class<?> klass = urlToClass.get(path);
				List<?> predictions = PredictionDao.getAllByProbability(klass);
				request.setAttribute("note", note);
				request.setAttribute("commonDownloadText", "The table below shows predictions with probabilities greater than or equal to 0.5. To download the full table:");
				request.setAttribute("downloadUrl", pathToMLDataDownloadUrl(path));
				request.setAttribute("predictions", predictions);
			} else {
				request.setAttribute("filename", filename);
			}
			request.setAttribute("pubWarning", pubWarning);
			request.getRequestDispatcher(pathToJSP(path)).forward(request, response);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}
	}

	private boolean isValidPath(String path) {
		return path == null || urlToClass.get(path) != null;
	}

	private String pathToTitle(String path) {
		if (path == null)
			return null;
		return path.split("_")[0].toUpperCase() + " Predictions";
	}

	private String pathToJSP(String path) {
		String dir = Constant.TEMPLATE_DIR + "machine_learning/";
		if (path == null)
			return dir + "machine_learning.jsp";
		return dir + path + ".jsp";
	}

	private String pathToMLDataDownloadUrl(String path) {
		return Constant.ML_DATA_DIR() + "/" + path + "/" + filename;
	}
}
