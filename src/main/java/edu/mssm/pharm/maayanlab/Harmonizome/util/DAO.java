package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
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
}