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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.EntityListSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class ListApi {
	
	public static <T> void doGet(HttpServletRequest request, HttpServletResponse response, Class<T> klass, String endpoint) throws ServletException, IOException {
		try {
			HibernateUtil.beginTransaction();
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(klass, new BioEntityLinkSerializer());
			Gson gson = gsonBuilder.create();
			
			String cursor = request.getParameter(Constant.API_CURSOR);
			int startAt = cursor == null ? 0 : Integer.parseInt(cursor);
			Long count = GenericDao.getCount(klass);
			
			PrintWriter out = response.getWriter();
			if (startAt >= count) {
				ErrorSchema schema = new ErrorSchema(ErrorSchema.Status.NOT_FOUND, "Cursor is out of range.");
				out.write(gson.toJson(schema));
			} else {
				EntityListSchema<T> schema = new EntityListSchema<T>(count, endpoint, startAt);
				List<T> entities = null;
				entities = GenericDao.getAll(klass, startAt);
				schema.setEntities(entities);
				out.write(gson.toJson(schema));
			}
			out.flush();
			
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}
	}
}