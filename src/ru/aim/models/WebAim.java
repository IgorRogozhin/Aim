package ru.aim.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *  JavaBean for any aim in the system
 */
public class WebAim implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int userId;
	private String name;
	private String description;
	private String control;
	private LocalDate deadline;
	private boolean archive;
	private boolean result;
	//private int userGroup;
	
	
	public WebAim()
	{
		//do nothing
	}
	
	/**
	 * Returns ID of an Aim in the app,
	 * @return int
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Sets ID for an Aim in the app
	 * @param value
	 */
	public void setId(int value)
	{
		id = value;
	}

	/**
	 * User's ID that created WebAim
	 * @return int
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets User's ID that creates WebAim
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Returns name of an Aim
	 * @return String
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets name for an Aim
	 * @param value
	 */
	public void setName(String value)
	{
		name = value;
	}
	
	/**
	 * Returns description of an Aim
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description for an Aim
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns parameter that User typed as
	 * control for an Aim
	 * @return String
	 */
	public String getControl() {
		return control;
	}

	/**
	 * Sets control parameter for an Aim
	 * @param control
	 */
	public void setControl(String control) {
		this.control = control;
	}
	
	/**
	 * Returns date in format YYYY-MM-DD
	 * on which User choose ends an Aim
	 * @return LocalDate
	 */
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * Sets deadline for an Aim
	 * @param deadline
	 */
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	/**
	 * Checks whether an Aim already done,
	 * returns <b>true</b> if an Aim is done
	 * @return boolean
	 */
	public boolean isArchive() {
		return archive;
	}

	/**
	 * Sets archive parameter for an Aim 
	 * @param archive
	 */
	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	/**
	 * Checks whether an Aim achieved
	 * or failed
	 * @return boolean
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * Sets result parameter to an Aim
	 * @param result
	 */
	public void setResult(boolean result) {
		this.result = result;
	}


	public String toString()
	{
		return String.format("ID: %d\tUserId: %d\tName: %s\tDescription: %s\tControl: %s\t"
				+ "Deadline: %s\tArchive: %s\tResult: %s\t"
					, getId()
					, getUserId()
					, getName()
					, getDescription()
					, getControl()
					, getDeadline().toString()
					, isArchive()
					, isResult());
					
	}
}
