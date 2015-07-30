package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class DatasetDAO {

	public static Long getCount() {
		return (Long) HibernateUtil
			.getCurrentSession()
			.createQuery("SELECT COUNT(*) FROM Dataset")
			.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public static List<Dataset> getDatasets() {
		List<Dataset> datasets = (List<Dataset>) HibernateUtil.getAll(Dataset.class);
		return datasets;
	}

	public static Dataset getByName(String name) {
		Criteria criteria = HibernateUtil.getCurrentSession()
			.createCriteria(Dataset.class)
			.add(Restrictions.eq("name", name).ignoreCase());
		return (Dataset) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public static List<Dataset> getByGene(String geneSymbol) {
		return (List<Dataset>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT DISTINCT dataset FROM Dataset AS dataset " +
				"JOIN dataset.attributes AS attrs " +
				"JOIN attrs.features as feats " +
				"JOIN feats.gene AS gene " +
				"WHERE gene.symbol = :symbol"
			)
			.setString("symbol", geneSymbol)
			.list();
	}

	public static Dataset getByAttribute(String attributeName) {
		return (Dataset) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT DISTINCT dataset FROM Dataset AS dataset " +
				"JOIN dataset.attributes AS attrs " +
				"WHERE attrs.nameFromDataset = :name"
			)
			.setString("name", attributeName)
			.uniqueResult();
	}
	
	public static Long getCountGeneAttributeAssocations(String datasetName) {
		return (Long) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT COUNT(feat) FROM Feature AS feat " +
				"JOIN feat.attribute AS attr " +
				"JOIN attr.dataset AS dataset " +
				"WHERE dataset.name = :datasetName"
			)
			.setString("datasetName", datasetName)
			.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Dataset> getByWordInResourceName(String query) {
		String sql = String.format("SELECT * FROM dataset JOIN resource ON dataset.resource_fk = resource.id WHERE MATCH(resource.name) AGAINST('%s*' IN BOOLEAN MODE)", query);		
		return (List<Dataset>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(Dataset.class)
			.list();
	}

	public static List<Dataset> getByWordInName(String query) {
		return GenericDAO.getBySubstringInField(Dataset.class, "dataset", "name", query);
	}

	public static List<Dataset> getByWordInNameButIgnoreExactMatch(String query, int idToIgnore) {
		return GenericDAO.getBySubstringInFieldButIgnoreId(Dataset.class, "dataset", "name", query, idToIgnore);
	}

	public static List<String> getSuggestions(String query) {
		return GenericDAO.getSuggestions("dataset", "name", query);
	}

	public static List<String> getByPrefix(String query) {
		return GenericDAO.getByPrefix("dataset", "name", query);		
	}
}
