package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec;

@Entity
@Table(name = "gene")
@BioEntityMetadata(name = "gene", keyColumn = "symbol", jsp = "gene.jsp")
public class Gene implements BioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "symbol", unique = true)
	private String symbol;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	@Type(type = "text")
	private String description;

	@Column(name = "ncbi_entrez_gene_id")
	private Integer ncbiEntrezGeneId;

	@Column(name = "ncbi_entrez_gene_url", length = 2083)
	private String ncbiEntrezGeneUrl;

	@Column(name = "num_page_views")
	private int numPageViews;

	/* Foreign key relationships
	 * ------------------------- */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idg_family_fk")
	private IdgFamily idgFamily;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idg_tdl_class_fk")
	private IdgTdlClass idgTdlClass;
	
	/* Back references
	 * --------------- */
	@OneToMany(mappedBy = "gene")
	private Set<GeneSynonym> synonyms;

	@OneToMany(mappedBy = "gene")
	private Set<Association> associations;

	@OneToMany(mappedBy = "gene")
	private Set<Protein> protein;
	
	@ManyToMany(mappedBy = "genes")
	private Set<HgncRootFamily> hgncRootFamilies;

	@ManyToMany(mappedBy = "genes")
	private Set<HgncTerminalFamily> hgncTerminalFamilies;

	/* Utilities
	 * --------- */
	public static final String ENDPOINT = "gene";
	
	public Gene() {
	}

	/* Getters & Setters 
	 * ----------------- */
	public int getId() {
		return id;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNcbiEntrezGeneId() {
		return ncbiEntrezGeneId;
	}

	public void setNcbiEntrezGeneId(int ncbiEntrezGeneId) {
		this.ncbiEntrezGeneId = ncbiEntrezGeneId;
	}

	public String getNcbiEntrezGeneUrl() {
		return ncbiEntrezGeneUrl;
	}

	public void setNcbiEntrezGeneUrl(String ncbiEntrezGeneUrl) {
		this.ncbiEntrezGeneUrl = ncbiEntrezGeneUrl;
	}

	public int getNumPageViews() {
		return numPageViews;
	}

	public void setNumPageViews(int numPageViews) {
		this.numPageViews = numPageViews;
	}

	public IdgFamily getIdgFamily() {
		return idgFamily;
	}

	public void setIdgFamily(IdgFamily idgFamily) {
		this.idgFamily = idgFamily;
	}

	public IdgTdlClass getIdgTdlClass() {
		return idgTdlClass;
	}

	public void setIdgTdlClass(IdgTdlClass idgTdlClass) {
		this.idgTdlClass = idgTdlClass;
	}

	public Set<GeneSynonym> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Set<GeneSynonym> synonyms) {
		this.synonyms = synonyms;
	}

	public Set<Association> getAssociations() {
		return associations;
	}

	public void setAssociations(Set<Association> associations) {
		this.associations = associations;
	}

	public Set<Protein> getProteins() {
		return protein;
	}

	public void setProteins(Set<Protein> protein) {
		this.protein = protein;
	}

	public Set<HgncRootFamily> getHgncRootFamilies() {
		return hgncRootFamilies;
	}

	public void setHgncRootFamilies(Set<HgncRootFamily> hgncRootFamilies) {
		this.hgncRootFamilies = hgncRootFamilies;
	}

	public Set<HgncTerminalFamily> getHgncTerminalFamilies() {
		return hgncTerminalFamilies;
	}

	public void setHgncTerminalFamilies(Set<HgncTerminalFamily> hgncTerminalFamilies) {
		this.hgncTerminalFamilies = hgncTerminalFamilies;
	}
	
	/* Utility functions
	 * ----------------- */
	@Transient
	public String getKey() {
		return "symbol";
	}
	
	@Transient
	public String getValue() {
		return symbol;
	}
	
	@Transient
	public String getUrlEncodedValue() {
		return UrlCodec.encode(symbol);
	}

	@Transient
	public String getEndpoint() {
		return ENDPOINT;
	}
}
