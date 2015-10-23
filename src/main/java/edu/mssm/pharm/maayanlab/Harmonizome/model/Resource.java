package edu.mssm.pharm.maayanlab.Harmonizome.model;

import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "resource")
@BioEntityMetadata(name = "resource", keyColumn = "name", jsp = "resource.jsp")
public class Resource implements BioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "acronym")
	private String acronym;

	@Column(name = "short_description")
	@Type(type = "text")
	private String shortDescription;

	@Column(name = "long_description")
	@Type(type = "text")
	private String longDescription;

	@Column(name = "url", length = 2083)
	private String url;

	@Column(name = "num_attributes")
	private Long numGeneSets;

    @Column(name = "num_datasets")
    private Long numDatasets;

	@Column(name = "image")
	private String image;
	
	/* Foreign key relationships
	 * ------------------------- */
	@OneToMany(mappedBy = "resource")
	private List<Dataset> datasets;
	
	/* Utilities
	 * ---------
	 */
	public static final String ENDPOINT = "resource";

	public Resource() {
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}
	
	public Long getNumGeneSets() {
		return numGeneSets;
	}

	public void setNumGeneSets(Long numGeneSets) {
		this.numGeneSets = numGeneSets;
	}

    public Long getNumDatasets() {
        return numDatasets;
    }

    public void setNumDatasets(Long numDatasets) {
        this.numDatasets = numDatasets;
    }

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/* Utility functions
	 * ----------------- */
	@Transient
	public String getKey() {
		return "name";
	}
	
	@Transient
	public String getValue() {
		return name;
	}
	
	@Transient
	public String getUrlEncodedValue() {
		return UrlCodec.encode(name);
	}

	@Transient
	public String getEndpoint() {
		return ENDPOINT;
	}
}
