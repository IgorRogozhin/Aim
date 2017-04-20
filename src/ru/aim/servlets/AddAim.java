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
import ru.aim.models.WebAimGroup;
import ru.aim.models.WebCollector;
import ru.aim.models.WebGroup;
import ru.aim.utilities.AimManagerUtils;


/**
 * Servlet implementation class AddAim
 */
@WebServlet("/Protected/addNewAim.do")
public class AddAim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAim() {
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
		request.setCharacterEncoding("utf-8");
				
		int userId = Integer.parseInt(request.getParameter("userId"));
		String name = request.getParameter("nameOfAim");
		String description = request.getParameter("description");
		String control = request.getParameter("control");
		
		String deadline_tmp = request.getParameter("calendar");
		String[] groups = request.getParameterValues("groups[]");
				
		LocalDate deadline = null;
		if(!deadline_tmp.equals("")){
			deadline = LocalDate.parse(deadline_tmp);
		}
		String baseURL = getServletContext().getInitParameter("baseURL");
		try
		{
			
			if (getServletContext().getAttribute("AimDBManager") != null)
			{
				DBManager dbm = (DBManager)getServletContext().getAttribute("AimDBManager");
				WebAimManager wam = new WebAimManager();
				WebAimGroupManager wagm = new WebAimGroupManager();
				WebAim aim = wam.insertAim(dbm, userId, name, description, control, deadline, deadline_tmp);
											
				if (aim == null)
				{
					//didn't work, try again:
					HttpSession s = request.getSession();
					s.setAttribute("occupiedAimName", name);
					response.sendRedirect(baseURL + "/Protected/addAim.jsp");
				}
				//now because we have modeled correctly, we can easily refresh data here, 
				//rather than redirecting around to do so!
				ArrayList<WebAim> allAims = wam.getUserAimsByDeadline(dbm, userId);
				//make the aim data fresh
				HttpSession s = request.getSession();
				s.setAttribute("aimData", allAims);
				
				//if a WebAim shared with WebGroups
				if ( !(AimManagerUtils.arrayNullOrEmpty(groups)) ) {
					ArrayList<WebAimGroup> aimGroups = new ArrayList<>();
					for(String group: groups) {
						WebAimGroup aimGroup = wagm.insertAimGroup(dbm, aim.getId(), group);
						aimGroups.add(aimGroup);
				}
					if(aimGroups.isEmpty()) {
						throw new Exception("Не получается установить задачу в группу");
					}
					
					String congrats = String.format("Поздравляю, у вас новая задача \"%s\"!", name);
					request.setAttribute("success", congrats);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Protected/updateSessionData.do");
					rd.forward(request, response);
					return;	
				}
				
				String congrats = String.format("Поздравляю, у вас новая задача \"%s\"!", name);
				request.setAttribute("success", congrats);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Protected/Cabinet.jsp");
				rd.forward(request, response);
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
