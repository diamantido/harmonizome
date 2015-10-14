package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.annotations.Expose;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;

/* Represents the search results for a single user query. This object *must*
 * be wrapped in a Hibernate transaction like so:
 * 
 * try {
 *     HibernateUtil.beginTransaction();
 *     SearchResults searchResults = new SearchResults(query, type);
 *     
 *     // Do something with results here. Hibernate lazily loads the data,
 *     // so any data access must happen inside the transaction.
 *   
 *     HibernateUtil.commitTransaction();
 * } catch (HibernateException he) {
 *     he.printStackTrace();
 *     HibernateUtil.rollbackTransaction();
 * } finally {
 *     HibernateUtil.close();
 * }
 */
public class SearchResults {

	private String query;

	// We use linked hash sets so we can enforce uniqueness and order.
	@Expose
	private Set<Dataset> datasets = new LinkedHashSet<Dataset>();

	@Expose
	private Set<Gene> genes = new LinkedHashSet<Gene>();

	@Expose
	private Set<GeneSet> geneSets = new LinkedHashSet<GeneSet>();

	private Map<String, List<String>> suggestions = new HashMap<String, List<String>>();

	public SearchResults(String query, String type) {
		this.query = query;

		suggestions.put("datasets", new ArrayList<String>());
		suggestions.put("genes", new ArrayList<String>());
		suggestions.put("geneSets", new ArrayList<String>());

		if (type == null) {
			queryDatasets();
			queryGenes();
			queryGeneSets();
		} else if (type.equals("dataset")) {
			queryDatasets();
		} else if (type.equals("gene")) {
			queryGenes();
		} else if (type.equals("geneSet")) {
			queryGeneSets();
		}
	}

	private void queryDatasets() {
		List<String> datasetSuggestions = suggestions.get("datasets");
		int datasetIdToIgnore;
		Dataset exactDataset = GenericDao.get(Dataset.class, query);

		if (exactDataset != null) {
			datasetIdToIgnore = exactDataset.getId();
			datasets.add(exactDataset);
			datasets.addAll(GenericDao.getBySubstringButIgnoreId(Dataset.class, query, datasetIdToIgnore));
		} else {
			datasets.addAll(GenericDao.getBySubstring(Dataset.class, query));
			datasets.addAll(DatasetDao.getByResourceName(query));
			datasets.addAll(DatasetDao.getByWordInResourceName(query));
		}
		
		if (datasets.size() == 0) {
			datasetSuggestions.addAll(GenericDao.getSuggestions(Dataset.class, query));
		}
	}

	public void queryGenes() {
		List<String> geneSuggestions = suggestions.get("genes");
		int geneIdToIgnore;
		Gene exactGene = GeneDao.getFromSymbol(query);
		if (exactGene != null) {
			geneIdToIgnore = exactGene.getId();
			genes.add(exactGene);
			genes.addAll(GenericDao.getBySubstringButIgnoreId(Gene.class, query, geneIdToIgnore));
		} else {
			genes.addAll(GeneDao.getFromName(query));
			genes.addAll(GenericDao.getBySubstring(Gene.class, query));
		}
		if (genes.size() == 0) {
			geneSuggestions.addAll(GenericDao.getSuggestions(Gene.class, query));
		}
	}

	public void queryGeneSets() {
		List<String> getSetSuggestions = suggestions.get("geneSets");
		List<GeneSet> exactGeneSets = GeneSetDao.getAllFromDatasetName(query);
		if (exactGeneSets.size() != 0) {
			List<Integer> idsToIgnore = new ArrayList<Integer>();
			for (GeneSet geneSet : exactGeneSets) {
				idsToIgnore.add(geneSet.getId());
			}
			geneSets.addAll(exactGeneSets);
			geneSets.addAll(GeneSetDao.getByWordInAttributeNameButIgnoreExactMatches(query, idsToIgnore));
		} else {
			geneSets.addAll(GeneSetDao.getByWordInGeneSetName(query));
		}
		if (geneSets.size() == 0) {
			getSetSuggestions.addAll(GenericDao.getSuggestions(GeneSet.class, query));
		}
	}

	public String getQuery() {
		return query;
	}

	public Set<Dataset> getDatasets() {
		return datasets;
	}

	public Set<Gene> getGenes() {
		return genes;
	}

	public Set<GeneSet> getGeneSets() {
		return geneSets;
	}

	public Map<String, List<String>> getSuggestions() {
		return suggestions;
	}

	/*
	 * Utility methods
	 */
	public boolean noMatches() {
		return query == null || (noExactMatchesFound() && noSuggestionsFound());
	}

	public boolean onlySuggestions() {
		return noExactMatchesFound() && !noSuggestionsFound();
	}

	private boolean noExactMatchesFound() {
		return datasets.size() == 0 && genes.size() == 0 && geneSets.size() == 0;
	}

	private boolean noSuggestionsFound() {
		return suggestions.get("datasets").size() == 0 && suggestions.get("genes").size() == 0 && suggestions.get("geneSets").size() == 0;
	}

}