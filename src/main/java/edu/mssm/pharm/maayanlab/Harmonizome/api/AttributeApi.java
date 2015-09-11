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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.AttributeSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.DatasetLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneSetLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Attribute.ENDPOINT + "/*" })
public class AttributeApi extends HttpServlet {

	private static final long serialVersionUID = -5505560500020374363L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Attribute.class, new AttributeSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new DatasetLinkSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetLinkSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = UrlUtil.getPath(request);
		List<Attribute> attributes = null;
		Map<String, List<Attribute>> attributeSchema = new HashMap<String, List<Attribute>>();

		try {
			HibernateUtil.beginTransaction();
			attributes = AttributeDAO.getByName(query);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		}

		attributeSchema.put("attributes", attributes);
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(attributeSchema));
		out.flush();
	}
}