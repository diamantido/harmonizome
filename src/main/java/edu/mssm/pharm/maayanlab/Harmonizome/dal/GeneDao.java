package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class GeneDao {
	
	public static Gene getFromSymbol(String symbol) {
		Criteria criteria = HibernateUtil.getCurrentSession()
			.createCriteria(Gene.class)
			.add(Restrictions.eq("symbol", symbol).ignoreCase());
		return (Gene) criteria.uniqueResult();
	}
	
	public static Gene getFromSynonymSymbol(String symbol) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(GeneSynonym.class).add(Restrictions.eq("symbol", symbol).ignoreCase());
		GeneSynonym geneSynonym = (GeneSynonym) criteria.uniqueResult();
		return (geneSynonym == null ? null : geneSynonym.getGene());
	}
	
	public static Pair<List<Gene>, List<Gene>> getFromAttributeByValue(String attributeName, String datasetName) {
		List<Gene> pos = getByValue(attributeName, datasetName, 1);
		List<Gene> neg = getByValue(attributeName, datasetName, -1);
		return new ImmutablePair<List<Gene>, List<Gene>>(pos, neg);
	}

	public static Long getCountByDataset(String datasetName) {
		return (Long) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT COUNT (DISTINCT gene) FROM Gene AS gene " +
				"JOIN gene.features AS feats " +
				"JOIN feats.geneSet AS geneSet " +
				"JOIN geneSet.dataset AS dataset " +
				"WHERE dataset.name = :datasetName"
			)
			.setString("datasetName", datasetName)
			.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	private static List<Gene> getByValue(String attributeName, String datasetName, int thresholdValue) {
		return (List<Gene>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT DISTINCT gene FROM Gene AS gene " +
				"JOIN gene.features AS feats " +
				"JOIN feats.geneSet AS geneSet " +
				"JOIN geneSet.dataset AS dataset " +
				"JOIN geneSet.attribute AS attr " +
				"WHERE attr.nameFromDataset = :attributeName AND dataset.name = :datasetName AND feats.thresholdValue = :thresholdValue"
			)
			.setString("attributeName", attributeName)
			.setString("datasetName", datasetName)
			.setInteger("thresholdValue", thresholdValue)
			.list();
	}
}
