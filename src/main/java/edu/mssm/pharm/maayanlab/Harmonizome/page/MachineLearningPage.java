//package edu.mssm.pharm.maayanlab.Harmonizome.page;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.mllib.linalg.Vector;
//import org.apache.spark.mllib.linalg.Vectors;
//
//import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
//
//@WebServlet(urlPatterns = { "/" + Constant.MACHINE_LEARNING_URL })
//public class MachineLearningPage extends HttpServlet {
//
//	private static final long serialVersionUID = -7180812944688684500L;
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		String logFile = "/Users/gwg/harmonizome/deploy.sh";
//		SparkConf conf = new SparkConf()
//            .setMaster("local");
//
//		JavaSparkContext sc = new JavaSparkContext(conf);
//		JavaRDD<String> logData = sc.textFile(logFile).cache();
//
//		double[] array = {1.0, 2.0, 3.0};
//		Vector vector = Vectors.dense(array);
//		
//		long numAs = logData.filter(new Function<String, Boolean>() {
//			public Boolean call(String s) {
//				return s.contains("a");
//			}
//		}).count();
//		
//		System.out.println("Lines with a: " + numAs);
//
//		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "learn.jsp").forward(request, response);
//	}
//}