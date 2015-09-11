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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.ProteinDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.EntityListSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.ProteinLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;


@WebServlet(urlPatterns = {
	"/" + Constant.API_URL + "/" + Protein.ENDPOINT,
	"/" + Constant.API_URL + "/" + Protein.ENDPOINT + "/"
})
public class ProteinListApi extends HttpServlet {

	private static final long serialVersionUID = -2597515820484732283L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Protein.class, new ProteinLinkSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cursor = request.getParameter(Constant.API_CURSOR);
		int startAt = cursor == null ? 0 : Integer.parseInt(cursor);
		EntityListSchema<Protein> geneBaseSchema = new EntityListSchema<Protein>(Protein.ENDPOINT, startAt);
		
		List<Protein> proteins = null;
		String query = request.getParameter("q");
		try {
			HibernateUtil.beginTransaction();
			proteins = ProteinDAO.getAll(query, startAt);
			geneBaseSchema.setEntities(proteins);
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