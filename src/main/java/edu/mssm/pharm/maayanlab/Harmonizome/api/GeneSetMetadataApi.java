package edu.mssm.pharm.maayanlab.Harmonizome.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneSetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.FeatureMetadataSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneSetMetadataSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.*;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT + "/*" })
public class GeneSetMetadataApi extends HttpServlet {

	private static final long serialVersionUID = -3627764278583624301L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(GeneSet.class, new GeneSetMetadataSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(Attribute.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(Feature.class, new FeatureMetadataSerializer());
		gsonBuilder.registerTypeAdapter(Gene.class, new BioEntityLinkSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] query = UrlUtil.getPathAsArray(request);
		PrintWriter out = response.getWriter();
		if (query == null) {
			response.sendRedirect("/" + Constant.HARMONIZOME + "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT);
		} else if (query.length != 2) {
			out.write(gson.toJson(new ErrorSchema()));
			out.flush();
		} else {
			try {
				HibernateUtil.beginTransaction();
				String attributeName = query[0];
				String datasetName = query[1];
				GeneSet geneSet = null;
				geneSet = GeneSetDao.getFromNameAndDataset(attributeName, datasetName);
				if (geneSet == null) {
					out.write(gson.toJson(new ErrorSchema()));
					out.flush();
				} else {
					out.write(gson.toJson(geneSet, GeneSet.class));
					out.flush();
				}
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				he.printStackTrace();
				HibernateUtil.rollbackTransaction();
			} finally {
				HibernateUtil.close();
			}
		}
	}
}