package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec;

@Entity
@Table(name = "hgnc_root_family")
public class HgncRootFamily {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "family_id", unique = true)
	private int familyId;

	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "url", length = 2083)
	private String url;

	/* Foreign key relationships
	 * ------------------------- */
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "hgnc_root_families_to_genes", joinColumns = { @JoinColumn(name = "hgnc_root_family_fk") }, inverseJoinColumns = { @JoinColumn(name = "gene_fk") })
	private Set<Gene> genes;

	/* Getters & Setters 
	 * ----------------- */
	@Transient
	public static final String ENDPOINT = "gene_family";
	
	public HgncRootFamily() {
	}

	/* Getters & Setters 
	 * ----------------- */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Gene> getGenes() {
		return genes;
	}

	public void setGenes(Set<Gene> genes) {
		this.genes = genes;
	}
	
	/* Utility functions
	 * ----------------- */
	@Transient
	public String getUrlEncodedName() throws UnsupportedEncodingException {
		return UrlCodec.encode(name);
	}

	@Transient
	public String getEndpoint() {
		return ENDPOINT;
	}
}