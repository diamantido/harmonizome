package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class GeneDAO implements DAOInterface<Gene> {

	@SuppressWarnings("unchecked")
	public static List<Gene> getAll() {
		return (List<Gene>) HibernateUtil.getAll(Gene.class);
	}
	
	public List<Gene> getAll(int startAt) {
		return GenericDAO.getAll(Gene.class, startAt);
	}

	public List<Gene> getAll(String query, int startAt) {
		return GenericDAO.getAllFromQuery(Gene.class, "gene", "symbol", query, startAt);
	}

	public static Pair<List<Gene>, List<Gene>> getFromAttributeByValue(String attributeName, String datasetName) {
		List<Gene> pos = getByValue(attributeName, datasetName, 1);
		List<Gene> neg = getByValue(attributeName, datasetName, -1);
		return new ImmutablePair<List<Gene>, List<Gene>>(pos, neg);
	}
	
	public static List<Gene> getByWordInSymbol(String query) {
		return GenericDAO.getBySubstringInField(Gene.class, "gene", "symbol", query);
	}

	public static List<Gene> getByWordInSymbolButIgnoreExactMatch(String query, int idToIgnore) {
		return GenericDAO.getBySubstringInFieldButIgnoreId(Gene.class, "gene", "symbol", query, idToIgnore);
	}
	
	public List<String> getSuggestions(String query) {
		return GenericDAO.getSuggestions("gene", "symbol", query);
	}
	
	public List<String> getByPrefix(String query) {
		return GenericDAO.getByPrefix("gene", "symbol", query);		
	}

	@SuppressWarnings("unchecked")
	public static List<String> getSymbols() {
		return (List<String>) HibernateUtil
			.getCurrentSession()
			.createQuery("SELECT gene.symbol FROM Gene AS gene")
			.list();
	}

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
	
	public static Long getCountByDataset(String datasetName) {
		return (Long) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT COUNT (DISTINCT gene) FROM Gene AS gene " +
				"JOIN gene.features AS feats " +
				"JOIN feats.attribute AS attr " +
				"JOIN attr.dataset AS dataset " +
				"WHERE dataset.name = :datasetName"
			)
			.setString("datasetName", datasetName)
			.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public static List<Gene> getFromDatasetAndAttributeAndValue(String datasetName, String attributeName, int thresholdValue) {
		return (List<Gene>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT gene FROM Gene AS gene " +
				"JOIN gene.features AS feats " +
				"JOIN feats.attribute AS attr " +
				"JOIN attr.dataset AS dataset " +
				"WHERE dataset.name = :datasetName AND attr.nameFromDataset = :attributeName AND feats.thresholdValue = :thresholdValue"
			)
			.setString("datasetName", datasetName)
			.setString("attributeName", attributeName)
			.setInteger("thresholdValue", thresholdValue)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Gene> getFromDataset(String datasetName) {
		return (List<Gene>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT gene FROM Gene AS gene " +
				"JOIN gene.features AS feats " +
				"JOIN feats.dataset AS dataset " +
				"WHERE dataset.name = :datasetName"
			)
			.setString("datasetName", datasetName)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Gene> getByAttributeAndDatasetAndValue(String attributeName, String datasetName, int thresholdValue) {
		return (List<Gene>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT gene FROM Gene AS gene " +
				"JOIN gene.features AS features " +
				"JOIN features.attribute AS attribute " +
				"JOIN features.dataset AS dataset " +
				"WHERE attribute.name = :attributeName AND dataset.name = :datasetName AND features.thresholdValue = :thresholdValue"
			)
			.setString("datasetName", datasetName)
			.setString("attributeName", attributeName)
			.setInteger("thresholdValue", thresholdValue)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Gene> getByValue(String attributeName, String datasetName, int thresholdValue) {
		return (List<Gene>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT DISTINCT gene FROM Gene AS gene " +
				"JOIN gene.features AS feats " +
				"JOIN feats.attribute AS attr " +
				"JOIN attr.dataset AS dataset " +
				"WHERE attr.nameFromDataset = :attributeName AND dataset.name = :datasetName AND feats.thresholdValue = :thresholdValue"
			)
			.setString("attributeName", attributeName)
			.setString("datasetName", datasetName)
			.setInteger("thresholdValue", thresholdValue)
			.list();
	}
}
