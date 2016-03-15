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

	public static Gene getFromSynonymSymbol(String symbol) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(GeneSynonym.class).add(Restrictions.eq("symbol", symbol).ignoreCase());
		GeneSynonym geneSynonym = (GeneSynonym) criteria.uniqueResult();
		return (geneSynonym == null ? null : geneSynonym.getGene());
	}
	
	public static Pair<List<Gene>, List<Gene>> getFromGeneSetByValue(String attributeName, String datasetName) {
		List<Gene> pos = getByValue(attributeName, datasetName, 1);
		List<Gene> neg = getByValue(attributeName, datasetName, -1);
		return new ImmutablePair<List<Gene>, List<Gene>>(pos, neg);
	}

	public static Long getCountByDataset(String datasetName) {
		return (Long) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT COUNT (DISTINCT gene) FROM Gene AS gene " +
				"  JOIN gene.associations AS associations " +
				"  JOIN associations.geneSet AS geneSet " +
				"  JOIN geneSet.dataset AS dataset " +
				"WHERE dataset.name = :datasetName"
			)
			.setString("datasetName", datasetName)
			.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	private static List<Gene> getByValue(String geneSetName, String datasetName, int thresholdValue) {
		return (List<Gene>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT DISTINCT gene FROM Gene AS gene " +
				"  JOIN gene.associations AS associations " +
				"  JOIN associations.geneSet AS geneSet " +
				"  JOIN geneSet.dataset AS dataset " +
				"WHERE geneSet.nameFromDataset = :geneSetName " +
				"  AND dataset.name = :datasetName " +
				"  AND associations.thresholdValue = :thresholdValue"
			)
			.setString("geneSetName", geneSetName)
			.setString("datasetName", datasetName)
			.setInteger("thresholdValue", thresholdValue)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Gene> getFromName(String query) {	
		return (List<Gene>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(
				"SELECT DISTINCT * FROM gene " +
				"WHERE MATCH(gene.name) AGAINST(:geneName IN BOOLEAN MODE)"
			)
			.addEntity(Gene.class)
			.setString("geneName", "%" + query + "with%")
			.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Object[]> getWithAssociationsByDatasetAndInputGenes(String datasetName, String[] genes) {
		String joinedGenes = "'" + String.join("','", genes) + "'";
		String sql = String.format("" +
			"SELECT gene_set.name_from_dataset, gene.symbol, association.standardized_value, association.threshold_value " +
			"FROM gene " +
			"  JOIN association ON association.gene_fk = gene.id " +
			"  JOIN gene_set ON gene_set.id = association.gene_set_fk " +
			"  JOIN dataset ON dataset.id = gene_set.dataset_fk " +
			"WHERE symbol IN (" + joinedGenes + ")" +
			"  AND dataset.name = '%s'", datasetName);
		return HibernateUtil.getCurrentSession().createSQLQuery(sql).list();
	}
}
