package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class GeneSetDao {
	
	@SuppressWarnings("unchecked")
	public static List<GeneSet> getByWordInAttributeName(String query) {
		String sql = String.format("" +
			"SELECT DISTINCT * FROM GeneSet " +
			"WHERE MATCH(GeneSet.nameFromDataset) AGAINST('%s*' IN BOOLEAN MODE)",
			query
		);		
		return (List<GeneSet>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(GeneSet.class)
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public static List<GeneSet> getAllFromAttributeName(String attributeName) {
		return (List<GeneSet>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT geneSet FROM GeneSet AS geneSet " +
				"JOIN geneSet.attribute AS attribute " +
				"WHERE attribute.nameFromDataset = :attributeName"
			)
			.setString("attributeName", attributeName)
			.list();
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
	public static List<GeneSet> getByWordInAttributeNameButIgnoreExactMatches(String query, List<Integer> idsToIgnore) {
		StringBuilder builder  = new StringBuilder();
		Iterator<Integer> iter = idsToIgnore.iterator();
		builder.append("(");
		while (iter.hasNext()) {
			builder.append(iter.next());
	        if (iter.hasNext()) {
	        	builder.append(",");
	        }
		}
		builder.append(")");
		String sql = String.format("" +
			"SELECT DISTINCT * FROM gene_set " +
			"JOIN attribute ON gene_set.attribute_fk = attribute.id " +
			"WHERE MATCH(attribute.name_from_dataset) AGAINST('%s*' IN BOOLEAN MODE) AND gene_set.id NOT IN %s", 
			query, builder.toString()
		);
		return (List<GeneSet>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(GeneSet.class)
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
	private static List<GeneSet> getFromDatasetAndGeneAndValue(String datasetName, String geneSymbol, int thresholdValue) {
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