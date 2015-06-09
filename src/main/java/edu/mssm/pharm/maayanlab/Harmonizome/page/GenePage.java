package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.GeneSynonym;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncRootFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.HgncTerminalFamily;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Protein;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.Harmonizome.util.DAO;
import edu.mssm.pharm.maayanlab.Harmonizome.util.URLUtil;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { Constant.PAGES_BASE_URL + "/gene/*" })
public class GenePage extends HttpServlet {

	private static final long serialVersionUID = 4256183225988457817L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String queriedSymbol = URLUtil.get(req);
		if (queriedSymbol.equals("")) {
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "search_landing.jsp").forward(req, resp);
		} else {
			Gene gene = null;
			boolean isSynonym = false;
			String idgFamily = "";
			String idgTdlClass = "";
			List<String> proteins = new ArrayList<String>();
			List<String> geneSynonyms = new ArrayList<String>();
			List<String> hgncRootFamilies = new ArrayList<String>();
			List<String> hgncTerminalFamilies = new ArrayList<String>();

			try {
				HibernateUtil.beginTransaction();
				gene = DAO.getGeneBySymbol(queriedSymbol);
				if (gene == null) {
					gene = DAO.getGeneBySynonymSymbol(queriedSymbol);
					if (gene != null) {
						isSynonym = true;
					}
				}
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
				HibernateUtil.commitTransaction();
			} catch (HibernateException he) {
				HibernateUtil.rollbackTransaction();
			}

			if (gene == null) {
				req.setAttribute("query", queriedSymbol);
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "not_found.jsp").forward(req, resp);
			} else {
				// Filter engine
				req.setAttribute("pageType", "gene");
				
				// Information.
				req.setAttribute("note", isSynonym ? "Gene; redirected from " + queriedSymbol : "Gene");
				req.setAttribute("symbol", gene.getSymbol());
				req.setAttribute("name", gene.getName());
				req.setAttribute("description", gene.getDescription());
				req.setAttribute("synonyms", StringUtils.join(geneSynonyms, ", "));
				req.setAttribute("proteins", proteins.toArray(new String[proteins.size()]));
				req.setAttribute("ncbiEntrezGeneId", gene.getNcbiEntrezGeneId());
				req.setAttribute("ncbiEntrezGeneUrl", gene.getNcbiEntrezGeneUrl());
				req.setAttribute("idgFamily", idgFamily);
				req.setAttribute("idgTdlClass", idgTdlClass);
				req.setAttribute("hgncRootFamilies", hgncRootFamilies.toArray(new String[hgncRootFamilies.size()]));
				req.setAttribute("hgncTerminalFamilies", hgncTerminalFamilies.toArray(new String[hgncTerminalFamilies.size()]));
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "gene_page.jsp").forward(req, resp);
			}
		}
	}
}
