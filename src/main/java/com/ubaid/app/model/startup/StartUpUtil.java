package com.ubaid.app.model.startup;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.schedule1_1.Scheduler;


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
	
	/**
	 * this is the method which call on the first run of website by a user after deployment
	 */
	public void onStart()
	{
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

	}
}
