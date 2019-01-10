package com.ubaid.app.model.notification;

import java.util.concurrent.LinkedBlockingQueue;

import org.json.JSONObject;

import com.ubaid.app.model.objects.Match;


/**
 * 
 * @author ubaid
 *
 *	this class is implementing Notification interface
 *
 */
public class Notification implements INotification
{
	public static LinkedBlockingQueue<Match> notificationQueue = new LinkedBlockingQueue<Match>();
	
	public Notification()
	{
		
	}

	@Override
	public JSONObject getNotification()
	{
		try
		{
			Match match = notificationQueue.take();
			JSONObject object = new JSONObject();
			object.put("parentMatchId", match.getTournamentId());
			object.put("matchId", match.getId());
			object.put("homeTeam", match.getHomeTeam());
			object.put("awayTeam", match.getAwayTeam());
			object.put("homeTeamOdds", match.getHomeTeamOdds());
			object.put("drawOdds", match.getDrawOdds());
			object.put("awayTeamOdds", match.getAwayTeamOdds());
			object.put("changeTime", match.getTimestamp());
			System.out.println(object.toString());
			return object;
			
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

}
