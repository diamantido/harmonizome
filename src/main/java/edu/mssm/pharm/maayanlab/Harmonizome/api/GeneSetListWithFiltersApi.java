package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
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

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneSetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.GeneSetAssociationsSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.schema.GeneSetListSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.json.serdes.BioEntityLinkSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.BioEntityAlphabetizer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + GeneSet.ENDPOINT })
public class GeneSetListWithFiltersApi extends HttpServlet {

	private static final long serialVersionUID = 3419499266408427043L;

	private static Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(Dataset.class, new BioEntityLinkSerializer());
		gsonBuilder.registerTypeAdapter(GeneSet.class, new BioEntityLinkSerializer());
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
			Pair<List<GeneSet>, List<GeneSet>> geneSets = GeneSetDao.getFromGeneAndDataset(geneSymbol, datasetName);
			
			List<GeneSet> upGeneSets = geneSets.getLeft();
			List<GeneSet> downGeneSets = geneSets.getRight();
			Collections.sort(upGeneSets, new BioEntityAlphabetizer());
			Collections.sort(downGeneSets, new BioEntityAlphabetizer());

			GeneSetListSchema up = new GeneSetListSchema(dataset.getPositiveAssociation(), upGeneSets);
			GeneSetListSchema down = new GeneSetListSchema(dataset.getNegativeAssociation(), downGeneSets);
			
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
