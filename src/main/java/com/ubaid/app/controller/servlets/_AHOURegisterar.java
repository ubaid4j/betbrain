package com.ubaid.app.controller.servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.ubaid.app.model.dao.RegisteredOutcomeDAO;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.schedule1_1.Helper;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.schedule1_1.Scheduler;

public class _AHOURegisterar extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public _AHOURegisterar()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String _map = request.getParameter("data");			
			JSONObject map = new JSONObject(_map);
			
			Outcome outcome = new Outcome.Builder()
									.id(map.getLong(Helper.OUTCOMEID.toString()))
									.homeTeam(map.getString(Helper.HOMETEAM.toString()))
									.awayTeam(map.getString(Helper.AWAYTEAM.toString()))
									.participant(map.getString(Helper.PARTICIPANT.toString()))
									.leagueName(map.getString(Helper.LEAGUENAME.toString()))
									.matchName(map.getString(Helper.MATCHNAME.toString()))
									.threshold(map.getFloat(Helper.THRESHOLD.toString()))
									.odds(map.getFloat(Helper.ODDS.toString()))
									.registerTime(new Timestamp(System.currentTimeMillis()))
									.changedTime(new Timestamp(System.currentTimeMillis()))
									.build();
			
			Logic logic = new RegisteredOutcomeLogic();
			if(logic.add(outcome));
				Scheduler.putInTrackeEvents(outcome.getId(), outcome);
			
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
