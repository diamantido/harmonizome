package edu.mssm.pharm.maayanlab.Harmonizome.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebListener
public class Context implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
	}       

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.close();
	}
}