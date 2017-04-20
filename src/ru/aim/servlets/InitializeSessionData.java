package ru.aim.servlets;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.aim.dbmodels.DBManager;
import ru.aim.modelmanagers.WebAimGroupManager;
import ru.aim.modelmanagers.WebAimManager;
import ru.aim.modelmanagers.WebGroupManager;
import ru.aim.models.WebAim;

import ru.aim.models.WebCollector;
import ru.aim.models.WebGroup;
import ru.aim.models.WebUser;



/**
 * Servlet implementation for all displayed tables
 */
@WebServlet("/Protected/initializeSessionData.do")
public class InitializeSessionData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitializeSessionData() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String baseURL = getServletContext().getInitParameter("baseURL");
		
		try
		{
			
			if (getServletContext().getAttribute("AimDBManager") != null)
			{
				DBManager dbm = (DBManager)getServletContext().getAttribute("AimDBManager");
				HttpSession s = request.getSession();
				WebUser wu = (WebUser) s.getAttribute("authorized_user");
				int userId = wu.getId();
				
				//Gets all WebAims for a User
				if (s.getAttribute("aimData") == null) {
					WebAimManager wam = new WebAimManager();
					ArrayList<WebAim> allAims = wam.getUserAimsByDeadline(dbm, userId);
					s = request.getSession();
					s.setAttribute("aimData", allAims);
				}
				
				//Gets all archive's WebAims for a User
				if (s.getAttribute("archiveAimData") == null) {
					WebAimManager wam = new WebAimManager();
					ArrayList<WebAim> allArchivesAims = wam.getArchivesUserAims(dbm, wu.getId());
					s = request.getSession();
					s.setAttribute("archiveAimData", allArchivesAims);
				}
								
				//Gets all Group for a User
				if (s.getAttribute("groupData") == null) {
					WebGroupManager wgm = new WebGroupManager();
					ArrayList<WebGroup> groupsOfUser = wgm.getGroupsByUser(dbm, userId);
					s = request.getSession();
					s.setAttribute("groupData", groupsOfUser);
					
				}
				
				//Gets composite object of WebCollectors and sets it into SessionScope.  WebGroups by deadline
				if (s.getAttribute("userAimGroupData") == null) {
					WebAimGroupManager wagm = new WebAimGroupManager();
						if(s.getAttribute("groupData") == null){
							response.sendRedirect(baseURL + "/Protected/Cabinet.jsp");
							return;
						}
					ArrayList<WebGroup> groupsOfUser = (ArrayList<WebGroup>) s.getAttribute("groupData");
					if(groupsOfUser.isEmpty()) {
						s.setAttribute("userAimGroupData", null);
					} else {
						ArrayList<ArrayList<WebCollector>> groupsInfo = new ArrayList<>();
						for(WebGroup wg : groupsOfUser) {
							groupsInfo.add(wagm.getWebCollectors(dbm, wg.getName()));
							}
						s = request.getSession();
						s.setAttribute("userAimGroupData", groupsInfo);
					}
				}
				//Gets composite object of WebCollectors and sets it into SessionScope. WebGroups by rate
				if (s.getAttribute("rateUsersInGroup") == null) {
					WebAimGroupManager wagm = new WebAimGroupManager();
						if(s.getAttribute("groupData") == null){
							response.sendRedirect(baseURL + "/Protected/Cabinet.jsp");
							return;
						}
					ArrayList<WebGroup> groupsOfUser = (ArrayList<WebGroup>) s.getAttribute("groupData");
					if(groupsOfUser.isEmpty()) {
						s.setAttribute("rateUsersInGroup", null);
					} else {
						ArrayList<ArrayList<WebCollector>> groupsRate = new ArrayList<>();
						for(WebGroup wg : groupsOfUser) {
							groupsRate.add(wagm.getWebCollectorsForRate(dbm, wg.getName()));
						}
						s = request.getSession();
						s.setAttribute("rateUsersInGroup", groupsRate);
					}
				}
				response.sendRedirect(baseURL + "/Protected/Cabinet.jsp");
				return;
			}
		}
		catch (Exception ex)
		{
			//log it.
			response.sendRedirect(baseURL + "/errorHandler.jsp");
		}
	}

}
