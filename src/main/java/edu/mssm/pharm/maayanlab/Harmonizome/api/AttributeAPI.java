package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
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
import com.google.gson.reflect.TypeToken;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.AttributeSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.AttributeInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.DatasetInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.info.GeneSetInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.URLUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Attribute.ENDPOINT + "/*" })
public class AttributeAPI extends HttpServlet {

	private static final long serialVersionUID = 4628749718947044737L;

	private static Gson gson;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.getPath(request);
		if (query == null) {
			doGetAll(request, response);
		} else {
			doGetByName(request, response, query);
		}	
	}
	
	public void doGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeInfoSerializer());
		gson = gsonBuilder.create();

		String cursor = request.getParameter("cursor");
		Map<String, Object> schema = new HashMap<String, Object>();
		List<Attribute> attributes = null;
		int next = 0;
		
		PrintWriter out = response.getWriter();
		try {
			HibernateUtil.beginTransaction();
			if (cursor == null) {
				next = Constant.API_MAX_RESULTS;
				attributes = AttributeDAO.getAll(0);
			} else {
				Integer c = Integer.parseInt(cursor);
				attributes = AttributeDAO.getAll(c);
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		String nextString = "/" + Constant.API_URL + "/" + Attribute.ENDPOINT + "?cursor=" + next;
		schema.put("next", nextString);
		schema.put("attributes", attributes);
		out.write(gson.toJson(schema));
		out.flush();
	}
	
	public void doGetByName(HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetInfoSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetInfoSerializer());
		gson = gsonBuilder.create();
		List<Attribute> attributes = null;
		try {
			HibernateUtil.beginTransaction();
			attributes = AttributeDAO.getByName(query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
		
		Type listType = new TypeToken<List<Attribute>>(){}.getType();
		out.write(gson.toJson(attributes, listType));
		out.flush();
	}
}
