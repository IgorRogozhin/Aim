package ru.aim.servlets;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.aim.dbmodels.DBManager;
import ru.aim.modelmanagers.WebAimGroupManager;
import ru.aim.modelmanagers.WebAimManager;
import ru.aim.models.WebAim;
import ru.aim.models.WebCollector;
import ru.aim.models.WebGroup;
import ru.aim.models.WebUser;


/**
 * Servlet implementation class DeleteAim
 */
@WebServlet("/Protected/failedAim.do")
public class FailedAim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FailedAim() {
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
		//add an aim
		
		request.setCharacterEncoding("UTF-8");

		int userId = Integer.parseInt(request.getParameter("userId"));
		String name = request.getParameter("nameOfAim");
		String description = request.getParameter("description");
		String baseURL = getServletContext().getInitParameter("baseURL");
		
		try
		{
			if (getServletContext().getAttribute("AimDBManager") != null)
			{
				DBManager dbm = (DBManager)getServletContext().getAttribute("AimDBManager");
				WebAimManager wam = new WebAimManager();
				
				boolean aimArchived = wam.markAsFailed(dbm, userId, name, description);
				if(!aimArchived) {
					response.sendRedirect(baseURL + "/errorHandler.jsp");
				}
				//now because we have modeled correctly, we can easily refresh data here, 
				//rather than redirecting around to do so!
				ArrayList<WebAim> allAims = wam.getUserAimsByDeadline(dbm, userId);
				ArrayList<WebAim> archiveAims = wam.getArchivesUserAims(dbm, userId);
				
				//make the aim data fresh
				HttpSession s = request.getSession();
				s.setAttribute("aimData", allAims);
				s.setAttribute("archiveAimData", archiveAims);
						
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("failed");
				return;		
			}
			else
			{
				//log it... and throw new Exception ("No database connection.");
				response.sendRedirect(baseURL + "/errorHandler.jsp");
			}
			
			
		}
		catch (Exception ex)
		{
			//log it.
			response.sendRedirect(baseURL + "/errorHandler.jsp");
		}
	}

}
