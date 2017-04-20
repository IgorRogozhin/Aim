package ru.aim.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.aim.dbmodels.DBManager;

import ru.aim.modelmanagers.WebGroupManager;


import ru.aim.utilities.AimManagerUtils;


/**
 * Servlet implementation class DeleteAim
 */
@WebServlet("/Protected/deleteGroup.do")
public class DeleteGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGroup() {
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
	
		String baseURL = getServletContext().getInitParameter("baseURL");
		String[] groups = request.getParameterValues("groups[]");
		
		try
		{
			
			if (getServletContext().getAttribute("AimDBManager") != null)
			{
				DBManager dbm = (DBManager)getServletContext().getAttribute("AimDBManager");
				WebGroupManager wgm = new WebGroupManager();
				
				if ( !(AimManagerUtils.arrayNullOrEmpty(groups)) ) {
					for(String groupName: groups) {
						boolean groupDeleted = wgm.deleteGroup(dbm, groupName);
						if( !groupDeleted) {
							throw new Exception ("Cannot delete: " + groupName);
						}
				}
					
					String congrats = "Поздравляю, удаление прошло успешно!";
					request.setAttribute("success", congrats);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Protected/updateSessionData.do");
					rd.forward(request, response);	
					return;		
			}
			else
			{
				throw new Exception ("No database connection.");
				
			}
			}
		}
		catch (Exception ex)
		{
			//log it.
			response.sendRedirect(baseURL + "/errorHandler.jsp");
		}
	}

}
