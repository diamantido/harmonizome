package edu.mssm.pharm.maayanlab.Harmonizome.util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.StatsDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Feature;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Stats;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/" + Constant.ADMIN_URL + "/refresh_stats" })
public class RefreshStats extends HttpServlet {

	private static final long serialVersionUID = 4359405827672550544L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			HibernateUtil.beginTransaction();
			
			Long numDatasets = GenericDAO.getCount(Dataset.class);
			Long numGenes = GenericDAO.getCount(Gene.class);
			Long numAttributes = GenericDAO.getCount(Attribute.class);
			Long numResources = GenericDAO.getCount(Resource.class);
			Long numFeatures = GenericDAO.getCount(Feature.class);
			
			Stats stats = StatsDAO.get();
			stats.setNumDatasets(numDatasets);
			stats.setNumGenes(numGenes);
			stats.setNumAttributes(numAttributes);
			stats.setNumResources(numResources);
			stats.setNumFeatures(numFeatures);
			
			for (Resource resource : GenericDAO.getAll(Resource.class)) {
				long numAttributesPerDataset = 0;
				for (Dataset dataset : resource.getDatasets()) {
					numAttributesPerDataset += dataset.getAttributes().size();
				}
				resource.setNumAttributes(numAttributesPerDataset);
			}
			
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}
}