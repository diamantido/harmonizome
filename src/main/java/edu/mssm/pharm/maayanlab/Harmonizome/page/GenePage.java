package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.AttributeDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncTerminalFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/gene", "/gene/*" })
public class GenePage extends HttpServlet {

	private static final long serialVersionUID = 4256183225988457817L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String query = URLUtil.get(request, true);
		Gene gene = null;
		boolean isSynonym = false;
		Map<String, Map<String, List<Attribute>>> organizedAttributes = null;
		List<String> genes = null;
		
		try {
			HibernateUtil.beginTransaction();
			if (query != null) {
				gene = GeneDAO.getGeneBySymbol(query);
				if (gene == null) {
					gene = GeneDAO.getGeneBySynonymSymbol(query);
					if (gene != null) {
						isSynonym = true;
					} 
				}
				if (gene != null) {
					organizedAttributes = AttributeDAO.getAttributesByGroupAndTypeFromGene(query);
				}
			} else {
				genes = GeneDAO.getGeneSymbols();
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}

		if (query == null) {
			request.setAttribute("genes", genes);
			request.getRequestDispatcher(Constant.TEMPLATE_DIR + "geneSearch.jsp").forward(request, response);
		} else {
			if (gene == null) {
				request.setAttribute("queryType", "gene");
				request.setAttribute("query", query);
				request.getRequestDispatcher(Constant.TEMPLATE_DIR + "notFound.jsp").forward(request, response);
			} else {
				String idgFamily = "";
				String idgTdlClass = "";
				List<String> proteins = new ArrayList<String>();
				List<String> geneSynonyms = new ArrayList<String>();
				List<String> hgncRootFamilies = new ArrayList<String>();
				List<String> hgncTerminalFamilies = new ArrayList<String>();
				if (gene != null) {
					if (gene.getIdgFamily() != null) {
						idgFamily = gene.getIdgFamily().getName();
					}
					if (gene.getIdgTdlClass() != null) {
						idgTdlClass = gene.getIdgTdlClass().getName();
					}
					for (GeneSynonym gs : gene.getSynonyms()) {
						geneSynonyms.add(gs.getSymbol());
					}
					if (gene.getProteins() != null) {
						for (Protein protein : gene.getProteins()) {
							proteins.add(protein.getSymbol());
						}
					}
					if (gene.getHgncRootFamilies() != null) {
						for (HgncRootFamily rf : gene.getHgncRootFamilies()) {
							hgncRootFamilies.add(rf.getName());
						}
					}
					if (gene.getHgncTerminalFamilies() != null) {
						for (HgncTerminalFamily rf : gene.getHgncTerminalFamilies()) {
							hgncTerminalFamilies.add(rf.getName());
						}
					}
				}

				// Information.
				request.setAttribute("note", isSynonym ? "Gene; redirected from " + query : "Gene");
				request.setAttribute("symbol", gene.getSymbol());
				request.setAttribute("name", gene.getName());
				request.setAttribute("description", gene.getDescription());
				request.setAttribute("synonyms", StringUtils.join(geneSynonyms, ", "));
				request.setAttribute("proteins", proteins.toArray(new String[proteins.size()]));
				request.setAttribute("ncbiEntrezGeneId", gene.getNcbiEntrezGeneId());
				request.setAttribute("ncbiEntrezGeneUrl", gene.getNcbiEntrezGeneUrl());
				request.setAttribute("idgFamily", idgFamily);
				request.setAttribute("idgTdlClass", idgTdlClass);
				request.setAttribute("hgncRootFamilies", hgncRootFamilies.toArray(new String[hgncRootFamilies.size()]));
				request.setAttribute("hgncTerminalFamilies", hgncTerminalFamilies.toArray(new String[hgncTerminalFamilies.size()]));
				request.setAttribute("organizedAttributes", organizedAttributes);
				request.getRequestDispatcher(Constant.TEMPLATE_DIR + "genePage.jsp").forward(request, response);
			}
		}
	}
}
