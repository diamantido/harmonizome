package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class AttributeDao {

	@SuppressWarnings("unchecked")
	public static List<AttributeGroup> getAttributeGroups() {
		return (List<AttributeGroup>) HibernateUtil
			.getCurrentSession()
			.createQuery("FROM AttributeGroup")
			.list();
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
	private static List<AttributeGroup> getAttributeGroupsFromGene(String geneSymbol) {
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
}