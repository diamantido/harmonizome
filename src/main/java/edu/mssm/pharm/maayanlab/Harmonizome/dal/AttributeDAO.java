package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class AttributeDAO {
	
	@SuppressWarnings("unchecked")
	public static List<AttributeGroup> getAttributeGroups() {
		return (List<AttributeGroup>) HibernateUtil
			.getCurrentSession()
			.createQuery("FROM AttributeGroup")
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public static List<AttributeGroup> getAttributeGroupsFromGene(String geneSymbol) {
		return (List<AttributeGroup>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT DISTINCT attrGroup FROM AttributeGroup AS attrGroup " +
				"JOIN attrGroup.attributes AS attrs " +
				"JOIN attrs.features AS feats " +
				"JOIN feats.gene AS gene " +
				"WHERE gene.symbol = :symbol"
			)
			.setString("symbol", geneSymbol)
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Attribute> getByName(String name) {
		return (List<Attribute>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT attr FROM Attribute AS attr " +
				"WHERE attr.nameFromDataset = :name"
			)
			.setString("name", name)
			.list();
	}
	
	public static Attribute getByNameAndDataset(String attributeName, String datasetName) {
		return (Attribute) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT attr FROM Attribute AS attr " +
				"JOIN attr.dataset AS dataset " +
				"WHERE attr.nameFromDataset = :attributeName AND dataset.name = :datasetName"
			)
			.setString("attributeName", attributeName)
			.setString("datasetName", datasetName)
			.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public static List<AttributeType> getTypesFromGroup(String attributeGroup) {
		return (List<AttributeType>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT attrType FROM AttributeType AS attrType " +
				"WHERE attrType.attributeGroup.name = :attributeGroup"		
			)
			.setString("attributeGroup", attributeGroup)
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Attribute> getFromTypeAndGene(String attributeType, String geneSymbol) {
		return (List<Attribute>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT attr FROM Attribute AS attr " +
				"JOIN attr.attributeType AS attrType " +
				"JOIN attr.features AS feats " +
				"JOIN feats.gene AS gene " +
				"WHERE attrType.name = :name " +
				"AND gene.symbol = :symbol"
			)
			.setString("name", attributeType)
			.setString("symbol", geneSymbol)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Attribute> getFromGene(String geneSymbol) {
		return (List<Attribute>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT attr FROM Attribute AS attr " +
				"JOIN attr.features AS feats " +
				"JOIN feats.gene AS gene " +
				"WHERE gene.symbol = :symbol"
			)
			.setString("symbol", geneSymbol)
			.list();
	}

	public static Map<String, Map<String, List<Attribute>>> getByGroupAndTypeFromGene(String geneSymbol) {
		Map<String, Map<String, List<Attribute>>> organizedAttributes = new HashMap<String, Map<String, List<Attribute>>>();
		List<AttributeGroup> attributeGroups = getAttributeGroupsFromGene(geneSymbol);
		for (AttributeGroup ag : attributeGroups) {
			String agName = ag.getName();
			List<AttributeType> attributeTypes = getTypesFromGroup(agName);
			Map<String, List<Attribute>> attributesByType = new HashMap<String, List<Attribute>>();
			for (AttributeType at : attributeTypes) {
				String atName = at.getName();
				List<Attribute> attributes = getFromTypeAndGene(atName, geneSymbol);
				attributesByType.put(atName, attributes);
			}
			organizedAttributes.put(agName, attributesByType);
		}
		return organizedAttributes;
	}

	@SuppressWarnings("unchecked")
	public static List<Attribute> getFromDatasetAndGene(String datasetName, String geneSymbol) {
		return (List<Attribute>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT attr FROM Attribute AS attr " +
				"JOIN attr.features AS feats " +
				"JOIN attr.dataset AS dataset " +
				"JOIN feats.gene AS gene " +
				"WHERE dataset.name = :datasetName AND gene.symbol = :geneSymbol AND feats.thresholdValue != 0"
			)
			.setString("datasetName", datasetName)
			.setString("geneSymbol", geneSymbol)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Attribute> getFromDatasetAndGeneAndValue(String datasetName, String geneSymbol, int thresholdValue) {
		return (List<Attribute>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT attr FROM Attribute AS attr " +
				"JOIN attr.features AS feats " +
				"JOIN attr.dataset AS dataset " +
				"JOIN feats.gene AS gene " +
				"WHERE dataset.name = :datasetName AND gene.symbol = :geneSymbol AND feats.thresholdValue = :thresholdValue"
			)
			.setString("datasetName", datasetName)
			.setString("geneSymbol", geneSymbol)
			.setInteger("thresholdValue", thresholdValue)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static List<Attribute> getFromDataset(String datasetName) {
		return (List<Attribute>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT attr FROM Attribute AS attr " +
				"JOIN attr.dataset AS dataset " +
				"WHERE dataset.name = :datasetName"
			)
			.setString("datasetName", datasetName)
			.list();
	}
	
	public static List<Pair<Dataset, Pair<List<Attribute>, List<Attribute>>>> getByDatasetsFromGene(String geneSymbol) {
		List<Dataset> datasetsByGene = DatasetDAO.getByGene(geneSymbol);
		List<Pair<Dataset, Pair<List<Attribute>, List<Attribute>>>> attributesByDatasets = new ArrayList<Pair<Dataset, Pair<List<Attribute>, List<Attribute>>>>();
		for (Dataset dataset : datasetsByGene) {
			String datasetName = dataset.getName();
			List<Attribute> pos = getFromDatasetAndGeneAndValue(datasetName, geneSymbol, 1);
			List<Attribute> neg = getFromDatasetAndGeneAndValue(datasetName, geneSymbol, -1);
			Pair<List<Attribute>, List<Attribute>> attributes = new ImmutablePair<List<Attribute>, List<Attribute>>(pos, neg);
			Pair<Dataset, Pair<List<Attribute>, List<Attribute>>> pair = new ImmutablePair<Dataset, Pair<List<Attribute>, List<Attribute>>>(dataset, attributes); 
			attributesByDatasets.add(pair);
		}
		return attributesByDatasets;
	}

	public static List<Attribute> getByWordInName(String query) {
		return GenericDAO.getBySubstringInField(Attribute.class, "attribute", "name_from_dataset", query);
	}

	public static List<Attribute> getByWordInNameButIgnoreExactMatches(String query, List<Integer> idToIgnore) {
		return GenericDAO.getBySubstringInFieldButIgnoreIds(Attribute.class, "attribute", "name_from_dataset", query, idToIgnore);
	}

	public static List<String> getSuggestions(String query) {
		return GenericDAO.getSuggestions("attribute", "name_from_dataset", query);
	}
}