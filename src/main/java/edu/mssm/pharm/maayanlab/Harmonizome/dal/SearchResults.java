package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;

import com.google.gson.annotations.Expose;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class SearchResults {

	private String query;
	
	// We use linked hash sets so we can enforce uniqueness and order.
	@Expose
	private Set<Dataset> datasets = new LinkedHashSet<Dataset>();
	
	@Expose
	private Set<Gene> genes = new LinkedHashSet<Gene>();
	
	@Expose
	private Set<Attribute> attributes = new LinkedHashSet<Attribute>();
	
	private Map<String, List<String>> suggestions = new HashMap<String, List<String>>();
	
	public SearchResults(String query, String type) {
		this.query = query;
		
		suggestions.put("datasets", new ArrayList<String>());
		suggestions.put("genes", new ArrayList<String>());
		suggestions.put("attributes", new ArrayList<String>());

		try {
			HibernateUtil.beginTransaction();
			if (type == null) {
				queryDatasets();
				queryGenes();
				queryAttributes();
			} else if (type.equals("dataset")) {
				queryDatasets();
			} else if (type.equals("gene")) {
				queryGenes();
			} else if (type.equals("attribute")) {
				queryAttributes();
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

	public void queryAttributes() {
		List<String> attributeSuggestions = suggestions.get("attributes");
		List<Attribute> exactAttributes = AttributeDao.getByName(query);
		if (exactAttributes.size() != 0) {
			List<Integer> idsToIgnore = new ArrayList<Integer>();
			for (Attribute attr : exactAttributes) {
				idsToIgnore.add(attr.getId());
			}
			attributes.addAll(exactAttributes);
			attributes.addAll(AttributeDao.getByWordInNameButIgnoreExactMatches(query, idsToIgnore));
		} else {
			attributes.addAll(AttributeDao.getByWordInName(query));
		}
		if (attributes.size() == 0) {
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

	public Set<Attribute> getAttributes() {
		return attributes;
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
		return datasets.size() == 0 && genes.size() == 0 && attributes.size() == 0;
	}
	
	private boolean noSuggestionsFound() {
		return suggestions.get("datasets").size() == 0 && suggestions.get("genes").size() == 0 && suggestions.get("attributes").size() == 0;
	}
	
}
