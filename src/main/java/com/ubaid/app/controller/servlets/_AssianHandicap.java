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

import com.ubaid.app.model.Converter;
import com.ubaid.app.model.asianhandicap.AssianHandicapConverter;
import com.ubaid.app.model.logic.AssianHandicapLogic;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;

public class _AssianHandicap extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public _AssianHandicap()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		try
		{
			Logic logic = new AssianHandicapLogic();
			Converter converter = new AssianHandicapConverter();
			
			String _id = request.getParameter("id");
			String homeTeam = request.getParameter("homeTeam");
			String awayTeam = request.getParameter("awayTeam");
						
			long id = Long.parseLong(_id);
			
			LinkedList<Entity> _eEntities = logic.getAll(id);
					
			LinkedList<Match> events =  converter.convert(id, homeTeam, awayTeam, _eEntities);
			
			JSONArray array = new JSONArray();
			JSONObject object;
			
			for (Match match : events)
			{
				object = new JSONObject();
				object.put("homeTeamThreshold", match.getHomeTeamThreshold1());
				object.put("awayTeamThreshold", match.getAwayTeamThreshold1());
				object.put("homeTeamOdds", match.getHomeTeamOdds());
				object.put("awayTeamOdds", match.getAwayTeamOdds());
				object.put("outcome1", match.getOutcome1());
				object.put("outcome2", match.getOutcome2());
				array.put(object);
			}
			
			
			PrintWriter writer = response.getWriter();
			writer.write(array.toString());
			writer.flush();
			
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
