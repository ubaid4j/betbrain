package com.ubaid.app.model.schedule;

import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubaid.app.controller.Controller;
import com.ubaid.app.model.logic.matchLogic.Logic;
import com.ubaid.app.model.logic.matchLogic.MatchLogic;
import com.ubaid.app.model.notification.Notification;
import com.ubaid.app.model.objects.Match;

public class TrackedMatchList implements Schedule, Runnable
{
	public static Hashtable<Long, Checked> hashtable = new Hashtable<>();
	public static Hashtable<Long, Match> trackedMatches = createTrackedMatchesList();
	int counter = 0;
	private Logic logic = new MatchLogic();
	ExecutorService service = null;
	Controller controller = Controller.getController();
	

	
	public TrackedMatchList()
	{
	
	}
	
	private static Hashtable<Long, Match> createTrackedMatchesList()
	{
		return new Hashtable<Long, Match>();
	}
	
	public static Checked ifChecked(boolean check)
	{
		if(check)
			return Checked.Checked;
		return null;
	}

	@Override
	public void schedule()
	{
		
		
		if(controller.isTerminate())
			if(service != null)
				service.shutdownNow();
		
		while(!controller.isTerminate())
		{


			Enumeration<Match> matches = TrackedMatchList.trackedMatches.elements();
			int size = TrackedMatchList.trackedMatches.size();
			
			while(true)
			{
				if(size > 0)
					break;
				size = TrackedMatchList.trackedMatches.size();		
				
				try
				{
					Thread.sleep(200);
				}
				catch(InterruptedException exp)
				{
					System.out.println("Chnage: Tracked");
					exp.printStackTrace();
				}
			}
			
			service = Executors.newFixedThreadPool(size);

			while(matches.hasMoreElements())
			{
				Match match = matches.nextElement();

				service.execute(new Runnable()
				{
					
					@Override
					public void run()
					{
						if(match.compare())
						{
							Match newMatch = match.getCurrentMatch();
							try
							{
								newMatch.setTimestamp(new Timestamp(System.currentTimeMillis()));
								logic.addChangedOddsMatch(newMatch);
								System.out.println(newMatch);
								Notification.notificationQueue.put(newMatch);
							}
							catch (InterruptedException e)
							{
								e.printStackTrace();
							}
						}
					}
				});
			}
			
			
			service.shutdown();
			System.out.println("Size of notification " + size);

			
			try
			{
				Thread.sleep(60000);
			}
			catch (InterruptedException e)
			{
				System.out.println("Changa[Line 115 Tracked Match List]");
			}

									
		}
	}

	@Override
	public void run()
	{
		schedule();
	}

	@Override
	public void stop()
	{
		
	}

	@Override
	public void resume()
	{
		
	}
}
