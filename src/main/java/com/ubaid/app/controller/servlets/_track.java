package com.ubaid.app.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Helper;
import com.ubaid.app.model.schedule1_1.Outcome;


public class _track extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    public _track()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			//here to get all outcomes and show this in the file with out session, (using json)	
			Logic logic = new RegisteredOutcomeLogic();
			LinkedList<Entity> _rOutcomes = logic.getAll();
			
			JSONArray array = new JSONArray();
			JSONObject object = null;
			
			for(Entity entity : _rOutcomes)
			{
				Outcome outcome  = (Outcome) entity;
				object = new JSONObject();
				object.put(Helper.LEAGUENAME.toString(), outcome.getLeagueName());
				object.put(Helper.MATCHNAME.toString(), outcome.getMatchName());
				object.put(Helper.BETTINGTYPE.toString(), outcome.getBettingType());
				object.put(Helper.PARTICIPANT.toString(), outcome.getParticipant());
				object.put(Helper.OUTCOMEID.toString(), outcome.getId());
				array.put(object);
			}
				    	
	    	PrintWriter writer = response.getWriter();
			writer.write(array.toString());
			response.flushBuffer();
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
