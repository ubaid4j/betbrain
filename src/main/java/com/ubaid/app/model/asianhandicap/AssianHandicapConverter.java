package com.ubaid.app.model.asianhandicap;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ubaid.app.model.Converter;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;

public class AssianHandicapConverter implements Converter
{

	public AssianHandicapConverter()
	{
	}

	private LinkedList<Match> _convert(long eventId, String homeTeam, String awayTeam, LinkedList<AssianHandicapRawData> assianHandicapRawData )
	{
		try
		{
			//list of matches
			LinkedList<Match> matches = new LinkedList<>();
			Match match;

			//hash table which tells the home, away team
			Hashtable<String, Integer> localHash = new Hashtable<>();
			localHash.put(homeTeam, 1);
			localHash.put(awayTeam, 2);
			
			//infinite loop
			for (;;)
			{
				try
				{
					//getting head of the list (coming from the builder)
					AssianHandicapRawData rawData = (AssianHandicapRawData) assianHandicapRawData.poll();

					//if list is empty then breaking the loop
					if(rawData == null)
						break;
					
					//building a match with basic stuff
					match = new Match.Builder()
							.id(eventId)
							.homeTeam(homeTeam)
							.awayTeam(awayTeam)
							.providerId(rawData.getProviderId())
							.build();
					
					//getting threshold from the rawData
					float threshold = Math.abs(rawData.getThreshold());
					
					//getting odds from the rawData
					float odds = rawData.getOdds();
					
					//checking the type of odds (home odds, or away odds)
					if(localHash.get(rawData.getParticipant()) == 1)
					{
						match.setHomeTeamThreshold1(rawData.getThreshold());
					}
					else if(localHash.get(rawData.getParticipant()) == 2)
					{
						match.setAwayTeamThreshold1(rawData.getThreshold());
					}
					
					//internal loop which determine the same threshold of rawData from the remaining events
					for(int i = 0; i < assianHandicapRawData.size(); i++)
					{
						
						Entity entity2 = assianHandicapRawData.get(i);
						AssianHandicapRawData rawData2 = (AssianHandicapRawData) entity2;
						
						if(threshold == Math.abs(rawData2.getThreshold()))
						{
							int status = localHash.get(rawData2.getParticipant());
							if(status == 1)
							{
								match.setHomeTeamOdds(rawData2.getOdds());
								match.setHomeTeamThreshold1(rawData2.getThreshold());
								match.setAwayTeamOdds(odds);
								match.setOutcome1(rawData2.getOutcomeId());
								match.setOutcome2(rawData.getOutcomeId());
								assianHandicapRawData.remove(rawData2);
								matches.add(match);
								break;
							}
							else if(status == 2)
							{
								match.setAwayTeamOdds(rawData2.getOdds());
								match.setAwayTeamThreshold1(rawData2.getThreshold());
								match.setHomeTeamOdds(odds);
								match.setOutcome1(rawData.getOutcomeId());
								match.setOutcome2(rawData2.getOutcomeId());
								assianHandicapRawData.remove(rawData2);
								matches.add(match);
								break;
							}
							
								
						}
					}
					
				}
				catch(NullPointerException exp)
				{
					System.out.println("Loop Ended");
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
			
			return matches;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		
		return null;
		
	}
	
	
	@Override
	public LinkedList<Match> convert(long eventId, String homeTeam, String awayTeam, LinkedList<Entity> assianHandicapRawData)
	{
		//getting Map
		Map<Long, List<AssianHandicapRawData>> map = getMap();
		//for loop which populate the map according to 
		//provider ids
		//provider id --> list[Assian Handicap RawData]
		int size = assianHandicapRawData.size();
		for(int i = 0; i < size; i++)
		{
			AssianHandicapRawData rawData = (AssianHandicapRawData) assianHandicapRawData.get(i);
			map.get(rawData.getProviderId()).add(rawData);
		}
		
		
		//getting list of list of rawdata
		List<List<AssianHandicapRawData>> rawdata_list = new LinkedList<>(map.values());
		
		//creating a list of match 
		LinkedList<Match> matches = new LinkedList<>();
	
		//loop which which collect the matches (assian handicap) of particular provider
		//and add them in the list of match
		for(int i = 0; i < rawdata_list.size(); i++)
		{
			matches.addAll(_convert(eventId, homeTeam, awayTeam, (LinkedList<AssianHandicapRawData>) rawdata_list.get(i)));
		}

		
		return matches;
		
	}
	
	private Map<Long, List<AssianHandicapRawData>> getMap()
	{
		//if we have more providers then we have to expand our map
		Map<Long, List<AssianHandicapRawData>> map = new Hashtable<>();
		//creating two entities in the map
		map.put(3000368l, new LinkedList<>());
		map.put(3000107l, new LinkedList<>());		
		return map;
	}


}
