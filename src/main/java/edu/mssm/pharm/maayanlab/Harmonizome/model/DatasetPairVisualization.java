package edu.mssm.pharm.maayanlab.Harmonizome.model;

import javax.persistence.*;

@Entity
@Table(name = "dataset_pair_visualization")
public class DatasetPairVisualization extends DatasetVisualizationAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "dataset_1_fk")
    private Dataset dataset1;

    @ManyToOne
    @JoinColumn(name = "dataset_2_fk")
    private Dataset dataset2;

    @Column(name = "image")
    private String image;

    @Column(name = "clustergrammer_link")
    private String clustergrammerLink;

    public DatasetPairVisualization() {
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dataset getDataset1() {
        return dataset1;
    }

    public void setDataset1(Dataset dataset1) {
        this.dataset1 = dataset1;
    }

    public Dataset getDataset2() {
        return dataset2;
    }

    public void setDataset2(Dataset dataset2) {
        this.dataset2 = dataset2;
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
}