package com.ubaid.app.model.schedule1_1.thresholdDetection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.SportUtil;
import com.ubaid.app.model.SportUtilFactory;
import com.ubaid.app.model.logic.AHOUOutcomeLogic;
import com.ubaid.app.model.logic.OutcomeLogic;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Outcome;
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
		while(true)
		{
			if(Schedule.trackedMatches.size() > 0)
			{
				schedule();
			}
			
			try
			{
				Thread.sleep(500);
			}
			catch(InterruptedException exp)
			{
				exp.printStackTrace();
			}
		}
	}

	@Override
	public void schedule()
	{
		//here will be logic, how to detect threshold 
 		SportUtil su = SportUtilFactory.getSportUtil();
		
 		while(true)
 		{
 			//first we will check how many are sports in the TrackedMatch are not null
 			for(SportType sportType : SportType.values())
 			{
 				Hashtable<Long, TrackedMatches> trHashtable = Schedule.trackedMatches.get(sportType.getValue());
 				
 				if(trHashtable != null)
 				{
 					//so, here is the hashtable in which a match id -> tracked match are present
 					//so, here we have to collect all the ids of this hashtable
 					//the result will contain matchId, outcomeId,  odds, threshold, bettingType, participant, OUType
 					//stead we build an outcome which contain the above attributes

 					Set<Long> _ids = trHashtable.keySet();
 					long[] ids = new long[_ids.size()];
 					int index = 0;
 					for(long id : _ids)
 					{
 						ids[index++] = id;
 					}

 					OutcomeLogic logic = new AHOUOutcomeLogic();
 					List<Entity> _outcomes = logic.getAll(ids, su.getAHOUEventPartId(sportType.toString()));
 					List<Outcome> newOutcomes = new LinkedList<>();
 					 					
 					for(Entity entity: _outcomes)
 					{
 						newOutcomes.add((Outcome) entity);
 					}
 					
 					//comparing info
 					//we have two options

 					//as _outcomes contain all outcomes of a matches of the same sport
 					//so we can compare all the outcomes of the registered matches with _outcomes
 					//if a any detection will be find from both side (added or removed) 
 					//then push notification
 					//and accordingly add or remove
 					
 					//we will do it using streams
 					Enumeration<TrackedMatches> matches = trHashtable.elements();
 					List<Outcome> oldOutcomes = new LinkedList<>();

 					//here we will make a map which represent matchId -> outcome
 					Map<Long, Outcome> matchId_outcome_map = new HashMap<>();
 					
 					while(matches.hasMoreElements())
 					{
 						TrackedMatches match = matches.nextElement();
 						matchId_outcome_map.put(match.getMatchId(), match.getOutcomes().get(0));
 						oldOutcomes.addAll(match.getOutcomes());
 					}
 					
 					//so here we have both oldOutcomes and newOutcomes of all matches of same sport
 					
 					//now we will convert them into Map
 					Map<Long, Outcome> oldOutcomeMap = new HashMap<Long, Outcome>();
 					Map<Long, Outcome> newOutcomeMap = new HashMap<Long, Outcome>();
 					
 					List<Long> oldOutcomeIds = new ArrayList<>();
 					List<Long> newOutcomeIds = new ArrayList<>();

 					for(Outcome outcome : newOutcomes)
 					{
 						newOutcomeMap.put(outcome.getId(), outcome);
 						newOutcomeIds.add(outcome.getId());
 					}
 					
 					for(Outcome outcome : oldOutcomes)
 					{
 						oldOutcomeMap.put(outcome.getId(), outcome);
 						oldOutcomeIds.add(outcome.getId());
 					}
 					
 					HashSet<Long> oldOutcomeSet = new HashSet<>(oldOutcomeIds);
 					HashSet<Long> newOutcomeSet = new HashSet<>(newOutcomeIds);
 					
 					newOutcomeSet.removeAll(oldOutcomeIds);
 					oldOutcomeSet.removeAll(newOutcomeIds);
 					
 					List<Long> newlyAddedOutcome = new ArrayList<>(newOutcomeSet);
 					List<Long> deletedOutcome = new ArrayList<>(oldOutcomeSet);
 					
 					//at here we have outcomes ids of newly added and deleted outcomes

 					//we need the following thing for push notification
 					//1. leagueName				will find from the matchId
 					//2. homeTeam				//
 					//3. awayTeam				//
 					//4. Match Name				//
 					//5. old threshold			No need
 					//6. threshold				from new outcome
 					//7. outcome id				from new outcome
 					//8. participant			from new outcome
 					//9. odds					from new outcome
 					//10. old odds				no need
 					//11. lastUpdateTime 		from system
 					
 					//so the question arise, how to find outcome from matchId
 					//as we know the matchId from the newly outcome
 					//we get the outcome from the map
 					
 					//how to make a map which represents matchId -> outcome //done

 					//now we want to produce the fully fledged outcome
 					//update this newly and deleted outcome in the hashtable

 					for(long newAddedOutcomeId : newlyAddedOutcome)
 					{ 				
 						Outcome outcome = newOutcomeMap.get(newAddedOutcomeId);
 						long matchId = outcome.getMatchId();
 						Outcome prototype = matchId_outcome_map.get(matchId);
 						outcome.setLeagueName(prototype.getLeagueName());
 						outcome.setHomeTeam(prototype.getHomeTeam());
 						outcome.setAwayTeam(prototype.getAwayTeam());
 						outcome.setMatchName(prototype.getMatchName());
 						outcome.setOldOdds(0);
 						outcome.setOldThreshold(0);
 						outcome.setChangedTime(new Timestamp(System.currentTimeMillis()));
 						outcome.setStatus("New Added");
 						trHashtable.get(matchId).addOutcome(outcome);
 						
 	 					Schedule.notificationQueue.add(outcome);
 						
 					}
 					
 					for(long deletedOutcomeId : deletedOutcome)
 					{
 						Outcome outcome = oldOutcomeMap.get(deletedOutcomeId);
 						long matchId = outcome.getMatchId();
 						trHashtable.get(matchId).removeOutcome(outcome);
 						outcome.setStatus("Removed"); 						
 						Schedule.notificationQueue.add(outcome);
 					}
 					
 					
 					//general info
 					//here comes how to check new outcome: 
 					//we will match outcome ids, from the given tracked match and from the coming outcome.
 					//if a new outcome comes, then it will added in the outcome list 
 					//if tracked match outcome has greater than comming outcome then 
 					//we will mark this outcome as inactive and 
 					//replace this outcome
 				}
 			}
 			
 		}
		
		
	}
	
	@SuppressWarnings("unused")
	private void call()
	{
		System.out.println("------------");
	}
	
	public static void putInTrackeEvents(long key,String sportName, TrackedMatches trackedMatch)
	{
		SportUtil su = SportUtilFactory.getSportUtil();
		int id = su.getSportId(sportName);
		if(id != -1)
		{
			Hashtable<Long, TrackedMatches> trHashtable = Schedule.trackedMatches.get(id);
			if(trHashtable == null)
				trHashtable = new Hashtable<>();
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
