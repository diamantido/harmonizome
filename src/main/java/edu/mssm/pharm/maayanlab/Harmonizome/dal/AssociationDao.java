package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class AssociationDao {

	public static Pair<List<Association>, List<Association>> getFromGeneAndDataset(String geneSymbol, String datasetName) {
		List<Association> pos = getFromDatasetAndGeneAndValue(datasetName, geneSymbol, 1);
		List<Association> neg = getFromDatasetAndGeneAndValue(datasetName, geneSymbol, -1);
		return new ImmutablePair<List<Association>, List<Association>>(pos, neg);
	}

	@SuppressWarnings("unchecked")
	private static List<Association> getFromDatasetAndGeneAndValue(String datasetName, String geneSymbol, int thresholdValue) {
		return (List<Association>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT association FROM Association AS association " +
				"  JOIN association.geneSet AS geneSet " +
				"  JOIN geneSet.dataset AS dataset " +
				"  JOIN association.gene AS gene " +
				"WHERE dataset.name = :datasetName " +
				"  AND gene.symbol = :geneSymbol " +
				"  AND association.thresholdValue = :thresholdValue"
			)
			.setString("datasetName", datasetName)
			.setString("geneSymbol", geneSymbol)
			.setInteger("thresholdValue", thresholdValue)
			.list();
	}
	
	public static Pair<List<Association>, List<Association>> getFromGeneSetByValue(String attributeName, String datasetName) {
		List<Association> pos = getByValue(attributeName, datasetName, 1);
		List<Association> neg = getByValue(attributeName, datasetName, -1);
		return new ImmutablePair<List<Association>, List<Association>>(pos, neg);
	}

	@SuppressWarnings("unchecked")
	private static List<Association> getByValue(String geneSetName, String datasetName, int thresholdValue) {
		String order = null;
		String optJoin = null;
		Dataset dataset = GenericDao.get(Dataset.class, datasetName);
		if (dataset.getIsContinuousValued()) {
			optJoin = "";
			if (thresholdValue == 1) {
				order = "ORDER BY association.standardizedValue DESC";
			} else {
				order = "ORDER BY association.standardizedValue ASC";
			}
		} else {
			optJoin = "  JOIN association.gene AS gene ";
			order = "ORDER BY gene.symbol";
		}
		return (List<Association>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT DISTINCT association FROM Association AS association " +
				"  JOIN association.geneSet AS geneSet " +
				"  JOIN geneSet.dataset AS dataset " +
				optJoin +
				"WHERE geneSet.nameFromDataset = :geneSetName " +
				"  AND dataset.name = :datasetName " +
				"  AND association.thresholdValue = :thresholdValue " +
				order
			)
			.setString("geneSetName", geneSetName)
			.setString("datasetName", datasetName)
			.setInteger("thresholdValue", thresholdValue)
			.list();
	}
}