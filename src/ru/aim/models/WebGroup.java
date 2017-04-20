package ru.aim.models;

import java.io.Serializable;

/**
 *  JavaBean for a Group created in the system
 */
public class WebGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int uid;
	private String name;
	private String pwd;
	private boolean creator;
	
	public WebGroup()
	{
		//do nothing
	}
	
	/**
	 * Returns ID of Group
	 * @return int
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Sets ID for Group
	 * @param value
	 */
	public void setId(int value)
	{
		id = value;
	}
	
	/**
	 * Returns User's ID
	 * @return int
	 */
	public int getUserId()
	{
		return uid;
	}
	
	/**
	 * Sets User's ID
	 * @param value
	 */
	public void setUserId(int value)
	{
		uid = value;
	}
	
	/**
	 * Returns name of Group
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of Group
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns Users's password for a Group
	 * @return String
	 */
	public String getPassword()
	{
		return pwd;
	}
	
	/**
	 * Sets User's password for a Group
	 * @param value
	 */
	public void setPassword(String value)
	{
		pwd = value;
	}
	

	/**
	 * Checks whether a User has created the Group,
	 * only creator can delete a Group
	 * returns <b>true</b> if is creator
	 * @return boolean
	 */
	public boolean isCreator() {
		return creator;
	}

	/**
	 * Sets User as creator of Group, therefore
	 * that User can delete Group
	 * @param creator
	 */
	public void setCreator(boolean creator) {
		this.creator = creator;
	}

	public String toString()
	{
		return String.format("ID: %d\tUID: %d\tPWD: %s\tGroupName: %s\tCreator: %s\t"
					, getId()
					, getUserId()
					, getPassword()
					, getName()
					, isCreator()
					);
					
	}
}
