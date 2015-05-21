package edu.mssm.pharm.maayanlab.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.hibernateObjects.DbGene;
import edu.mssm.pharm.maayanlab.Harmonizome.hibernateObjects.DbMetric;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class DAO {

	public static List<DbGene> getAllGenes() {
		@SuppressWarnings("unchecked")
		List<DbGene> genes = (List<DbGene>) HibernateUtil.getAll(DbGene.class);
		return genes;
	}

	public static DbGene getGeneByName(String name) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(DbGene.class).add(Restrictions.eq("name", name).ignoreCase());
		DbGene dbGene = (DbGene) criteria.uniqueResult();
		return dbGene;
	}

	public static List<DbMetric> getAllMetrics() {
		@SuppressWarnings("unchecked")
		List<DbMetric> metrics = (List<DbMetric>) HibernateUtil.getAll(DbMetric.class);
		return metrics;
	}
	
	public static DbMetric getMetricByResource(String resource) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(DbMetric.class).add(Restrictions.eq("resource", resource).ignoreCase());
		DbMetric dbMetric = (DbMetric) criteria.uniqueResult();
		return dbMetric;
	}
}
