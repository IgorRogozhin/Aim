package ru.aim.dbmodels;

/**
 * 
 * Java Bean for DB-connection object
 *
 */
public abstract class DBUserInfo 
{
	private String uid;
	private String pwd;
	private String cat;
	
	public DBUserInfo()
	{
		
	}
	
	public DBUserInfo(String userID, String password, String catalog)
	{
		uid = userID;
		pwd = password;
		cat = catalog;
	}
	
	public String getUserID()
	{
		return uid;
	}
	
	public void setUserID(String value)
	{
		uid = value;
	}
	
	public String getPassword()
	{
		return pwd;
	}
	
	public void setPassword(String value)
	{
		pwd = value;
	}
	
	public String getCatalog()
	{
		return cat;
	}
	
	public void setCatalog(String catalog)
	{
		cat = catalog;
	}
}
