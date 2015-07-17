package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class SearchResults {

	private String query;
	private List<Dataset> datasets = new ArrayList<Dataset>();
	private List<Gene> genes = new ArrayList<Gene>();
	private List<Attribute> attributes = new ArrayList<Attribute>();
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
		Dataset exactDataset = DatasetDAO.getByName(query);
		if (exactDataset != null) {
			datasetIdToIgnore = exactDataset.getId();
			datasets.addAll(DatasetDAO.getByWordInNameButIgnoreExactMatch(query, datasetIdToIgnore));
			datasets.add(0, exactDataset);
		} else {
			datasets.addAll(DatasetDAO.getByWordInName(query));
		}
		datasets.addAll(DatasetDAO.getByWordInResourceName(query));
		if (datasets.size() == 0) {
			datasetSuggestions.addAll(DatasetDAO.getSuggestions(query));
		}
	}
	
	public void queryGenes() {
		List<String> geneSuggestions = suggestions.get("genes");
		int geneIdToIgnore;
		Gene exactGene = GeneDAO.getBySymbol(query);
		if (exactGene != null) {
			geneIdToIgnore = exactGene.getId();
			genes.addAll(GeneDAO.getByWordInSymbolButIgnoreExactMatch(query, geneIdToIgnore));
			genes.add(0, exactGene);
		} else {
			genes.addAll(GeneDAO.getByWordInSymbol(query));
		}
		if (genes.size() == 0) {
			geneSuggestions.addAll(GeneDAO.getSuggestions(query));
		}
	}

	public void queryAttributes() {
		List<String> attributeSuggestions = suggestions.get("attributes");
		int attributeIdToIgnore;
		Attribute exactAttribute = AttributeDAO.getByName(query);
		if (exactAttribute != null) {
			attributeIdToIgnore = exactAttribute.getId();
			attributes.addAll(AttributeDAO.getByWordInNameButIgnoreExactMatch(query, attributeIdToIgnore));
			attributes.add(0, exactAttribute);
		} else {
			attributes.addAll(AttributeDAO.getByWordInName(query));
		}
		if (attributes.size() == 0) {
			attributeSuggestions.addAll(AttributeDAO.getSuggestions(query));
		}
	}
	
	public String getQuery() {
		return query;
	}
	
	public List<Dataset> getDatasets() {
		return datasets;
	}

	public List<Gene> getGenes() {
		return genes;
	}

	public List<Attribute> getAttributes() {
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
