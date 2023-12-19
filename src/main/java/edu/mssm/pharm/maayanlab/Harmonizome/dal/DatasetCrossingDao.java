package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.DatasetCrossing;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

import java.util.List;

public class DatasetCrossingDao {

    public static List<DatasetCrossing> getDatasetCrossing(String dataset_1, String dataset_2) {
        return (List<DatasetCrossing>) HibernateUtil.getCurrentSession()
            .createQuery(
                "SELECT DISTINCT dc FROM DatasetCrossing dc " +
                "  JOIN dc.geneSet1 gs1 " +
                "  JOIN dc.geneSet2 gs2 " +
                "  JOIN gs1.dataset ds1 " +
                "  JOIN gs2.dataset ds2 " +
                "WHERE ds1.name = :dataset_1 AND ds2.name = :dataset_2"
            )
            .setString("dataset_1", dataset_1)
            .setString("dataset_2", dataset_2)
            .list();
    }

    @SuppressWarnings("unchecked")
	public static List<String> getLeftDatasetInPair() {
        return (List<String>) HibernateUtil.getCurrentSession()
            .createSQLQuery(
                "SELECT DISTINCT name FROM dataset" +
                "  JOIN gene_set ON gene_set.dataset_fk = dataset.id" +
                "  JOIN dataset_crossing ON " +
                "       dataset_crossing.gene_set_1_fk = gene_set.id "
            )
            .list();
    }

    @SuppressWarnings("unchecked")
	public static List<String> getRightDatasetInPair(String dataset1) {
        String subQuery = String.format("(SELECT id FROM dataset WHERE name = \"%s\")", dataset1);
        String query = String.format(
            "SELECT DISTINCT ds2.name AS dataset_2_name" +
            "   FROM dataset_crossing dc" +
            "   JOIN gene_set gs1 ON dc.gene_set_1_fk = gs1.id" +
            "   JOIN gene_set gs2 ON dc.gene_set_2_fk = gs2.id" +
            "   JOIN dataset ds2 ON gs2.dataset_fk = ds2.id " +
            "WHERE gs1.dataset_fk = %s " +
            "ORDER BY ds2.name ASC",
            subQuery
        );
        System.out.println(query);
        return (List<String>) HibernateUtil.getCurrentSession()
            .createSQLQuery(query)
            .list();
    }

    @SuppressWarnings("unchecked")
    public static List<Gene> getOverlap(String geneSet1, String geneSet1Dir, String geneSet2, String geneSet2Dir) {
        return (List<Gene>) HibernateUtil.getCurrentSession()
        .createQuery(
            "SELECT DISTINCT g1 " +
             "FROM DatasetCrossing dc " +
             "JOIN dc.geneSet1 gs1 " +
             "JOIN dc.geneSet2 gs2 " +
             "JOIN gs1.associations a1 " +
             "JOIN gs2.associations a2 " +
             "JOIN a1.gene g1 " +
             "JOIN a2.gene g2 " +
             "WHERE gs1.nameFromDataset = :geneSet1 " +
             "AND gs2.nameFromDataset = :geneSet2 " +
             "AND a1.thresholdValue = :geneSet1Dir " +
             "AND a2.thresholdValue = :geneSet2Dir " +
             "AND g1.id=g2.id"
        )
        .setString("geneSet1", geneSet1)
        .setString("geneSet1Dir", geneSet1Dir)
        .setString("geneSet2", geneSet2)
        .setString("geneSet2Dir", geneSet2Dir)
        .list();
    }
}