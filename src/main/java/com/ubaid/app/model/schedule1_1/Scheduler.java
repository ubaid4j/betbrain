package com.ubaid.app.model.schedule1_1;

import java.util.LinkedList;
import java.util.Set;

import com.ubaid.app.model.logic.OutcomeLogic;
import com.ubaid.app.model.logic.OutcomeLogici;
import com.ubaid.app.model.objects.Entity;

public class Scheduler implements Schedule
{

	public Scheduler()
	{
		
	}

	@Override
	public void schedule()
	{
		OutcomeLogic logic = new OutcomeLogici();
		
		while(true)
		{
			//let retrieve the ids of outcomes
			Set<Long> ids = Schedule.trackedOutcomes.keySet();
			Object[] _ids = new Object[ids.size()];
			int index = 0;
			for(long id : ids)
			{
				_ids[index++] = id;
			}

			//get the response from the database as list of Outcome
			LinkedList<Entity> _outcomes =  logic.getAll(_ids);
			LinkedList<Outcome> outcomes = new LinkedList<>();
			for(Entity outcome : _outcomes)
			{
				outcomes.add((Outcome) outcome);
			}
			
			//comparing
			for(Outcome outcome: outcomes)
			{
				Outcome oldOutcome = Schedule.trackedOutcomes.get(outcome.getId());
				if(outcome.getOdds() != oldOutcome.getOdds() || outcome.getThreshold() != oldOutcome.getThreshold())
				{
					outcome.setAwayTeam(oldOutcome.getAwayTeam());
					outcome.setHomeTeam(oldOutcome.getHomeTeam());
					outcome.setLeagueName(oldOutcome.getLeagueName());
					outcome.setParticipant(oldOutcome.getParticipant());
					Schedule.notificationQueue.add(outcome);
				}
			}
			
			try
			{
				Thread.sleep(60000);
			}
			catch(InterruptedException exp)
			{
				System.out.println("Thread Interrupted at Schedular");
			}
		}
	}

	@Override
	public void run()
	{
		schedule();
	}

	public static void putInTrackeEvents(long key, Outcome outcome)
	{
		Schedule.trackedOutcomes.put(key, outcome);
	}
	
	
}
