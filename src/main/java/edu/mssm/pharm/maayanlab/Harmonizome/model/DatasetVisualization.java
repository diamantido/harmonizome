package edu.mssm.pharm.maayanlab.Harmonizome.model;

import javax.persistence.*;

@Entity
@Table(name = "dataset_visualization")
public class DatasetVisualization extends DatasetVisualizationAbstract {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "type")
	private String type;

    @Column(name = "image")
    private String image;

    @Column(name = "clustergrammer_link")
    private String clustergrammerLink;

    /* Foreign key relationships
     * ------------------------- */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dataset_fk")
    private Dataset dataset;

    public DatasetVisualization() {
	}

	/* Getters & Setters 
	 * ----------------- */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getClustergrammerLink() {
        return clustergrammerLink;
    }

    public void setClustergrammerLink(String clustergrammerLink) {
        this.clustergrammerLink = clustergrammerLink;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }
}