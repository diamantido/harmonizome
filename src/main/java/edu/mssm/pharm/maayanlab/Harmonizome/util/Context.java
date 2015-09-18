package edu.mssm.pharm.maayanlab.Harmonizome.util;

import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebListener
public class Context implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
	}

	@SuppressWarnings("deprecation")
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Free all resources.
		HibernateUtil.shutdown();

		// TODO: Find memory leak that requires server to be restarted after hot deploying several (3?) times.
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		for (Thread t : threadSet) {
			if (t.getName().contains("Abandoned connection cleanup thread")) {
				synchronized (t) {
					System.out.println("Forcibly stopping thread to avoid memory leak: " + t.getName());
					// Don't complain, it works.
					t.stop(); 
				}
			}
		}
	}
}