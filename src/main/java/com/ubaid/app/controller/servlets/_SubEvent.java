package com.ubaid.app.controller.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.SubEventsLogic;
import com.ubaid.app.model.objects.Convert_;
import com.ubaid.app.model.objects.Converter;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;
import com.ubaid.app.model.objects.SubEvents;

public class _SubEvent extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private Logic logic;
	private Converter converter = new Convert_();
   
    public _SubEvent()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			System.out.println("In Sub Events Servlet");

			
			String attribute = request.getParameter("id");
			
			System.out.println("In Sub Events Servlet");
			logic  = new SubEventsLogic();
			
			long id = Long.parseLong(attribute);
			
			
			LinkedList<Entity> events = logic.getAll(id);
			
			LinkedList<SubEvents> list = new LinkedList<>();
			
			for(int i = 0; i < events.size(); i++)
			{
				list.add((SubEvents) events.get(i));
			}

			System.out.println("Converting these sub events to matches");
			
			List<Match> matchs = converter.convert(list);
			
			System.out.println("Coversion completed");
			
			request.getSession().setAttribute("list_subEvent", matchs);
			
			response.getWriter().write("200");
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

}
