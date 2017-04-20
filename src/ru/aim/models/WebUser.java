package ru.aim.models;

import java.io.Serializable;

/**
 *  JavaBean for any User accounted into system
 */
public class WebUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String uid;
	private String pwd;
	private int solved;
	private int failed;
	
	
	public WebUser()
	{
		//do nothing
	}
	
	/**
	 * Returns ID of User in the app,
	 * @return int
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Sets ID for User in the app
	 * @param value
	 */
	public void setId(int value)
	{
		id = value;
	}
	
	/**
	 * Returns User's login
	 * @return String
	 */
	public String getUserId()
	{
		return uid;
	}
	
	/**
	 * Sets User's login
	 * @param value
	 */
	public void setUserId(String value)
	{
		uid = value;
	}
	
	/**
	 * Returns Users's password
	 * @return String
	 */
	public String getPassword()
	{
		return pwd;
	}
	
	/**
	 * Sets User's password
	 * @param value
	 */
	public void setPassword(String value)
	{
		pwd = value;
	}
	
	/**
	 * Returns number of solved Aims
	 * @return int
	 */
	public int getSolved() {
		return solved;
	}

	/**
	 * Sets number of solved Aims
	 * @param solved
	 */
	public void setSolved(int solved) {
		this.solved = solved;
	}

	/**
	 * Returns number of failed Aims
	 * @return int
	 */
	public int getFailed() {
		return failed;
	}

	/**
	 * Sets number of failed Aims
	 * @param failed
	 */
	public void setFailed(int failed) {
		this.failed = failed;
	}

	public String toString()
	{
		return String.format("ID: %d\tUID: %s\tPWD: %s\tsolved: %d\tfailed: %d\t"
					, getId()
					, getUserId()
					, getPassword()
					, getSolved()
					, getFailed());
					
	}
}
