package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncTerminalFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class DAO {

	public static List<Gene> getAllGenes() {
		@SuppressWarnings("unchecked")
		List<Gene> genes = (List<Gene>) HibernateUtil.getAll(Gene.class);
		return genes;
	}

	public static Gene getGeneBySymbol(String symbol) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Gene.class).add(Restrictions.eq("symbol", symbol).ignoreCase());
		return (Gene) criteria.uniqueResult();
	}

	public static Gene getGeneBySynonymSymbol(String symbol) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(GeneSynonym.class).add(Restrictions.eq("symbol", symbol).ignoreCase());
		GeneSynonym geneSynonym = (GeneSynonym) criteria.uniqueResult();
		return (geneSynonym == null ? null : geneSynonym.getGene());
	}

	public static List<Dataset> getAllDatasets() {
		@SuppressWarnings("unchecked")
		List<Dataset> datasets = (List<Dataset>) HibernateUtil.getAll(Dataset.class);
		return datasets;
	}

	public static Dataset getDatasetByName(String name) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Dataset.class).add(Restrictions.eq("name", name).ignoreCase());
		return (Dataset) criteria.uniqueResult();
	}

	public static Protein getProteinBySymbol(String symbol) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Protein.class).add(Restrictions.eq("symbol", symbol).ignoreCase());
		return (Protein) criteria.uniqueResult();
	}

	public static HgncRootFamily getHgncRootFamilyByName(String name) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(HgncRootFamily.class).add(Restrictions.eq("name", name).ignoreCase());
		return (HgncRootFamily) criteria.uniqueResult();
	}

	public static HgncTerminalFamily getHgncTerminalFamilyByName(String name) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(HgncTerminalFamily.class).add(Restrictions.eq("name", name).ignoreCase());
		return (HgncTerminalFamily) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public static List<Dataset> filterDataset(String dataset, String datasetGroup, String datasetType, String attribute, String attributeGroup, String attributeType, String gene, String idgFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession()
				.createCriteria(Dataset.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		if (dataset != null) {
			criteria.add(Restrictions.eq("name", dataset));
		}
		if (datasetGroup != null) {
			criteria.createCriteria("datasetGroup").add(Restrictions.eq("name", datasetGroup));
		}
		if (datasetType != null) {
			criteria.createCriteria("datasetType").add(Restrictions.eq("name", datasetType));
		}
		if (attributeGroup != null) {
			criteria.createCriteria("attributeGroup").add(Restrictions.eq("name", attributeGroup));
		}
		if (attributeType != null) {
			criteria.createCriteria("attributeType").add(Restrictions.eq("name", attributeType));
		}

		return (List<Dataset>) criteria.list();
	}


	@SuppressWarnings("unchecked")
	public static List<DatasetGroup> filterDatasetGroup(String dataset, String datasetGroup, String datasetType, String attribute, String attributeGroup, String attributeType, String gene,
			String idgFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession()
				.createCriteria(DatasetGroup.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		boolean datasetCriteriaCreated = false;
		if (dataset != null) {
			datasetCriteriaCreated = true;
			criteria.createAlias("datasets", "ds").add(Restrictions.eq("ds.name", dataset));
		}
		if (datasetGroup != null) {
			criteria.add(Restrictions.eq("name", datasetGroup));
		}
		if (datasetType != null) {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.datasetType", "dst").add(Restrictions.eq("dst.name", datasetType));
		}
		if (attributeGroup != null) {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeGroup", "dsg").add(Restrictions.eq("dsg.name", attributeGroup));
		}
		if (attributeType != null) {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeType", "dsa").add(Restrictions.eq("dsa.name", attributeType));
		}

		return (List<DatasetGroup>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public static List<DatasetType> filterDatasetType(String dataset, String datasetGroup, String datasetType, String attribute, String attributeGroup, String attributeType, String gene,
			String idgFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession()
				.createCriteria(DatasetType.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		boolean datasetCriteriaCreated = false;
		if (dataset != null) {
			datasetCriteriaCreated = true;
			criteria.createAlias("datasets", "ds").add(Restrictions.eq("ds.name", dataset));
		}
		if (datasetGroup != null) {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.datasetGroup", "dsg").add(Restrictions.eq("dsg.name", datasetGroup));
		}
		if (datasetType != null) {
			criteria.add(Restrictions.eq("name", datasetType));
		}
		if (attributeGroup != null) {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeGroup", "dsag").add(Restrictions.eq("dsag.name", attributeGroup));
		}
		if (attributeType != null) {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeType", "dsat").add(Restrictions.eq("dsat.name", attributeType));
		}

		return (List<DatasetType>) criteria.list();
	}

	/*@SuppressWarnings("unchecked")
	public static List<Attribute> filterAttribute(String dataset, String datasetGroup, String datasetType, String attribute, String attributeGroup, String attributeType, String gene, String idgFamily) {
		Criteria criteria = HibernateUtil.getCurrentSession()
			.createCriteria(Attribute.class)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Attribute>) criteria.list();
	}*/

	@SuppressWarnings("unchecked")
	public static List<AttributeGroup> filterAttributeGroup(String dataset, String datasetGroup, String datasetType, String attribute, String attributeGroup, String attributeType, String gene,
			String idgFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession()
				.createCriteria(AttributeGroup.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		boolean datasetCriteriaCreated = false;
		if (dataset != null) {
			datasetCriteriaCreated = true;
			criteria.createAlias("datasets", "ds").add(Restrictions.eq("ds.name", dataset));
		}
		
		return (List<AttributeGroup>) criteria.list();
	}
}