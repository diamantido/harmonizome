package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class DatasetDao {

	@SuppressWarnings("unchecked")
	public static List<Dataset> getFromGene(String geneSymbol) {
		return (List<Dataset>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT DISTINCT dataset FROM Dataset AS dataset " +
				"  JOIN dataset.geneSets AS geneSets " +
				"  JOIN geneSets.features as feats " +
				"  JOIN feats.gene AS gene " +
				"WHERE gene.symbol = :symbol"
			)
			.setString("symbol", geneSymbol)
			.list();
	}
	
	public static Long getCountGeneAttributeAssocations(String datasetName) {
		return (Long) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT COUNT(feat) FROM Feature AS feat " +
				"  JOIN feat.geneSet AS geneSet " +
				"  JOIN geneSet.dataset AS dataset " +
				"WHERE dataset.name = :datasetName"
			)
			.setString("datasetName", datasetName)
			.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public static List<Dataset> getFromResourceName(String query) {
		return (List<Dataset>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(
				"SELECT * FROM dataset " +
				"  JOIN resource ON dataset.resource_fk = resource.id " +
				"WHERE resource.name = :query"
			)
			.addEntity(Dataset.class)
			.setString("query", query)
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Dataset> getFromWordInResourceName(String query) {
		return (List<Dataset>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(
				"SELECT * FROM dataset " +
				"  JOIN resource ON dataset.resource_fk = resource.id " +
				"WHERE MATCH(resource.name) AGAINST(:query IN BOOLEAN MODE)"
			)
			.addEntity(Dataset.class)
			.setString("query", query + "*")
			.list();
	}

	public static Long getCountByResource(String resourceName) {
		return (Long) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT COUNT(dataset) FROM Dataset AS dataset " +
				"  JOIN dataset.resource AS resource " +
				"WHERE resource.name = :resourceName"
			)
			.setString("resourceName", resourceName)
			.uniqueResult();
	}
}
