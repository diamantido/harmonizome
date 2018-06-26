package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AssociationDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.AssociationsSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.GeneSetAssociationsSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.AssociationWithGeneSetSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = {
	"/" + Constant.API_URL + "/" + Association.ENDPOINT,
	"/" + Constant.API_URL + "/" + Association.ENDPOINT + "/"
})
public class GeneSetListWithFiltersApi extends HttpServlet {

	private static final long serialVersionUID = 3419499266408427043L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(Association.class, new AssociationWithGeneSetSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String geneSymbol = UrlUtil.getParameter(request, "gene");
		String datasetName = UrlUtil.getParameter(request, "dataset");
		Dataset dataset = null;
		GeneSetAssociationsSchema schema = new GeneSetAssociationsSchema();
		
		try {
			HibernateUtil.beginTransaction();
			dataset = GenericDao.get(Dataset.class, datasetName);
			Pair<List<Association>, List<Association>> associations = AssociationDao.getFromGeneAndDataset(geneSymbol, datasetName);
			
			List<Association> upAssociations = associations.getLeft();
			List<Association> downAssociations = associations.getRight();
			//Collections.sort(upFeatures, new BioEntityAlphabetizer());
			//Collections.sort(downFeatures, new BioEntityAlphabetizer());

			AssociationsSchema up = new AssociationsSchema(dataset.getPositiveAssociation(), upAssociations);
			AssociationsSchema down = new AssociationsSchema(dataset.getNegativeAssociation(), downAssociations);
			
			schema.setUp(up);
			schema.setDown(down);
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
