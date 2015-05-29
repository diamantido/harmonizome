package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Metric;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Synonym;
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
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Synonym.class).add(Restrictions.eq("symbol", symbol).ignoreCase());
		return ((Synonym) criteria.uniqueResult()).getGene();
	}

	public static List<Metric> getAllMetrics() {
		@SuppressWarnings("unchecked")
		List<Metric> metrics = (List<Metric>) HibernateUtil.getAll(Metric.class);
		return metrics;
	}
	
	public static Dataset getDatasetByName(String name) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(Dataset.class).add(Restrictions.eq("name", name).ignoreCase());
		return (Dataset) criteria.uniqueResult();		
	}
}
