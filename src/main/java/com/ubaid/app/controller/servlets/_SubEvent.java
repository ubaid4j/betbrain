package com.ubaid.app.controller.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.SubEventsLogic;
import com.ubaid.app.model.objects.Convert_;
import com.ubaid.app.model.objects.Converter;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;
import com.ubaid.app.model.objects.SubEvents;
import com.ubaid.app.model.schedule1_1.Helper;
import com.ubaid.app.model.schedule1_1.Scheduler;

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
			
			String attribute = request.getParameter("id");
			String eventName = request.getParameter("eventName");
			
			logic  = new SubEventsLogic();
			
			long id = Long.parseLong(attribute);
						
			LinkedList<Entity> events = logic.getAll(id);
			
			LinkedList<SubEvents> list = new LinkedList<>();
			
			for(int i = 0; i < events.size(); i++)
			{
				list.add((SubEvents) events.get(i));
			}

			List<Match> matchs = converter.convert(list);
						

			JSONArray array = new JSONArray();
			JSONObject object;
			
			try
			{
				for(Match match : matchs)
				{
					object = new JSONObject();
					
					object.put(Helper.ISCHECKED.toString(), Scheduler.getTrackedNotification().get(match.getHomeTeamOutcomeId()) == null ? "" : "Checked");
					object.put(Helper.HOMETEAM.toString(), match.getHomeTeam());
					object.put(Helper.AWAYTEAM.toString(), match.getAwayTeam());
					object.put(Helper.LEAGUENAME.toString(), match.getTournamentName());
					object.put(Helper.HOMETEAMODDS.toString(), match.getHomeTeamOdds());
					object.put(Helper.AWAYTEAMODDS.toString(), match.getAwayTeamOdds());
					object.put(Helper.DRAWODDS.toString(), match.getDrawOdds());
					object.put(Helper.HOMETEAMOUTCOMEID.toString(), match.getHomeTeamOutcomeId());
					object.put(Helper.AWAYTEAMOUTCOMEID.toString(), match.getAwayTeamOutcomeId());
					object.put(Helper.DRAWOUTCOMEID.toString(), match.getDrawOutcomeId());
					object.put("startTime", match.getStartTime());
					object.put("id", match.getId());
		
					array.put(object);
				}
				
			}
			catch(NullPointerException exp)
			{
				
			}
				
			response.getWriter().write(array.toString());
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
