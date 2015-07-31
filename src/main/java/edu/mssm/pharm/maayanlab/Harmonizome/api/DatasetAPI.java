package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.DatasetSimpleSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Dataset.ENDPOINT + "/*" })
public class DatasetAPI extends HttpServlet {

	private static final long serialVersionUID = 1695966393931239258L;

	private static Gson gson;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request);
		System.out.println(query);
		if (query == null) {
			doGetAll(request, response);
		} else {
			doGetBySymbol(request, response, query);
		}	
	}
	
	public void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Map<String, Object> datasetSchema = new HashMap<String, Object>();
		PrintWriter out = response.getWriter();
		try {
			HibernateUtil.beginTransaction();
			gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetSimpleSerializer());
			gson = gsonBuilder.create();
			List<Dataset> datasets = DatasetDAO.getAll();
			datasetSchema.put("datasets", datasets);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		out.write(gson.toJson(datasetSchema));
		out.flush();
	}
	
	public void doGetBySymbol(HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetSerializer());
		gson = gsonBuilder.create();
		Dataset dataset = null;
		try {
			HibernateUtil.beginTransaction();
			dataset = DatasetDAO.getByName(query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
		out.write(gson.toJson(dataset, Dataset.class));
		out.flush();
	}
}
