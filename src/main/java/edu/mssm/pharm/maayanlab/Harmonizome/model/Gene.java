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

import org.hibernate.annotations.Type;

@Entity
@Table(name = "gene")
public class Gene {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idg_family_fk")
	private IdgFamily idgFamily;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idg_tdl_class_fk")
	private IdgTdlClass idgTdlClass;
	
	@OneToMany(mappedBy = "gene")
	private Set<GeneSynonym> synonyms;

	@OneToMany(mappedBy = "gene")
	private Set<Feature> features;

	@OneToMany(mappedBy = "gene")
	private Set<Protein> protein;

	@OneToMany(mappedBy = "gene1")
	private Set<GeneSimilarityMatrix> geneSimilarities1;

	@OneToMany(mappedBy = "gene2")
	private Set<GeneSimilarityMatrix> geneSimilarities2;

	@ManyToMany(mappedBy = "genes")
	private Set<HgncRootFamily> hgncRootFamilies;

	@ManyToMany(mappedBy = "genes")
	private Set<HgncTerminalFamily> hgncTerminalFamilies;
	
	public Gene() {
	}

	public Gene(String symbol, String name, String description, int ncbiEntrezGeneId, String ncbiEntrezGeneUrl, IdgFamily idgFamily, IdgTdlClass idgTdlClass, Set<GeneSynonym> synonyms, Set<Feature> features,
			Set<Protein> protein) {
		this.symbol = symbol;
		this.name = name;
		this.description = description;
		this.ncbiEntrezGeneId = ncbiEntrezGeneId;
		this.ncbiEntrezGeneUrl = ncbiEntrezGeneUrl;
		this.idgFamily = idgFamily;
		this.idgTdlClass = idgTdlClass;
		this.synonyms = synonyms;
		this.features = features;
		this.protein = protein;
	}

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

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public Set<Protein> getProteins() {
		return protein;
	}

	public void setProteins(Set<Protein> protein) {
		this.protein = protein;
	}

	public Set<GeneSimilarityMatrix> getGeneSimilarities1() {
		return geneSimilarities1;
	}

	public void setGeneSimilarities1(Set<GeneSimilarityMatrix> geneSimilarities1) {
		this.geneSimilarities1 = geneSimilarities1;
	}

	public Set<GeneSimilarityMatrix> getGeneSimilarities2() {
		return geneSimilarities2;
	}

	public void setGeneSimilarities2(Set<GeneSimilarityMatrix> geneSimilarities2) {
		this.geneSimilarities2 = geneSimilarities2;
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
}
