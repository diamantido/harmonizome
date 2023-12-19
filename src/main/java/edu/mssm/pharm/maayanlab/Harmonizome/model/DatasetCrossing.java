package edu.mssm.pharm.maayanlab.Harmonizome.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "dataset_crossing")
public class DatasetCrossing {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gene_set_1_fk")
    private GeneSet geneSet1;

    @Column(name = "gene_set_1_dir")
    private Integer geneSet1Dir;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gene_set_2_fk")
    private GeneSet geneSet2;

    @Column(name = "gene_set_2_dir")
    private Integer geneSet2Dir;

    @Column(name = "p_value")
    private Double pValue;

    @Column(name = "jaccard_index")
    private Double jaccardIndex;

    @Column(name = "overlapping_genes")
    private Integer overlappingGenes;
	
	/* Getters & Setters
     * ----------------- */ 
    public DatasetCrossing() {
	}
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GeneSet getGeneSet1() {
        return geneSet1;
    }

    public void setGeneSet1(GeneSet geneSet) {
        this.geneSet1 = geneSet;
    }

    public Integer getGeneSet1Dir() {
        return geneSet1Dir;
    }

    public void setGeneSet1Dir(Integer geneSetDir) {
        this.geneSet1Dir = geneSetDir;
    }

    public GeneSet getGeneSet2() {
        return geneSet2;
    }

    public void setGeneSet2(GeneSet geneSet) {
        this.geneSet2 = geneSet;
    }

    public Integer getGeneSet2Dir() {
        return geneSet2Dir;
    }

    public void setGeneSet2Dir(Integer geneSetDir) {
        this.geneSet2Dir = geneSetDir;
    }

    public Double getPValue() {
        return pValue;
    }

    public void setPValue(Double pValue) {
        this.pValue = pValue;
    }

    public Double getJaccardIndex() {
        return jaccardIndex;
    }

    public void setJaccardIndex(Double jaccardIndex) {
        this.jaccardIndex = jaccardIndex;
    }
    
    public Integer getOverlappingGenes() {
        return overlappingGenes;
    }

    public void setOverlappingGenes(Integer overlappingGenes) {
        this.overlappingGenes = overlappingGenes;
    }
}