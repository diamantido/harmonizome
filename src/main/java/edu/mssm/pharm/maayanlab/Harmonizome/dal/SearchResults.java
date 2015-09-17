package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;

import com.google.gson.annotations.Expose;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSet;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

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

		try {
			HibernateUtil.beginTransaction();
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
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}
	
	private void queryDatasets() {
		List<String> datasetSuggestions = suggestions.get("datasets");
		int datasetIdToIgnore;
		Dataset exactDataset = DatasetDao.getFromName(query);
		if (exactDataset != null) {
			datasetIdToIgnore = exactDataset.getId();
			datasets.add(exactDataset);
			datasets.addAll(DatasetDao.getByWordInNameButIgnoreExactMatch(query, datasetIdToIgnore));
		} else {
			datasets.addAll(DatasetDao.getByWordInName(query));
		}
		datasets.addAll(DatasetDao.getByWordInResourceName(query));
		if (datasets.size() == 0) {
			datasetSuggestions.addAll(DatasetDao.getSuggestions(query));
		}
	}
	
	public void queryGenes() {
		GeneDao geneDAO = new GeneDao();
		List<String> geneSuggestions = suggestions.get("genes");
		int geneIdToIgnore;
		Gene exactGene = GeneDao.getFromSymbol(query);
		if (exactGene != null) {
			geneIdToIgnore = exactGene.getId();
			genes.add(exactGene);
			genes.addAll(GeneDao.getByWordInSymbolButIgnoreExactMatch(query, geneIdToIgnore));
		} else {
			genes.addAll(GeneDao.getByWordInSymbol(query));
		}
		if (genes.size() == 0) {
			geneSuggestions.addAll(geneDAO.getSuggestions(query));
		}
	}

	public void queryGeneSets() {
		List<String> attributeSuggestions = suggestions.get("geneSets");
		List<GeneSet> exactGeneSets = GeneSetDao.getAllFromAttributeName(query);
		if (exactGeneSets.size() != 0) {
			List<Integer> idsToIgnore = new ArrayList<Integer>();
			for (GeneSet geneSet : exactGeneSets) {
				idsToIgnore.add(geneSet.getId());
			}
			geneSets.addAll(exactGeneSets);
			geneSets.addAll(GeneSetDao.getByWordInAttributeNameButIgnoreExactMatches(query, idsToIgnore));
		} else {
			geneSets.addAll(GeneSetDao.getByWordInAttributeName(query));
		}
		if (geneSets.size() == 0) {
			attributeSuggestions.addAll(AttributeDao.getSuggestions(query));
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

	/* Utility methods
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
		return suggestions.get("datasets").size() == 0 && suggestions.get("genes").size() == 0 && suggestions.get("attributes").size() == 0;
	}
	
}
