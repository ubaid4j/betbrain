package com.ubaid.app.model.startup;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubaid.app.controller.Controller;
import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.logic.TrackLogic;
import com.ubaid.app.model.logic.matchLogic.MatchLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;
import com.ubaid.app.model.objects.eventParticipant.Track;
import com.ubaid.app.model.schedule.Checked;
import com.ubaid.app.model.schedule.TrackedMatchList;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.schedule1_1.Scheduler;

import jdk.jfr.SettingControl;

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
	com.ubaid.app.model.logic.matchLogic.Logic logic = null;
	
	/**
	 * this is the method which call on the first run of website by a user after deployment
	 */
	public void onStart()
	{
/*		
		//getting tracklogic //will be removed
		Logic trackLogic = new TrackLogic();
		
		//getting match logic //will be removed
		logic = new MatchLogic();
		
		//getting entities //will be removed
		List<Entity> entities = trackLogic.getAll();
		
		//size of entities //will be removed
		int size = entities.size();
		
		//getting track list //will be removed
		LinkedList<Track> tracks = new LinkedList<>();
		
		for(int i = 0; i < size; i++)
		{
			tracks.add((Track) entities.get(i));
		}
			
		
		//setting these tracks in tracked match hash table //will be removed 
		for(int i = 0; i < size; i++)
		{
			long match_id = tracks.get(i).getId();
			Match match = logic.getMatch(match_id);
			TrackedMatchList.trackedMatches.put(match_id, match);
			TrackedMatchList.hashtable.put(match_id, Checked.Checked);
		}
*/		
		//will implement the 69 type bit 
		//an another thread which will 
		//fill the outcome tracked list
		//and we wait for this thread to complete and then 
		//in the service we can execute outcome schedule
		ExecutorService innerThread1 = Executors.newFixedThreadPool(1);
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
					Scheduler.putInTrackeEvents(outcome.getId(), outcome);
				}
			}
		});
		
		innerThread1.shutdown();
		
		while(true)
		{
			if(innerThread1.isTerminated())
				break;
		}

/*		
		Controller controller = Controller.getController();		
		ExecutorService service = Executors.newCachedThreadPool();
//		service.execute(new TrackedMatchList());
		service.shutdown();
		controller.setSevice(service);
*/		
	}
}
