package edu.mssm.pharm.maayanlab.Harmonizome.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebListener
public class Context implements ServletContextListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Context.class);
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
	}       

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.close();
	}
}