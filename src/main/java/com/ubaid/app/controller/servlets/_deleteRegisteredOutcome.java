package com.ubaid.app.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.schedule1_1.Scheduler;

/**
 * Servlet implementation class _deleteRegisteredOutcome
 */
public class _deleteRegisteredOutcome extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public _deleteRegisteredOutcome()
	{
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Logic logic = new RegisteredOutcomeLogic();
			long id = Long.parseLong(request.getParameter("id"));
			
			
			if(logic.delete(id))
				Scheduler.removeFromTrackedEvents(id);
			
			response.getWriter().write("200");
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
