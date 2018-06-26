package edu.mssm.pharm.maayanlab.Harmonizome.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Association;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = {
	"/" + Constant.DOWNLOAD_ASSOCIATIONS_URL,
	"/" + Constant.DOWNLOAD_ASSOCIATIONS_URL + "/"
})
public class DownloadGeneAssociationsApi extends HttpServlet {

	private static final long serialVersionUID = 825000865667024400L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateUtil.beginTransaction();
			String symbol = UrlUtil.getParameter(request, "gene");
			Gene gene = GenericDao.get(Gene.class, symbol);
			if (gene == null) {
				return;
			}

			// http://stackoverflow.com/questions/1062113/fastest-way-to-write-huge-data-in-text-file-java
			String contentDisposition = String.format("attachment; filename=%s associations.tsv", gene.getSymbol());
			response.setHeader("Content-Disposition", contentDisposition);

			ServletOutputStream outStream = response.getOutputStream();
			String text = "association\tdataset\tthreshold value\tstandardized value\n";
			outStream.print(text);

			for (Association assoc : gene.getAssociations()) {
				text = "";
				text += assoc.getGeneSet().getNameFromDataset();
				text += "\t";
				text += assoc.getGeneSet().getDataset().getName();
				text += "\t";
				text += assoc.getThresholdValue();
				text += "\t";
				text += assoc.getStandardizedValue();
				text += "\n";
				outStream.print(text);
			}

			outStream.flush();
			outStream.close();
			
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}
	}
}
