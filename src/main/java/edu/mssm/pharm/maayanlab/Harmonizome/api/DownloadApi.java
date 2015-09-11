package edu.mssm.pharm.maayanlab.Harmonizome.api;
/* 2015/09/18
 * GWG
 * 
 * This code works perfectly *except* Hadoop has a dependency on
 * servlet-api v2.5. We use v3.0, and I don't want to downgrade a
 * dependency just for this. In the future, it might be nice to store
 * files on a distributed system.
 */

//package edu.mssm.pharm.maayanlab.Harmonizome.api;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.io.Reader;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.zip.GZIPInputStream;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//
//import edu.mssm.pharm.maayanlab.Harmonizome.model.Download;
//import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlUtil;
//import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
//
//@WebServlet(urlPatterns = { "/" + Constant.DATA_DIRECTORY + "/" + Download.ENDPOINT + "/*" })
//public class DownloadAPI extends HttpServlet {
//
//	private static final long serialVersionUID = -5101442831516218061L;
//	
//	private static final String DIRECTORY = "/projects/harmonizome/data/";
//	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String[] query = UrlUtil.getPathAsArray(request, true);
//		System.out.println("Downloading static files");
//		if (query != null && query.length == 2) {
//			try {
//				String resourceName = query[0];
//				String filename = query[1];
//				
//				Configuration configuration = new Configuration();
//				FileSystem fs = FileSystem.get(new URI("hdfs://146.203.54.165:8020"), configuration);
//				String fullFilePath = DIRECTORY + resourceName + "/" + filename;
//				System.out.println(fullFilePath);
//				Path filePath = new Path(fullFilePath);
//				FSDataInputStream fsDataInputStream = fs.open(filePath);
//				
//				InputStream gzipStream = new GZIPInputStream(fsDataInputStream);
//				Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
//				BufferedReader br = new BufferedReader(decoder);
//				
//				PrintWriter out = response.getWriter();
//		        String line = br.readLine();
//		        while (line != null) {
//					line = br.readLine();
//					if (line != null) {
//						out.write(line);
//					}			
//		        }
//		        out.flush();
//		        
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}