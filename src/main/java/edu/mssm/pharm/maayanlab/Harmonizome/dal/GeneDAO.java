package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class GeneDAO {

	@SuppressWarnings("unchecked")
	public static List<String> getSymbols() {
		return (List<String>) HibernateUtil
			.getCurrentSession()
			.createQuery("SELECT gene.symbol FROM Gene AS gene")
			.list();
	}

	public static Gene getBySymbol(String symbol) {
		Criteria criteria = HibernateUtil.getCurrentSession()
			.createCriteria(Gene.class)
			.add(Restrictions.eq("symbol", symbol).ignoreCase());
		return (Gene) criteria.uniqueResult();
	}
	
	public static Gene getBySynonymSymbol(String symbol) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(GeneSynonym.class).add(Restrictions.eq("symbol", symbol).ignoreCase());
		GeneSynonym geneSynonym = (GeneSynonym) criteria.uniqueResult();
		return (geneSynonym == null ? null : geneSynonym.getGene());
	}

	@SuppressWarnings("unchecked")
	public static List<Gene> getFromDatasetAndAttributeAndValue(String datasetName, String attributeName, int thresholdValue) {
		return (List<Gene>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT gene FROM Gene AS gene " +
				"JOIN gene.features AS feats " +
				"JOIN feats.dataset AS dataset " +
				"JOIN feats.attribute AS attr " +
				"WHERE dataset.name = :datasetName AND attr.name = :attributeName AND feats.thresholdValue = :thresholdValue"
			)
			.setString("datasetName", datasetName)
			.setString("attributeName", attributeName)
			.setInteger("thresholdValue", thresholdValue)
			.list();
	}
	
	public static List<Pair<Dataset, Pair<List<Gene>, List<Gene>>>> getByDatasetsFromAttribute(String attributeName) {
		List<Dataset> datasetsByAttribute = DatasetDAO.getByAttribute(attributeName);
		List<Pair<Dataset, Pair<List<Gene>, List<Gene>>>> genesByDatasets = new ArrayList<Pair<Dataset, Pair<List<Gene>, List<Gene>>>>();
		for (Dataset dataset : datasetsByAttribute) {
			String datasetName = dataset.getName();
			List<Gene> pos = getFromDatasetAndAttributeAndValue(datasetName, attributeName, 1);
			List<Gene> neg = getFromDatasetAndAttributeAndValue(datasetName, attributeName, -1);
			Pair<List<Gene>, List<Gene>> attributes = new ImmutablePair<List<Gene>, List<Gene>>(pos, neg);
			Pair<Dataset, Pair<List<Gene>, List<Gene>>> pair = new ImmutablePair<Dataset, Pair<List<Gene>, List<Gene>>>(dataset, attributes); 
			genesByDatasets.add(pair);
		}
		return genesByDatasets;
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

	public static List<Gene> getByWordInSymbol(String query) {
		return GenericDAO.getBySubstringInField(Gene.class, "gene", "symbol", query);
	}

	public static List<Gene> getByWordInSymbolButIgnoreExactMatch(String query, int idToIgnore) {
		return GenericDAO.getBySubstringInFieldButIgnoreId(Gene.class, "gene", "symbol", query, idToIgnore);
	}
	
	public static List<String> getSuggestions(String query) {
		return GenericDAO.getSuggestions("gene", "symbol", query);
	}
}
