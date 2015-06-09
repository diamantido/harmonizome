package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
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
	public static List<Dataset> filterDataset(String dataset, String datasetGroup, String datasetType, String attribute, String attributeGroup, String attributeType, String gene, String geneFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Dataset.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Criteria featureCriteria = criteria.createCriteria("features");
		Criteria attributeCriteria = featureCriteria.createCriteria("attribute");
		Criteria geneCriteria = featureCriteria.createCriteria("gene");

		if (dataset != null) {
			criteria.add(Restrictions.eq("name", dataset));
		}
		if (datasetGroup != null) {
			criteria.createCriteria("datasetGroup").add(Restrictions.eq("name", datasetGroup));
		}
		if (datasetType != null) {
			criteria.createCriteria("datasetType").add(Restrictions.eq("name", datasetType));
		}
		if (attribute != null) {
			attributeCriteria.add(Restrictions.eq("name", attribute));
		}
		if (attributeGroup != null) {
			attributeCriteria.createCriteria("attributeGroup").add(Restrictions.eq("name", attributeGroup));
		}
		if (attributeType != null) {
			attributeCriteria.createCriteria("attributeType").add(Restrictions.eq("name", attributeType));
		}
		if (gene != null) {
			geneCriteria.add(Restrictions.eq("symbol", gene));
		}
		if (geneFamily != null) {
			geneCriteria.add(Restrictions.eq("idgFamily", geneFamily));
		}

		return (List<Dataset>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public static List<DatasetGroup> filterDatasetGroup(String dataset, String datasetGroup, String datasetType, String attribute, String attributeGroup, String attributeType, String gene,
			String geneFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(DatasetGroup.class).setProjection(Projections.distinct(Projections.property("name")));
		Criteria datasetCriteria = criteria.createCriteria("datasets");
		Criteria featuresCriteria = datasetCriteria.createCriteria("features");
		Criteria geneCriteria = featuresCriteria.createCriteria("gene");
		Criteria attributeCriteria = featuresCriteria.createCriteria("attribute");

		if (dataset != null) {
			datasetCriteria.add(Restrictions.eq("name", dataset));
		}
		if (datasetGroup != null) {
			criteria.add(Restrictions.eq("name", datasetGroup));
		}
		if (datasetType != null) {
			datasetCriteria.createCriteria("datasetType").add(Restrictions.eq("name", datasetType));
		}
		if (attribute != null) {
			datasetCriteria.createCriteria("attribute").add(Restrictions.eq("name", attribute));
		}
		if (attributeGroup != null) {
			attributeCriteria.createCriteria("attributeGroup").add(Restrictions.eq("name", attributeGroup));
		}
		if (attributeType != null) {
			attributeCriteria.createCriteria("attributeType").add(Restrictions.eq("name", attributeType));
		}
		if (gene != null) {
			geneCriteria.add(Restrictions.eq("symbol", gene));
		}
		if (geneFamily != null) {
			geneCriteria.add(Restrictions.eq("idgFamily", geneFamily));
		}

		return (List<DatasetGroup>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public static List<DatasetType> filterDatasetType(String dataset, String datasetGroup, String datasetType, String attribute, String attributeGroup, String attributeType, String gene,
			String geneFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(DatasetType.class).setProjection(Projections.distinct(Projections.property("name")));
		Criteria datasetCriteria = criteria.createCriteria("datasets");
		Criteria geneCriteria = datasetCriteria.createCriteria("features").createCriteria("gene");

		if (dataset != null) {
			datasetCriteria.add(Restrictions.eq("name", dataset));
		}
		if (datasetGroup != null) {
			datasetCriteria.createCriteria("datasetGroup").add(Restrictions.eq("name", datasetGroup));
		}
		if (datasetType != null) {
			criteria.add(Restrictions.eq("name", datasetType));
		}
		if (attribute != null) {
			datasetCriteria.createCriteria("attribute").add(Restrictions.eq("name", attribute));
		}
		if (attributeGroup != null) {
			datasetCriteria.createCriteria("attributeGroup").add(Restrictions.eq("name", attributeGroup));
		}
		if (attributeType != null) {
			datasetCriteria.createCriteria("attributeType").add(Restrictions.eq("name", attributeType));
		}
		if (gene != null) {
			geneCriteria.add(Restrictions.eq("symbol", gene));
		}
		if (geneFamily != null) {
			geneCriteria.add(Restrictions.eq("idgFamily", geneFamily));
		}

		return (List<DatasetType>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Attribute> filterAttribute(String dataset, String datasetGroup, String datasetType, String attribute, String attributeGroup, String attributeType, String gene, String geneFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Attribute.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Criteria featureCriteria = criteria.createCriteria("features");
		Criteria datasetCriteria = featureCriteria.createCriteria("dataset");
		Criteria geneCriteria = featureCriteria.createCriteria("gene");

		if (dataset != null) {
			featureCriteria.add(Restrictions.eq("name", dataset));
		}
		if (datasetGroup != null) {
			datasetCriteria.createCriteria("datasetGroup").add(Restrictions.eq("name", datasetGroup));
		}
		if (datasetType != null) {
			datasetCriteria.createCriteria("datasetType").add(Restrictions.eq("name", datasetType));
		}
		if (attribute != null) {
			criteria.add(Restrictions.eq("name", attribute));
		}
		if (attributeGroup != null) {
			criteria.add(Restrictions.eq("attributeGroup", attributeGroup));
		}
		if (attributeType != null) {
			criteria.add(Restrictions.eq("attributeType", attributeType));
		}
		if (gene != null) {
			geneCriteria.add(Restrictions.eq("symbol", gene));
		}
		if (geneFamily != null) {
			geneCriteria.add(Restrictions.eq("idgFamily", geneFamily));
		}

		return (List<Attribute>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public static List<AttributeGroup> filterAttributeGroup(String dataset, String datasetGroup, String datasetType, String attribute, String attributeGroup, String attributeType, String gene,
			String geneFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(AttributeGroup.class).setProjection(Projections.distinct(Projections.property("name")));
		return (List<AttributeGroup>) criteria.list();
	}
}