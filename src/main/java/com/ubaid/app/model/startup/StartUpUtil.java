package com.ubaid.app.model.startup;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.logic.TrackedMatchLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.schedule1_1.oddsDetection.OddsDetection;
import com.ubaid.app.model.schedule1_1.thresholdDetection.ThresholdDetection;
import com.ubaid.app.model.schedule1_1.thresholdDetection.TrackedMatches;


/**
 * 
 * @author ubaid
 * after very first deployment of web site 
 * when a user open the application (note only for first time) 
 * then hash tables becomes populated and get outcomes from the registered outcome tables from the 
 * mysql database
 */
public class StartUpUtil
{
	public static boolean isFirst = true;
	
	//onStart method is called from the init() method of the servlet
	public void onStart()
	{

		//this thread fill the hashtable with the outcomes [registered in the database]
		ExecutorService innerThread1 = Executors.newFixedThreadPool(2);

		//TODO to test threshold detection, I commented out this code
		
		innerThread1.execute(new Runnable()
		{
			@Override
			public void run()
			{
				//loop will fill the outcomes table
				//getting registered outcomes
				Logic logic = new RegisteredOutcomeLogic();
				LinkedList<Entity> _outcomes = logic.getAll();
				for(Entity entity : _outcomes)
				{
					Outcome outcome = (Outcome) entity;
					OddsDetection.putInTrackeEvents(outcome.getId(), outcome);
				}
			}
		});
		
		//this thread will fill the trackedMatch hashtable 
		innerThread1.execute(new Runnable()
		{
			@Override
			public void run()
			{
				
				long startTime = System.nanoTime();
				//getting Tracked Matches
				Logic logic = new TrackedMatchLogic();
				List<Entity> _trackedMatches = logic.getAll();
				List<TrackedMatches> tracked_matches = new LinkedList<>();
				for(Entity entity : _trackedMatches)
				{
					TrackedMatches trackedMatch = (TrackedMatches) entity;
					ThresholdDetection.putInTrackeEvents(trackedMatch.getMatchId(), trackedMatch.getSportName(), trackedMatch);	
					tracked_matches.add(trackedMatch);
				}
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				System.out.println(duration/1000000 + "milli seconds");
				//ensuring outcomes populated in the tracked matches TODO no need of this
/*
				while(true)
				{
					int total_size = tracked_matches.size();
					int counter = 0;
					try
					{
						Thread.sleep(1000);
					}
					catch(InterruptedException exp)
					{
						exp.printStackTrace();
					}
					
					for(TrackedMatches match : tracked_matches)
					{
						if(match.getOutcomes() == null)
						{
							break;
						}
						counter++;
					}
					if(counter == total_size)
						break;
				}*/
			}
		});
		
		innerThread1.shutdown();
		
		//when the thread work completed then return back
		try
		{
			innerThread1.awaitTermination(1, TimeUnit.HOURS);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
