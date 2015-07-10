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
import edu.mssm.pharm.maayanlab.Harmonizome.json.GenePageSchema;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.serdes.GeneSerializer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
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
			List<String> genes = null;//new ArrayList<String>();
			try {
				HibernateUtil.beginTransaction();
				genes = GeneDAO.getGeneSymbols();
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
				gene = GeneDAO.getGeneBySymbol(query);
				if (gene == null) {
					gene = GeneDAO.getGeneBySynonymSymbol(query);
				}
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}
			
			/*
			// TODO: Make this a separate API endpoint for constructing matrices.
			Set<DatasetGroup> dsgs = new HashSet<DatasetGroup>();
			Set<AttributeGroup> ags = new HashSet<AttributeGroup>();
			for (Feature f : gene.getFeatures()) {
				dsgs.add(f.getDataset().getDatasetGroup());
				ags.add(f.getAttribute().getAttributeGroup());
			}
			
			Map<String, Object> summaryMatrix = new HashMap<String, Object>();
			
			// Column and row labels.
			Map<String, List<String>> labels = new HashMap<String, List<String>>();
			List<String> colLabels = new ArrayList<String>();
			for (DatasetGroup dsg : dsgs) {
				colLabels.add(dsg.getName());
			}
			labels.put("cols", colLabels);
			List<String> rowLabels = new ArrayList<String>();
			for (AttributeGroup ag : ags) {
				rowLabels.add(ag.getName());
			}
			labels.put("rows", rowLabels);
			summaryMatrix.put("labels", labels);

			// Calculate average of threshold value for summary matrix.
			Map<String, Map<String, List<Double>>> runningStats = new HashMap<String, Map<String, List<Double>>>();
			for (DatasetGroup dsg : dsgs) {
				Map<String, List<Double>> dsgm = new HashMap<String, List<Double>>();
				for (AttributeGroup ag : ags) {
					List<Double> l = new ArrayList<Double>();
					l.add(0, 0.0);
					l.add(1, 0.0);
					dsgm.put(ag.getName(), l);
				}
				runningStats.put(dsg.getName(), dsgm);
			}
			
			for (Feature f : gene.getFeatures()) {
				String datasetGroup = f.getDataset().getDatasetGroup().getName();
				String attributeGroup = f.getAttribute().getAttributeGroup().getName();
				List<Double> stats = runningStats.get(datasetGroup).get(attributeGroup);
				double newThresholdValue = stats.get(0) + Math.abs(f.getThresholdValue());
				stats.set(0, newThresholdValue);
				double newCount = stats.get(1) + 1;
				stats.set(1, newCount);
			}
			
			List<MatrixCell> cells = new ArrayList<MatrixCell>();
			for (DatasetGroup dsg : dsgs) {
				for (AttributeGroup ag : ags) {
					List<Double> stats = runningStats.get(dsg.getName()).get(ag.getName());
					double averageThresholdValue = stats.get(0) / stats.get(1);
					MatrixCell mn = new MatrixCell(
						dsg.getName(),
						ag.getName(),
						averageThresholdValue
					);
					cells.add(mn);
				}
			}
			
			summaryMatrix.put("cells", cells);*/
			
			GenePageSchema gps = new GenePageSchema();
			gps.setGene(gene);
			//gps.setSummaryMatrix(summaryMatrix);
			
			PrintWriter out = response.getWriter();
			out.write(gson.toJson(gps, GenePageSchema.class));
			out.flush();
		}
	}
}
