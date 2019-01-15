package com.ubaid.app.controller.servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.schedule1_1.BettingType;
import com.ubaid.app.model.schedule1_1.Helper;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.schedule1_1.Scheduler;

/**
 * Servlet implementation class HADRegistrar
 */
public class HADRegistrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HADRegistrar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String _map = request.getParameter("data");		
			String _isAdd = request.getParameter("checked");
			boolean isAdd = Boolean.parseBoolean(_isAdd);
			JSONObject map = new JSONObject(_map);
			Logic logic = new RegisteredOutcomeLogic();
			Outcome[] outcomes = new Outcome[3];
			
			
			outcomes[0] = new Outcome.Builder()
									.id(map.getLong(Helper.HOMETEAMOUTCOMEID.toString()))
									.homeTeam(map.getString(Helper.HOMETEAM.toString()))
									.awayTeam(map.getString(Helper.AWAYTEAM.toString()))
									.participant(map.getString(Helper.HOMETEAM.toString()))
									.leagueName(map.getString(Helper.LEAGUENAME.toString()))
									.matchName(map.getString(Helper.MATCHNAME.toString()))
									.odds(map.getFloat(Helper.HOMETEAMODDS.toString()))
									.registerTime(new Timestamp(System.currentTimeMillis()))
									.changedTime(new Timestamp(System.currentTimeMillis()))
									.threshold(-1)
									.bettingType(BettingType.HomeDrawAway)
									.build();
			
			outcomes[1] = new Outcome.Builder()
									.id(map.getLong(Helper.AWAYTEAMOUTCOMEID.toString()))
									.homeTeam(map.getString(Helper.HOMETEAM.toString()))
									.awayTeam(map.getString(Helper.AWAYTEAM.toString()))
									.participant(map.getString(Helper.AWAYTEAM.toString()))
									.leagueName(map.getString(Helper.LEAGUENAME.toString()))
									.matchName(map.getString(Helper.MATCHNAME.toString()))
									.odds(map.getFloat(Helper.AWAYTEAMODDS.toString()))
									.registerTime(new Timestamp(System.currentTimeMillis()))
									.changedTime(new Timestamp(System.currentTimeMillis()))
									.threshold(-1)
									.bettingType(BettingType.HomeDrawAway)
									.build();

			
			outcomes[2] = new Outcome.Builder()
									.id(map.getLong(Helper.DRAWOUTCOMEID.toString()))
									.homeTeam(map.getString(Helper.HOMETEAM.toString()))
									.awayTeam(map.getString(Helper.AWAYTEAM.toString()))
									.leagueName(map.getString(Helper.LEAGUENAME.toString()))
									.matchName(map.getString(Helper.MATCHNAME.toString()))
									.odds(map.getFloat(Helper.DRAWODDS.toString()))
									.registerTime(new Timestamp(System.currentTimeMillis()))
									.changedTime(new Timestamp(System.currentTimeMillis()))
									.threshold(-1)
									.bettingType(BettingType.HomeDrawAway)
									.build();

			
			
			if(isAdd)
				add(logic, outcomes);
			else
				remove(logic, outcomes);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}

	}

	private void remove(Logic logic, Outcome[] outcomes)
	{
		for(int i = 0; i < 3; i++)
		{
			if(logic.delete(outcomes[i].getId()))
				Scheduler.removeFromTrackedEvents(outcomes[i].getId());			
		}
	}
	
	private void add(Logic logic, Outcome[] outcomes) throws ServletException
	{
		for(int i = 0; i < 3; i++)
		{
			if(logic.add(outcomes[i]));
				Scheduler.putInTrackeEvents(outcomes[i].getId(), outcomes[i]);
		}
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
