package ru.aim.models;

import java.io.Serializable;

/**
 *  JavaBean for a WebAim and WebGroups where it belongs
 *  
 */
public class WebAimGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int aimId;
	private int groupId;
	
	
	public WebAimGroup()
	{
		//do nothing
	}
	
	

	/**
	 * Returns ID of the Aim-Group pair
	 * @return int
	 */
	public int getId() {
		return id;
	}



	/**
	 * Sets ID for the Aim-Group pair
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * Returns ID for a WebAim
	 * @return int
	 */
	public int getAimId() {
		return aimId;
	}



	/**
	 * Sets ID for a WebAim
	 * @param aimId
	 */
	public void setAimId(int aimId) {
		this.aimId = aimId;
	}



	/**
	 * Returns ID for a WebGroup
	 * @return int
	 */
	public int getGroupId() {
		return groupId;
	}



	/**
	 * Sets ID for a WebGroup
	 * @param groupId
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}



	public String toString()
	{
		return String.format("ID: %d\tAimId: %d\tGroupId: %d\t"
					, getId()
					, getAimId()
					, getGroupId()
					);
					
	}
}
