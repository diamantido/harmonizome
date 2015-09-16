package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class GeneSetDao {

	public static List<GeneSet> getAll(int startAt) {
		return GenericDao.getAll(GeneSet.class, startAt);
	}

	public static GeneSet getFromAttributeAndDataset(String attributeName, String datasetName) {
		return (GeneSet) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT geneSet FROM GeneSet AS geneSet " +
				"JOIN geneSet.attribute AS attribute " +
				"JOIN geneSet.dataset AS dataset " +
				"WHERE attribute.nameFromDataset = :attributeName " +
				"AND dataset.name = :datasetName"
			)
			.setString("attributeName", attributeName)
			.setString("datasetName", datasetName)
			.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public static List<GeneSet> getFromDataset(String datasetName) {
		return (List<GeneSet>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT geneSet FROM GeneSet AS geneSet " +
				"JOIN geneSet.dataset AS dataset " +
				"WHERE dataset.name = :datasetName"
			)
			.setString("datasetName", datasetName)
			.list();
	}
	
	public static List<Pair<Dataset, Pair<List<GeneSet>, List<GeneSet>>>> getByDatasetsFromGene(String geneSymbol) {
		List<Dataset> datasetsByGene = DatasetDao.getByGene(geneSymbol);
		List<Pair<Dataset, Pair<List<GeneSet>, List<GeneSet>>>> geneSetsByDataset = new ArrayList<Pair<Dataset, Pair<List<GeneSet>, List<GeneSet>>>>();
		for (Dataset dataset : datasetsByGene) {			
			String datasetName = dataset.getName();
			List<GeneSet> pos = getFromDatasetAndGeneAndValue(datasetName, geneSymbol, 1);
			List<GeneSet> neg = getFromDatasetAndGeneAndValue(datasetName, geneSymbol, -1);
			Pair<List<GeneSet>, List<GeneSet>> attributes = new ImmutablePair<List<GeneSet>, List<GeneSet>>(pos, neg);
			Pair<Dataset, Pair<List<GeneSet>, List<GeneSet>>> pair = new ImmutablePair<Dataset, Pair<List<GeneSet>, List<GeneSet>>>(dataset, attributes); 
			geneSetsByDataset.add(pair);
		}
		return geneSetsByDataset;
	}
	
	@SuppressWarnings("unchecked")
	public static List<GeneSet> getFromDatasetAndGeneAndValue(String datasetName, String geneSymbol, int thresholdValue) {
		return (List<GeneSet>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT geneSet FROM GeneSet AS geneSet " +
				"JOIN geneSet.features AS feats " +
				"JOIN geneSet.dataset AS dataset " +
				"JOIN feats.gene AS gene " +
				"WHERE dataset.name = :datasetName AND gene.symbol = :geneSymbol AND feats.thresholdValue = :thresholdValue"
			)
			.setString("datasetName", datasetName)
			.setString("geneSymbol", geneSymbol)
			.setInteger("thresholdValue", thresholdValue)
			.list();
	}
}