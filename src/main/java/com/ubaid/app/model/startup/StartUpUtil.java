package com.ubaid.app.model.startup;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubaid.app.model.logic.Logic;
import com.ubaid.app.model.logic.RegisteredOutcomeLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.schedule1_1.oddsDetection.OddsDetection;


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
		
		innerThread1.execute(new Runnable()
		{
			
			@Override
			public void run()
			{
				
			}
		});
		
		innerThread1.shutdown();
		
		//when the thread work completed then return back
		while(true)
		{
			if(innerThread1.isTerminated())
				break;
		}

	}
}
