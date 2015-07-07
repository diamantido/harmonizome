package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.mssm.pharm.maayanlab.Harmonizome.json.GenePageSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Feature;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.API_BASE_URL + "/gene/*" })
public class GeneAPI extends HttpServlet {

	private static final long serialVersionUID = -5484736863604374714L;

	private static final Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Gene.class, new GeneSerializer());
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = URLUtil.get(request);
		if (query == null) {
			List<String> genes = new ArrayList<String>();
			try {
				HibernateUtil.beginTransaction();
				for (Gene g : DAO.getAllGenes()) {
					genes.add(g.getSymbol());
				}
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
			PrintWriter out = response.getWriter();
			out.write(gson.toJson(genes));
			out.flush();
		} else {
			Gene gene = null;
			try {
				HibernateUtil.beginTransaction();
				gene = DAO.getGeneBySymbol(query);
				if (gene == null) {
					gene = DAO.getGeneBySynonymSymbol(query);
				}
				if (gene != null) {
					for (Feature f : gene.getFeatures()) {
						System.out.println(f.getAttribute().getAttributeGroup());
					}
				}
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
			
			/*Set<DatasetGroup> dsgs = new HashSet<DatasetGroup>();
			Set<AttributeGroup> ags = new HashSet<AttributeGroup>();
			Set<String> test = new HashSet<String>();
			for (Feature f : gene.getFeatures()) {
				dsgs.add(f.getDataset().getDatasetGroup());
				ags.add(f.getAttribute().getAttributeGroup());
				System.out.println(f.getAttribute().getAttributeGroup());
			}
			
			System.out.println("-----");
			for (DatasetGroup dsg : dsgs) {
				System.out.println(dsg.getName());
			}
			System.out.println("-----");
			for (AttributeGroup ag : ags) {
				System.out.println(ag);
			}
			System.out.println("-----");
			for (String s : test) {
				System.out.println(s);
			}
			System.out.println("-----");*/
			
			GenePageSchema gps = new GenePageSchema();
			gps.setGene(gene);
			
			PrintWriter out = response.getWriter();
			out.write(gson.toJson(gps, GenePageSchema.class));
			out.flush();
		}
	}
}
