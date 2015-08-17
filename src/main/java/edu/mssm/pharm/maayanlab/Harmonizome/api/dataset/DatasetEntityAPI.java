package edu.mssm.pharm.maayanlab.Harmonizome.api.dataset;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.DatasetSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.GeneSetInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.HttpStatusCode;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Dataset.ENDPOINT + "/*" })
public class DatasetEntityAPI extends HttpServlet {

	private static final long serialVersionUID = 2013021100694227509L;

	private static Gson gson;
	
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetInfoSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = URLUtil.getPath(request);
		if (name == null) {
			response.sendRedirect("/" + Constant.HARMONIZOME + "/" + Constant.API_URL + "/" + Dataset.ENDPOINT);
		} else {
			getFromName(request, response, name);
		}
	}

	private void getFromName(HttpServletRequest request, HttpServletResponse response, String name) throws ServletException, IOException {
		Dataset dataset = null;
		try {
			HibernateUtil.beginTransaction();
			dataset = DatasetDAO.getFromName(name);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
		PrintWriter out = response.getWriter();
		if (dataset == null) {
			ErrorSchema errorSchema = new ErrorSchema(HttpStatusCode.NOT_FOUND);
			out.write(gson.toJson(errorSchema));
		} else {
			out.write(gson.toJson(dataset, Dataset.class));
		}
		out.flush();
	}
}
