package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;

public class ProteinDAO {

	public static List<Protein> getAll(String query, int startAt) {
		return GenericDAO.getAllFromQuery(Protein.class, "symbol", query, startAt);
	}
	
	public static Protein getBySymbol(String symbol) {
		return GenericDAO.getFromField(Protein.class, "symbol", symbol);
	}
}