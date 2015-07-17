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
				"JOIN dataset.features AS feats " +
				"JOIN feats.gene AS gene " +
				"WHERE gene.symbol = :symbol"
			)
			.setString("symbol", geneSymbol)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Dataset> getByAttribute(String attributeName) {
		return (List<Dataset>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT DISTINCT dataset FROM Dataset AS dataset " +
				"JOIN dataset.features AS feats " +
				"JOIN feats.attribute AS attr " +
				"WHERE attr.name = :name"
			)
			.setString("name", attributeName)
			.list();
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
}
