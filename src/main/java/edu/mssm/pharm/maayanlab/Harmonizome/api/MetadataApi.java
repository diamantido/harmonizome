package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class MetadataApi {

	public static <E> void doGet(HttpServletRequest request, HttpServletResponse response, Class<E> klass, Gson gson) throws ServletException, IOException {
		String query = UrlUtil.getPath(request);
		E entity = null;

		try {
			HibernateUtil.beginTransaction();
			entity = GenericDAO.getBioEntityFromKeyColumn(klass, query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}
		
		PrintWriter out = response.getWriter();
		if (entity == null) {
			out.write(gson.toJson(new ErrorSchema()));
		} else {
			out.write(gson.toJson(entity, klass));
		}
		out.flush();
	}
}