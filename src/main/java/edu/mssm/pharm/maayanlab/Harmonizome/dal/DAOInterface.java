package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

public interface DAOInterface<E> {

	public List<E> getAll(int startAt);

	public List<E> getAll(String query, int startAt);

	public List<String> getSuggestions(String query);
	
	public List<String> getByPrefix(String query);
}
