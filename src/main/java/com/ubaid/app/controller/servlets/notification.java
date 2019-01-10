package com.ubaid.app.controller.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ubaid.app.controller.Controller;
import com.ubaid.app.model.logic.matchLogic.Logic;
import com.ubaid.app.model.logic.matchLogic.MatchLogic;
import com.ubaid.app.model.objects.Match;

public class notification extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Controller.pauseNotificationSystem();
			//all match(odds) in the tracked odds history
			Logic logic = new MatchLogic();
			LinkedList<Match> matches = logic.getAllMatches();
			request.getSession().setAttribute("matches", matches);
//			request.getRequestDispatcher("/app1/src/views/notifications/index.jsp").forward(request, response);
			System.out.println("IN notification servlet");
			Controller.resumeNotificationSystem();
			response.getWriter().write(200);
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
