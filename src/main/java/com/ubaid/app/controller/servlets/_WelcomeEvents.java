package com.ubaid.app.controller.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.WelcomeEvents;
import com.ubaid.app.model.singleton.WList;

public class _WelcomeEvents extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public _WelcomeEvents()
	{
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
		
			LinkedList<WelcomeEvents> list = new LinkedList<>();
			
			@SuppressWarnings("unchecked")
			Vector<Entity> entities = (Vector<Entity>) WList.getWList().clone();
			
			for(int i = 0; i < 1000; i++)
			{
				list.add((WelcomeEvents) entities.get(i));
			}
			
						
			
			System.out.println("Size of List" + list.size());
			
			request.getSession().setAttribute("list", list);
			
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("url", "/app1/src/views/start/start.jsp");
			response.getWriter().write(jsonObject.toString());
			System.out.println("Response sent");
			
		}
		catch(Exception exp)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("url", exp.getStackTrace());
			response.getWriter().write(jsonObject.toString());
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

}
