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

public class StartUpUtil
{
	public static boolean isFirst = true;
	com.ubaid.app.model.logic.matchLogic.Logic logic = null;
	
	public void onStart()
	{
		Logic trackLogic = new TrackLogic();
		logic = new MatchLogic();
		List<Entity> entities = trackLogic.getAll();
		int size = entities.size();
		
		LinkedList<Track> tracks = new LinkedList<>();
		
		for(int i = 0; i < size; i++)
		{
			tracks.add((Track) entities.get(i));
		}
			
		for(int i = 0; i < size; i++)
		{
			long match_id = tracks.get(i).getId();
			Match match = logic.getMatch(match_id);
			TrackedMatchList.trackedMatches.put(match_id, match);
			TrackedMatchList.hashtable.put(match_id, Checked.Checked);
		}
		
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
				//staring from here
				Logic logic = new RegisteredOutcomeLogic();
				
			}
		});
		
		
		Controller controller = Controller.getController();		
		ExecutorService service = Executors.newCachedThreadPool();
//		service.execute(new TrackedMatchList());
		service.shutdown();
		controller.setSevice(service);
		
	}
	
	
	

}
