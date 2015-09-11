package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.EntityListSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.AttributeLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Attribute.ENDPOINT, "/" + Constant.API_URL + "/" + Attribute.ENDPOINT + "/" })
public class AttributeListApi extends HttpServlet {

	private static final long serialVersionUID = 4628749718947044737L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeLinkSerializer());
		gson = gsonBuilder.create();	
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cursor = request.getParameter("cursor");
		int startAt = cursor == null ? 0 : Integer.parseInt(cursor);
		EntityListSchema<Attribute> schema = new EntityListSchema<Attribute>(Attribute.ENDPOINT, startAt);

		try {
			HibernateUtil.beginTransaction();
			List<Attribute> attributes;
			if (cursor == null) {
				attributes = GenericDAO.getAll(Attribute.class, 0);
			} else {
				Integer c = Integer.parseInt(cursor);
				attributes = GenericDAO.getAll(Attribute.class, c);
			}
			schema.setEntities(attributes);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		PrintWriter out = response.getWriter();
		out.write(gson.toJson(schema));
		out.flush();
	}
}
