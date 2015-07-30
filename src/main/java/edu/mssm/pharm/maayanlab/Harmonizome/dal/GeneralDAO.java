package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Measurement;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class GeneralDAO {

	@SuppressWarnings("unchecked")
	public static List<Gene> getGenesByPrefix(String prefix) {
		System.out.println(prefix);
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Gene.class).add(Restrictions.like("symbol", prefix, MatchMode.END).ignoreCase());
		return (List<Gene>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Dataset> filterDataset(String dataset, String datasetGroup, String measurement, String attribute, String attributeGroup, String attributeType, String gene, String idgFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession()
				.createCriteria(Dataset.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		if (dataset != null && dataset != "") {
			criteria.add(Restrictions.eq("name", dataset));
		}
		if (datasetGroup != null && datasetGroup != "") {
			criteria.createCriteria("datasetGroup").add(Restrictions.eq("name", datasetGroup));
		}
		if (measurement != null && measurement != "") {
			criteria.createCriteria("measurement").add(Restrictions.eq("name", measurement));
		}
		if (attributeGroup != null && attributeGroup != "") {
			criteria.createCriteria("attributeGroup").add(Restrictions.eq("name", attributeGroup));
		}
		if (attributeType != null && attributeType != "") {
			criteria.createCriteria("attributeType").add(Restrictions.eq("name", attributeType));
		}

		return (List<Dataset>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public static List<DatasetGroup> filterDatasetGroup(String dataset, String datasetGroup, String measurement, String attribute, String attributeGroup, String attributeType, String gene,
			String idgFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession()
				.createCriteria(DatasetGroup.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		boolean datasetCriteriaCreated = false;
		if (dataset != null && dataset != "") {
			datasetCriteriaCreated = true;
			criteria.createAlias("datasets", "ds").add(Restrictions.eq("ds.name", dataset));
		}
		if (datasetGroup != null && datasetGroup != "") {
			criteria.add(Restrictions.eq("name", datasetGroup));
		}
		if (measurement != null && measurement != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.measurement", "dst").add(Restrictions.eq("dst.name", measurement));
		}
		if (attributeGroup != null && attributeGroup != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeGroup", "dsg").add(Restrictions.eq("dsg.name", attributeGroup));
		}
		if (attributeType != null && attributeType != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeType", "dsa").add(Restrictions.eq("dsa.name", attributeType));
		}

		return (List<DatasetGroup>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Measurement> filterMeasurement(String dataset, String datasetGroup, String measurement, String attribute, String attributeGroup, String attributeType, String gene,
			String idgFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession()
				.createCriteria(Measurement.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		boolean datasetCriteriaCreated = false;
		if (dataset != null && dataset != "") {
			datasetCriteriaCreated = true;
			criteria.createAlias("datasets", "ds").add(Restrictions.eq("ds.name", dataset));
		}
		if (datasetGroup != null && datasetGroup != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.datasetGroup", "dsg").add(Restrictions.eq("dsg.name", datasetGroup));
		}
		if (measurement != null && measurement != "") {
			criteria.add(Restrictions.eq("name", measurement));
		}
		if (attributeGroup != null && attributeGroup != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeGroup", "dsag").add(Restrictions.eq("dsag.name", attributeGroup));
		}
		if (attributeType != null && attributeType != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeType", "dsat").add(Restrictions.eq("dsat.name", attributeType));
		}

		return (List<Measurement>) criteria.list();
	}

	/*@SuppressWarnings("unchecked")
	public static List<Attribute> filterAttribute(String dataset, String datasetGroup, String measurement, String attribute, String attributeGroup, String attributeType, String gene, String idgFamily) {
		Criteria criteria = HibernateUtil.getCurrentSession()
			.createCriteria(Attribute.class)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Attribute>) criteria.list();
	}*/

	@SuppressWarnings("unchecked")
	public static List<AttributeGroup> filterAttributeGroup(String dataset, String datasetGroup, String measurement, String attribute, String attributeGroup, String attributeType, String gene,
			String idgFamily) {

		Criteria criteria = HibernateUtil.getCurrentSession()
				.createCriteria(AttributeGroup.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		boolean datasetCriteriaCreated = false;
		if (dataset != null && dataset != "") {
			datasetCriteriaCreated = true;
			criteria.createAlias("datasets", "ds").add(Restrictions.eq("ds.name", dataset));
		}
		if (datasetGroup != null && datasetGroup != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.datasetGroup", "dsg").add(Restrictions.eq("dsg.name", datasetGroup));
		}
		if (measurement != null && measurement != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.measurement", "dst").add(Restrictions.eq("dst.name", measurement));
		}
		if (attributeGroup != null && attributeGroup != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeGroup", "dsag").add(Restrictions.eq("dsag.name", attributeGroup));
		}
		if (attributeType != null && attributeType != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeType", "dsat").add(Restrictions.eq("dsat.name", attributeType));
		}
		
		return (List<AttributeGroup>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public static List<AttributeType> filterAttributeTypes(String dataset, String datasetGroup, String measurement, String attribute, String attributeGroup, String attributeType, String gene, String idgFamily) {
		
		Criteria criteria = HibernateUtil.getCurrentSession()
				.createCriteria(AttributeType.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		boolean datasetCriteriaCreated = false;
		if (dataset != null) {
			datasetCriteriaCreated = true;
			criteria.createAlias("datasets", "ds").add(Restrictions.eq("ds.name", dataset));
		}
		if (datasetGroup != null && datasetGroup != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.datasetGroup", "dsg").add(Restrictions.eq("dsg.name", datasetGroup));
		}
		if (measurement != null && measurement != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.measurement", "dst").add(Restrictions.eq("dst.name", measurement));
		}
		if (attributeGroup != null && attributeGroup != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeGroup", "dsag").add(Restrictions.eq("dsag.name", attributeGroup));
		}
		if (attributeType != null && attributeType != "") {
			if (!datasetCriteriaCreated) {
				datasetCriteriaCreated = true;
				criteria.createAlias("datasets", "ds");
			}
			criteria.createAlias("ds.attributeType", "dsat").add(Restrictions.eq("dsat.name", attributeType));
		}
		
		return (List<AttributeType>) criteria.list();
	}

	public static Attribute getAttributeByDatasetAndGene(String dataset, String gene) {
		return null;
	}
}