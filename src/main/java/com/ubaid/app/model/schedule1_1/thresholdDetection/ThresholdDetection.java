package com.ubaid.app.model.schedule1_1.thresholdDetection;

import java.util.Hashtable;

import com.ubaid.app.model.SportUtil;
import com.ubaid.app.model.SportUtilFactory;
import com.ubaid.app.model.schedule1_1.Schedule;

public class ThresholdDetection implements Schedule
{
	
	//detect threshold changing and put in the push notificaiton as an outcome


	public ThresholdDetection()
	{

	}

	@Override
	public void run()
	{
		schedule();
	}

	@Override
	public void schedule()
	{
		//here will be logic, how to detect threshold 
		//
		SportUtil su = SportUtilFactory.getSportUtil();
		
		//the result will contain matchId, odds, threshold, bettingType, participant, OUType
	}
	
	public static void putInTrackeEvents(long key,String sportName, TrackedMatches trackedMatch)
	{
		SportUtil su = SportUtilFactory.getSportUtil();
		int id = su.getSportId(sportName);
		if(id != -1)
		{
			Hashtable<Long, TrackedMatches> trHashtable = Schedule.trackedMatches.get(id);
			trHashtable.put(key, trackedMatch);
			Schedule.trackedMatches.put(id, trHashtable);
		}
	}
	
	public static void removeFromTrackedEvents(String sportName, long key)
	{
		SportUtil su = SportUtilFactory.getSportUtil();
		int id = su.getSportId(sportName);
		
		if(id != -1)
		{
			Hashtable<Long, TrackedMatches> trHashtable = Schedule.trackedMatches.get(id);
			trHashtable.remove(key);
			Schedule.trackedMatches.put(id, trHashtable);			
		}
	}
	
	public static Hashtable<Integer, Hashtable<Long, TrackedMatches>> getTrackedOutcomes()
	{
		return Schedule.trackedMatches;
	}

}
