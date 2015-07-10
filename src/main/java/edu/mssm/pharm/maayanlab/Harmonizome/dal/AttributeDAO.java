package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeGroup;
import edu.mssm.pharm.maayanlab.Harmonizome.model.AttributeType;
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
	public static List<AttributeGroup> getAttributeGroupsByGene(String geneSymbol) {
		/*
		SELECT DISTINCT(attribute_group.name) FROM `attribute_group`
		  JOIN attribute ON attribute.attribute_group_fk = attribute_group.id
		  JOIN feature ON feature.attribute_fk = attribute.id
		  JOIN gene ON feature.gene_fk = gene.id
		  WHERE gene.symbol = "HS6ST2";
		 */
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
	public static List<AttributeType> getAttributeTypesByGroup(String attributeGroup) {
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
	public static List<Attribute> getAttributesByTypeAndGene(String attributeType, String geneSymbol) {
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
	public static List<Attribute> getAttributesByGene(String symbol) {
		return (List<Attribute>) HibernateUtil
			.getCurrentSession()
			.createQuery(
				"SELECT attr FROM Attribute AS attr " +
				"JOIN attr.features AS feats " +
				"JOIN feats.gene AS gene " +
				"WHERE gene.symbol = :symbol"
			)
			.setString("symbol", symbol)
			.list();
	}

	public static Map<String, Map<String, List<Attribute>>> getAttributesByGroupAndTypeFromGene(String geneSymbol) {
		Map<String, Map<String, List<Attribute>>> organizedAttributes = new HashMap<String, Map<String, List<Attribute>>>();
		//List<AttributeGroup> attributeGroups = AttributeDAO.getAttributeGroups();
		List<AttributeGroup> attributeGroups = AttributeDAO.getAttributeGroupsByGene(geneSymbol);
		for (AttributeGroup ag : attributeGroups) {
			String agName = ag.getName();
			List<AttributeType> attributeTypes = AttributeDAO.getAttributeTypesByGroup(agName);
			Map<String, List<Attribute>> attributesByType = new HashMap<String, List<Attribute>>();
			for (AttributeType at : attributeTypes) {
				String atName = at.getName();
				List<Attribute> attributes = AttributeDAO.getAttributesByTypeAndGene(atName, geneSymbol);
				attributesByType.put(atName, attributes);
			}
			organizedAttributes.put(agName, attributesByType);
		}
		return organizedAttributes;
	}
}