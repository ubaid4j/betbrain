package com.ubaid.app.model.objects;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Convert_ implements Converter
{

	
	Convert_()
	{
	}

	@Override
	public List<Match> convert(List<SubEvents> events_l, int eventPartId, int bettingTypeId)
	{
		int size = events_l.size();
		List<Match> matchs = new Vector<Match>();

		Vector<SubEvents> events = new Vector<>();
		
		for(SubEvents subEvent: events_l)
		{
			events.add(subEvent);
		}
		
		ExecutorService service = Executors.newFixedThreadPool(size);
		
		for(int i = 0; i < size; i++)
		{
			
			service.execute(new Runnable()
			{
				
				@Override
				public void run()
				{
					try
					{								
						SubEvents event_1 = events.get(0);
						SubEvents event_2 = null;
						events.remove(event_1);
						long id = event_1.getId();
						
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
						
						match.setId(event_1.getId());
						match.setStartTime(event_1.getStartTime());
						match.execute(eventPartId, bettingTypeId);
						matchs.add(match);
						
						
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
		
		service.shutdown();
		
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
