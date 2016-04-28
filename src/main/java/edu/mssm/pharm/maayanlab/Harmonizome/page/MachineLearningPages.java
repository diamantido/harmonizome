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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
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
	private static String commonText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = UrlUtil.getPath(request);
		if (!isValidPath(path)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		try {
			HibernateUtil.beginTransaction();
			request.setAttribute("title", pathToTitle(path));
			Class<?> klass = urlToClass.get(path);
			// TODO: Get all sorted by probability.
			List<?> predictions = GenericDao.getAll(klass, 0);
			request.setAttribute("note", note);
			request.setAttribute("commonText", commonText);
			request.setAttribute("predictions", predictions);
			request.getRequestDispatcher(pathToCamelCaseJSP(path)).forward(request, response);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}
	}

	private boolean isValidPath(String path) {
		return urlToClass.get(path) != null;
	}

	private String pathToTitle(String path) {
		return path.split("_")[0].toUpperCase() + " Predictions";
	}

	private String pathToCamelCaseJSP(String urlPath) {
		return Constant.TEMPLATE_DIR + "machine_learning/" + urlPath + ".jsp";
	}
}
