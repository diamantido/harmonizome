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
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.DatasetListSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.DatasetInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Dataset.ENDPOINT })
public class DatasetListAPI extends HttpServlet {

	private static final long serialVersionUID = 1695966393931239258L;

	private static Gson gson;
	
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetInfoSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatasetListSchema datasetListSchema = new DatasetListSchema();
		try {
			HibernateUtil.beginTransaction();
			datasetListSchema.setDatasets(DatasetDAO.getAll());
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(datasetListSchema));
		out.flush();
	}
}