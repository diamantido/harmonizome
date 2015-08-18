package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.ResourceDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.ResourceSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;


@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/stats" })
public class StatsAPI extends HttpServlet {
	
	private static final long serialVersionUID = 2755067155579627424L;
	
	private static final Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Resource.class, new ResourceSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Resource> resources = new ArrayList<Resource>();
		try {
			HibernateUtil.beginTransaction();
			resources = ResourceDAO.getAll();
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(resources));
		out.flush();
	}
}