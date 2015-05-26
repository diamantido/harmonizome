package edu.mssm.pharm.maayanlab.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Metric;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class DAO {

	public static List<Gene> getGenes() {
		@SuppressWarnings("unchecked")
		List<Gene> genes = (List<Gene>) HibernateUtil.getAll(Gene.class);
		return genes;
	}

	public static List<Metric> getAllMetrics() {
		@SuppressWarnings("unchecked")
		List<Metric> metrics = (List<Metric>) HibernateUtil.getAll(Metric.class);
		return metrics;
	}

	public static Metric getMetricByResource(String resource) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Metric.class).add(Restrictions.eq("resource", resource).ignoreCase());
		Metric dbMetric = (Metric) criteria.uniqueResult();
		return dbMetric;
	}
}
