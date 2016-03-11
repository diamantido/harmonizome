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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AssociationDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.ErrorSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.GeneWithAssociationsSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.AssociationWithGeneSetSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.GeneMetadataSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Gene.ENDPOINT + "/*" })
public class GeneMetadataApi extends HttpServlet {

	private static final long serialVersionUID = -5484736863604374714L;

	private static Gson gson;
	private static GsonBuilder gsonBuilder;
	static {
		gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Association.class, new AssociationWithGeneSetSerializer());
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneMetadataSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(Protein.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(HgncRootFamily.class, new BioEntityLinkSerializer());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String symbol = UrlUtil.getPath(request);
		String showAssociations = UrlUtil.getParameter(request, "showAssociations");
		if (showAssociations != null && showAssociations.equals("true")) {
			doGetWithAssociations(request, response, symbol);
		} else {
			doGetWithoutAssociations(request, response, symbol);
		}
	}
	
	private void doGetWithoutAssociations(HttpServletRequest request, HttpServletResponse response, String symbol) throws ServletException, IOException {
		gson = gsonBuilder.create();
		try {
			HibernateUtil.beginTransaction();
			Gene gene = null;
			gene = GenericDao.get(Gene.class, symbol);
			if (gene == null) {
				gene = GeneDao.getFromSynonymSymbol(symbol);
			}
			PrintWriter out = response.getWriter();
			if (gene == null) {
				out.write(gson.toJson(new ErrorSchema()));
			} else {
				out.write(gson.toJson(gene, Gene.class));
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
	
	private void doGetWithAssociations(HttpServletRequest request, HttpServletResponse response, String symbol) throws ServletException, IOException {
		gson = gsonBuilder.create();
		try {
			HibernateUtil.beginTransaction();
			GeneWithAssociationsSchema schema = new GeneWithAssociationsSchema();
			Gene gene = null;
			gene = GenericDao.get(Gene.class, symbol);
			if (gene == null) {
				gene = GeneDao.getFromSynonymSymbol(symbol);
			}
			List<Association> associations = AssociationDao.getFromGene(gene.getSymbol());
			
			schema.setGene(gene);
			schema.setAssociations(associations);
			
			PrintWriter out = response.getWriter();
			out.write(gson.toJson(schema));
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