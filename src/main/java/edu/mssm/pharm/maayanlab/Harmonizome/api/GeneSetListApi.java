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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.EntityListSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneSetLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = {
	"/" + Constant.API_URL + "/" + GeneSet.ENDPOINT,
	"/" + Constant.API_URL + "/" + GeneSet.ENDPOINT + "/"
})
public class GeneSetListApi extends HttpServlet {

	private static final long serialVersionUID = -4351635599545445946L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Attribute.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetLinkSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cursor = request.getParameter(Constant.API_CURSOR);
		int startAt = cursor == null ? 0 : Integer.parseInt(cursor);
		EntityListSchema<GeneSet> geneSetSchema = new EntityListSchema<GeneSet>(GeneSet.ENDPOINT, startAt);
		List<GeneSet> geneSets = new ArrayList<GeneSet>();
		List<Attribute> attributes = null;
		String query = request.getParameter("q");

		try {
			HibernateUtil.beginTransaction();
			attributes = AttributeDAO.getAll(query, startAt);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		if (attributes != null) {
			for (Attribute attribute : attributes) {
				GeneSet gs = new GeneSet(attribute, attribute.getDataset());
				geneSets.add(gs);
			}
		}

		geneSetSchema.setEntities(geneSets);
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(geneSetSchema));
		out.flush();
	}
}
