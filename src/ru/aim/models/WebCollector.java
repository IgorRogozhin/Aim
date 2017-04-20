package ru.aim.models;

import java.io.Serializable;

/**
 *  Collects all basic models (WebUser, WebAim, WebGroup)
 */
public class WebCollector implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private WebUser webUser = new WebUser();
	private WebAim webAim = new WebAim();
	private WebGroup webGroup = new WebGroup();

	public WebCollector()
	{
		//do nothing
	}
	


	/**
	 * Returns WebUser from WebCollector
	 * @return WebUser
	 */
	public WebUser getWebUser() {
		return webUser;
	}



	/**
	 * Sets WebUser for WebCollector
	 * @param webUser
	 */
	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}



	/**
	 * Returns WebAim from WebCollector
	 * @return WebAim
	 */
	public WebAim getWebAim() {
		return webAim;
	}



	/**
	 * Sets WebAim for WebCollector
	 * @param webAim
	 */
	public void setWebAim(WebAim webAim) {
		this.webAim = webAim;
	}



	/**
	 * Returns WebGroup from WebCollector
	 * @return WebGroup
	 */
	public WebGroup getWebGroup() {
		return webGroup;
	}



	/**
	 * Sets WebGroup for WebCollector
	 * @param webGroup
	 */
	public void setWebGroup(WebGroup webGroup) {
		this.webGroup = webGroup;
	}



	public String toString()
	{
		return String.format("WebUser: %s\tWebAim: %s\tWebGroup: %s\t"
					, getWebUser().toString()
					, getWebAim().toString()
					, getWebGroup().toString());
					
					
	}
}
