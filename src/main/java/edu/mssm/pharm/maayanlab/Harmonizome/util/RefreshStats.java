package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.StatsDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
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
			
			Long numDatasets = GenericDao.getCount(Dataset.class);
			Long numGenes = GenericDao.getCount(Gene.class);
			Long numAttributes = GenericDao.getCount(Attribute.class);
			Long numResources = GenericDao.getCount(Resource.class);
			Long numFeatures = GenericDao.getCount(Feature.class);
			
			Stats stats = StatsDao.get();
			stats.setNumDatasets(numDatasets);
			stats.setNumGenes(numGenes);
			stats.setNumAttributes(numAttributes);
			stats.setNumResources(numResources);
			stats.setNumFeatures(numFeatures);
			
			Map<DatasetGroup, Long> datasetGroupCounts = new HashMap<DatasetGroup, Long>();
			for (DatasetGroup datasetGroup : GenericDao.getAll(DatasetGroup.class)) {
				datasetGroupCounts.put(datasetGroup, new Long(0));
			}
			Map<AttributeGroup, Long> attributeGroupCounts = new HashMap<AttributeGroup, Long>();
			for (AttributeGroup attributeGroup : GenericDao.getAll(AttributeGroup.class)) {
				attributeGroupCounts.put(attributeGroup, new Long(0));
			}

			for (Resource resource : GenericDao.getAll(Resource.class)) {
				long numGeneSetsPerResource = 0;
				for (Dataset dataset : resource.getDatasets()) {
					numGeneSetsPerResource += dataset.getGeneSets().size();
					
					// Count dataset groups
					DatasetGroup datasetGroup = dataset.getDatasetGroup();
					Long currentCount = datasetGroupCounts.get(datasetGroup);
					Long newCount = currentCount + 1;
					datasetGroupCounts.put(datasetGroup, newCount);
					
					// Count attribute groups
					AttributeGroup attributeGroup = dataset.getAttributeGroup();
					Long currentCount2 = attributeGroupCounts.get(attributeGroup);
					Long newCount2 = currentCount2 + dataset.getGeneSets().size();
					attributeGroupCounts.put(attributeGroup, newCount2);
				}
				resource.setNumGeneSets(numGeneSetsPerResource);
			}
			
			for (Entry<DatasetGroup, Long> entry : datasetGroupCounts.entrySet()) {
				DatasetGroup datasetGroup = entry.getKey();
				Long count = entry.getValue();
				datasetGroup.setNumDatasets(count);
			}
			
			for (Entry<AttributeGroup, Long> entry : attributeGroupCounts.entrySet()) {
				AttributeGroup attributeGroup = entry.getKey();
				Long count = entry.getValue();
				attributeGroup.setNumAttributes(count);
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