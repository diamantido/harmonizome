package edu.mssm.pharm.maayanlab.Harmonizome.page;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.DatasetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneSetDao;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.BioEntityAlphabetizer;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = {"/gene", "/gene/*"})
public class GenePage extends HttpServlet {

    private static final long serialVersionUID = 4256183225988457817L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String query = UrlUtil.getPath(request);

        // If the gene is a synonym, we save the original query to notify the
        // user where we are redirecting from.
        String originalQuery = null;
        Gene gene = null;
        boolean isSynonym = false;
        int numCategories = 0;
        int numDatasets = 0;
        Long numAssociations = new Long(0);
        String entityList = "";

        List<Dataset> datasetsByGene = null;

        // We could make another DB query, but the number datasets should
        // never be greater than ~100.
        Set<String> uniqueAttributeGroups = new HashSet<String>();

        try {
            HibernateUtil.beginTransaction();
            if (query != null) {
                gene = GenericDao.get(Gene.class, query);
                if (gene == null) {
                    gene = GeneDao.getFromSynonymSymbol(query);
                    if (gene != null) {
                        isSynonym = true;
                        originalQuery = query;
                        query = gene.getSymbol();
                    }
                }
                if (gene != null) {
                    datasetsByGene = DatasetDao.getFromGene(query);
                    Collections.sort(datasetsByGene, new BioEntityAlphabetizer());
                    for (Dataset dataset : datasetsByGene) {
                        uniqueAttributeGroups.add(dataset.getAttributeGroup().getName());
                    }
                    numCategories = uniqueAttributeGroups.size();
                    numDatasets = datasetsByGene.size();
                    numAssociations = GeneSetDao.getNumAssociationsFromGene(gene.getSymbol());
                }
            }

            if (gene == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            } else {
                StringBuilder groups = new StringBuilder();
                int i = 0;
                for (String group : uniqueAttributeGroups) {
                    groups.append(group);
                    if (i < uniqueAttributeGroups.size() - 1) {
                        groups.append(", ");
                    }
                    i++;
                }

// [GeneSym] has [Number of associations] functional associations with biological entities spanning [Number of attribute categories] categories ([semi-colon delimited list of categories]) extracted from [Number of datasets] datasets”.
                String allAssociationsSummary = gene.getSymbol() +
                        " has " + NumberFormat.getInstance().format(numAssociations) +
                        " functional associations with biological entities spanning " + numCategories +
                        " categories (" + groups.toString() + ")" +
                        " extracted from " + numDatasets +
                        " datasets.";
                // "Associations covering " + numCategories + " categories of biological entities (" + groups.toString() + ")" + entityList + " from " + numDatasets + " datasets";
                request.setAttribute("allAssociationsSummary", allAssociationsSummary);
                request.setAttribute("note", isSynonym ? "Gene; redirected from " + originalQuery : "Gene");
                request.setAttribute("gene", gene);
                request.setAttribute("datasetsByGene", datasetsByGene);
                request.getRequestDispatcher(Constant.TEMPLATE_DIR + "gene.jsp").forward(request, response);
            }
            HibernateUtil.commitTransaction();
        } catch (HibernateException he) {
            he.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.close();
        }
    }
}
