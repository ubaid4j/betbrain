package com.ubaid.app.model.objects;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * this class accept a list of partial matches 
 * and return a list of complete matches having odds (home/draw/away) 
 * @author UbaidurRehman
 *
 */
public class Convert_ implements Converter
{

	
	Convert_()
	{
	}

	@Override
	public List<Match> convert(List<SubEvents> events_l, int eventPartId, int bettingTypeId)
	{
		//first getting size of partial matches
		//[see defination of partial matches at SubEvent strategy at strategy packge]
		int size = events_l.size();
		
		//creating a vector of complete matches [to avoid any concurrency issue]
		List<Match> matchs = new Vector<Match>();

		//a vector of events
		Vector<SubEvents> events = new Vector<>();
		
		//populating
		for(SubEvents subEvent: events_l)
		{
			events.add(subEvent);
		}
		
		//creating a thread pool
		ExecutorService service = Executors.newFixedThreadPool(size);
		
		//loop of size of partial matches
		for(int i = 0; i < size; i++)
		{
			//new thread (anonymous)
			service.execute(new Runnable()
			{
				
				@Override
				public void run()
				{
					try
					{				
						//getting an partial match [arbitrary let say first one]
						//when list becomes empty then it throw exception and 
						//task finished
						SubEvents event_1 = events.get(0);
						
						//declaring second partial match 
						SubEvents event_2 = null;
						
						//removing the first partial match from the vector of partial matches
						events.remove(event_1);
						
						//id of partial match
						long id = event_1.getId();
						
						//this loop getting the same subEvent from the list
						//to make a complete match 
						int counter = 0;
						while(true)
						{
							if(events.get(counter++).getId() == id)
							{
								event_2 = events.remove(--counter);
								counter = 0;
								break;
							}
						}
					
						//---> till this we have two parts of an match
						//---> one part has home team and second has away team
						//---> now merging them on their role
						
						Match match = new Match();
						
						if(event_1.getRoleId() == 1)
						{
							match.setHomeTeam(event_1.getEventName());
						}
						else if(event_1.getRoleId() == 2)
						{
							match.setAwayTeam(event_1.getEventName());
						}

						if(event_2.getRoleId() == 1)
						{
							match.setHomeTeam(event_2.getEventName());
						}
						else if(event_2.getRoleId() == 2)
						{
							match.setAwayTeam(event_2.getEventName());
						}
						
						//---> till here we have merged home team and away team in a match
						
						//setting id of match
						match.setId(event_1.getId());
						//start time
						match.setStartTime(event_1.getStartTime());
						
						//now we will call execute method of this match 
						//to full fill in this match odds of type home/draw/away
						List<Match> matches = match.execute(eventPartId, bettingTypeId);
						
						//adding match in the list of matches
						matchs.addAll(matches);
						
						
					}
					catch(IndexOutOfBoundsException exp)
					{
						//out of this function

					}	
					catch(NullPointerException exp)
					{
						
					}
				}
			});
		}
		
		//shutting down service
		service.shutdown();
		
		//waiting for service to complete its task
		while(true)
			if(service.isTerminated())
				return matchs;
			else
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
	}

}
