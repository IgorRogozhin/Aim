package ru.aim.modelmanagers;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;

import ru.aim.dbhelpers.DBAimQueries;
import ru.aim.dbmodels.DBManager;
import ru.aim.models.WebUser;

/**
 * 
 * Manages a WebUser object creation cycle
 * (check-creation-validation)
 * by its methods
 * 
 */
public class WebUserManager implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Get a web user from DB if there is a match using uid/pwd combo
	 * @param dbm DBManager
	 * @param uid userid
	 * @param pwd password
	 * @return null if no match or bad input else matching WebUser
	 */
	public WebUser getWebUserByCredentials(DBManager dbm, String uid, String pwd)
	{
		if (dbm == null) return null;
		if (!dbm.isConnected())
		{
			if (!dbm.openConnection()) 
			{
				//throw new IOException("Could not connect to database and open connection");
				return null;
			}
		}
		
		WebUser wu = new WebUser();
		//see if there is a matching WebUser
		String query = DBAimQueries.getWebUserByUsernameAndPassword(uid, pwd);
		try
		{
			ResultSet rs = dbm.ExecuteResultSet(query);
						
			while (rs.next())
			{
				wu.setId(rs.getInt("id"));
				wu.setUserId(rs.getString("login"));
				wu.setPassword(rs.getString("password"));
				
			}
		
			//If DB return empty set
			if(!userIsValid(wu)) {
				return null;
			}
			
			return wu;
		}
		catch (Exception ex)
		{
			//log it
			return wu;
		}
	}
	
	/**
	 * Creates and returns WebUser object only if 
	 * user with exact name already exist
	 * @param dbm
	 * @param uid
	 * @param pwd
	 * @return WebUser
	 */
	public WebUser setWebUserByCredentials(DBManager dbm, String uid, String pwd)
	{
	
		if (dbm == null) return null;
		if (!dbm.isConnected())
		{
			if (!dbm.openConnection()) 
			{
				try {
					throw new IOException("Could not connect to database and open connection");
				} catch (IOException e) {
						e.getMessage();
				}
				return null;
			}
		}
		
		WebUser wu = new WebUser();
		
		//see if there is a matching WebUser only by login
		String query = DBAimQueries.getWebUserByUsername(uid);
		
		try
		{
			ResultSet rs = dbm.ExecuteResultSet(query);
			if (!rs.next()) {
				
				query =  DBAimQueries.addWebUserByUserNameAndPassword(uid, pwd);
				dbm.ExecuteNonQuery(query);
			
				query = DBAimQueries.getWebUserByUsernameAndPassword(uid, pwd);
				rs = dbm.ExecuteResultSet(query);
				
				while (rs.next())
				{
					
					wu.setId(rs.getInt("id"));
					wu.setUserId(rs.getString("login"));
					wu.setPassword(rs.getString("password"));
					
				}
				return wu;
			} 
			
			
		}
		catch (Exception ex)
		{
			//log it
			return wu;
		}
		return null;
	}

	/**
	 * Checks a WebUser object on <b>null</b> parameters <br>
	 * returns <b>true</b> if not <b>null</b> values
	 * @param wu 
	 * @return boolean
	 */
	public boolean userIsValid(WebUser wu)
	{
		if (wu == null) return false;
		if (wu.getUserId() == null || wu.getUserId().length() == 0) return false;
		if (wu.getPassword() == null || wu.getPassword().length() == 0) return false;
		
		return true;
	}
	
	/**
	 * Checks <b>parameters</b> on <b>null</b> value
	 * and zero length
	 * @param uid
	 * @param pwd
	 * @return boolean
	 */
	public boolean validateParams(String uid, String pwd)
	{
		if (uid == null) return false;
		if (pwd == null) return false;
		if (uid.length() == 0 || pwd.length() == 0)
		{
			//can't log in
			return false;
		}
		return true;
	}
	
}
