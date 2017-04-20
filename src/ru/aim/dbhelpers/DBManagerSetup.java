package ru.aim.dbhelpers;
import javax.servlet.*;

import ru.aim.dbmodels.DBManager;
import ru.aim.dbmodels.MySQLServerConnectionBehavior;
import ru.aim.dbmodels.ServerConnectionBehavior;

/**
 * 
 * Creates frame to connect with polymorphic DB 
 * by initialization: creates Connection with exact DB
 * by destroy: close Connection with exact DB
 *
 */
public class DBManagerSetup implements ServletContextListener
{
	private DBManager dbm = null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//cleanup the connection when the context is destroyed
		if (dbm != null)
		{
			if (dbm.isConnected())
			{
				dbm.closeConnection(false);
			}
		}
		dbm = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//access the servlet context from the event argument (renamed sce)
		//get db con info from context init params
		ServletContext sc = sce.getServletContext();
		String uid = sc.getInitParameter("dbuserid");
		String pwd = sc.getInitParameter("dbuserpwd");
		String cat = sc.getInitParameter("dbinitcat");
		
		//set the scb for mySQL
		ServerConnectionBehavior scb = 
				new MySQLServerConnectionBehavior(uid, pwd, cat);
		
		//create the manager
		dbm = new DBManager(scb);
		
		//put the manager into the servlet context attributes for global use in
		//the application
		sc.setAttribute("AimDBManager", dbm);
		
		//System.out.println("AimDBManager created and added to context");
	}
}
