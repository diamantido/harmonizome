package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;

public class ProteinDAO {

	public static Protein getBySymbol(String symbol) {
		return GenericDAO.getFromField(Protein.class, "symbol", symbol);
	}
}