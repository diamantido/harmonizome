package edu.mssm.pharm.maayanlab.Harmonizome.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebListener
public class Context implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("STARTING -------------------------------------------------------");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("CLOSING -------------------------------------------------------");
		HibernateUtil.shutdown();

		try {
		    AbandonedConnectionCleanupThread.shutdown();
		} catch (InterruptedException e) {
		    System.out.println("SEVERE problem cleaning up: " + e.getMessage());
		    e.printStackTrace();
		}
		
		String x = null;
	}
}