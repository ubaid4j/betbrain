package com.ubaid.app.model.schedule1_1;

import org.json.JSONObject;

public class OutcomeNotificationi implements OutcomeNotification
{

	public OutcomeNotificationi()
	{

	}
	
	
	

	@Override
	public Outcome next()
	{
		Outcome outcome = null;
		try
		{
			outcome = Schedule.notificationQueue.take();
		}
		catch (InterruptedException e)
		{
			System.out.println("Thread Interrupted at OutcomeNotificationi");
		}
		
		return outcome;
	}




	@Override
	public JSONObject getNotification()
	{
		Outcome outcome = next();
		JSONObject notification = new JSONObject();

		notification.put(Helper.AWAYTEAM.toString(), outcome.getAwayTeam());
		notification.put(Helper.HOMETEAM.toString(), outcome.getHomeTeam());
		notification.put(Helper.LEAGUENAME.toString(), outcome.getLeagueName());
		notification.put(Helper.MATCHNAME.toString(), outcome.getMatchName());
		notification.put(Helper.ODDS.toString(), outcome.getOdds());
		notification.put(Helper.OUTCOMEID.toString(), outcome.getId());
		notification.put(Helper.PARTICIPANT.toString(), outcome.getParticipant());
		notification.put(Helper.THRESHOLD.toString(), outcome.getThreshold());		

		return notification;
	}

}
