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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.EntityListSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneInfoSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Gene.ENDPOINT, "/" + Constant.API_URL + "/" + Gene.ENDPOINT + "/" })
public class GeneListApi extends HttpServlet {

	private static final long serialVersionUID = -8924091688621192218L;
	
	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneInfoSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cursor = request.getParameter(Constant.API_CURSOR);
		int startAt = cursor == null ? 0 : Integer.parseInt(cursor);
		EntityListSchema<Gene> geneBaseSchema = new EntityListSchema<Gene>(Gene.ENDPOINT, startAt);
		
		List<Gene> genes = null;
		String query = request.getParameter("q");
		try {
			HibernateUtil.beginTransaction();
			genes = GeneDAO.getAll(query, startAt);
			geneBaseSchema.setEntities(genes);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		PrintWriter out = response.getWriter();
		out.write(gson.toJson(geneBaseSchema));
		out.flush();
	}
}
