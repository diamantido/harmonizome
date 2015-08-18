package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec;

@Entity
@Table(name = "naming_authority")
public class NamingAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "acronym")
	private String acronym;

	@Column(name = "description")
	@Type(type = "text")
	private String description;
	
	@Column(name = "url", length = 2083)
	private String url;
	
	/* Foreign Keys
	 * ------------ */	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publication_fk")
	private Publication publication;

	/* Back references
	 * --------------- */
	@OneToMany(mappedBy = "namingAuthority")
	private Set<Attribute> attributes;

	public NamingAuthority() {
	}

	/* Getters & Setters 
	 * ----------------- */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	/* Utility functions
	 * ----------------- */
	public String getUrlEncodedName() throws UnsupportedEncodingException {
		return UrlCodec.encode(name);
	}

	public String getEndpoint() {
		return "naming_authority";
	}
}
