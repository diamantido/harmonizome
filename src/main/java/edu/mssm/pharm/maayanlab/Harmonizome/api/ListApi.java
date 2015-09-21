package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.EntityListSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class ListApi {
	
	public static <T> void doGet(HttpServletRequest request, HttpServletResponse response, Class<T> klass, String endpoint) throws ServletException, IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(klass, new BioEntityLinkSerializer());
		Gson gson = gsonBuilder.create();
		
		String cursor = request.getParameter(Constant.API_CURSOR);
		int startAt = cursor == null ? 0 : Integer.parseInt(cursor);
		EntityListSchema<T> schema = new EntityListSchema<T>(endpoint, startAt);
		List<T> entities = null;

		try {
			HibernateUtil.beginTransaction();
			entities = GenericDAO.getAll(klass, startAt);
			schema.setEntities(entities);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}

		PrintWriter out = response.getWriter();
		out.write(gson.toJson(schema));
		out.flush();
	}
}