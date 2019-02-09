package com.ubaid.app.model.overunder;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ubaid.app.model.Converter;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;

/**
 * this class return the matches having over/under odds of multiple providers
 * @author UbaidurRehman
 *
 */
public class OverUnderConverter implements Converter
{

	public OverUnderConverter()
	{
		
	}

	public LinkedList<Match> _convert(long eventId, String homeTeam, String awayTeam, LinkedList<OverUnderRawData> overUnderRawData)
	{
		try
		{
			//linked list of match
			LinkedList<Match> matches = new LinkedList<>();
			Match match;
			
			//hash table which distinguish home team and away team
			Hashtable<String, Integer> localHash = new Hashtable<>();
			localHash.put(homeTeam, 1);
			localHash.put(awayTeam, 2);
			
			//infinite loop
			for (;;)
			{
				try
				{
				
					//removing the head of the list of raw data (coming from the builder)
					OverUnderRawData rawData = overUnderRawData.poll();
					
					//if list is empty then breaking loop
					if(rawData == null)
						break;
					
					//building a match (with following entities)
					match = new Match.Builder()
							.id(eventId)
							.homeTeam(homeTeam)
							.awayTeam(awayTeam)
							.providerId(rawData.getProviderId())
							.build();
					
					//getting a threshold from the raw data
					float threshold = rawData.getThreshold();
					
					//loop which will find the same threshold raw data					
					for(int i = 0; i < overUnderRawData.size(); i++)
					{
						//getting a raw data from the list (coming from builder)
						Entity entity2 = overUnderRawData.get(i);
						
						//up casting to OverUnderRawData
						OverUnderRawData rawData2 = (OverUnderRawData) entity2;
						
						
						//finding two events of same threshold
						//checking if threshold of first element is equal to the threshold of second element
						if(threshold == rawData2.getThreshold())
						{
							//outcome1 is for under
							//outcome2 is for over
							if(rawData.getTypeId() == OVERUNDER.OVER.getValue())
							{
								match.setOverOdds(rawData.getOdds());
								match.setOutcome2(rawData.getOutcomeId());

								match.setUnderOdds(rawData2.getOdds());
								match.setOutcome1(rawData2.getOutcomeId());

								match.setAwayTeamThreshold1(rawData.getThreshold());
								match.setHomeTeamThreshold1(rawData.getThreshold());
								
								matches.add(match);
								break;
								
							}
							else
							{
								match.setOverOdds(rawData2.getOdds());
								match.setOutcome2(rawData2.getOutcomeId());

								
								match.setUnderOdds(rawData.getOdds());
								match.setOutcome1(rawData.getOutcomeId());

								match.setAwayTeamThreshold1(rawData.getThreshold());
								match.setHomeTeamThreshold1(rawData.getThreshold());

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
	public LinkedList<Match> convert(long eventId, String homeTeam, String awayTeam, LinkedList<Entity> overUnderRawData)
	{
		Map<Long, List<OverUnderRawData>> map = getMap();
		//for loop which populate the map according to 
		//provider ids
		//provider id --> list[odds]
		int size = overUnderRawData.size();
		for(int i = 0; i < size; i++)
		{
			OverUnderRawData rawData = (OverUnderRawData) overUnderRawData.get(i);
			map.get(rawData.getProviderId()).add(rawData);
		}
		
		
		//getting list of list of rawdata
		List<List<OverUnderRawData>> rawdata_list = new LinkedList<>(map.values());
		
		//creating a list of match
		LinkedList<Match> matches = new LinkedList<>();
	
		//loop which 
		for(int i = 0; i < rawdata_list.size(); i++)
		{
			matches.addAll(_convert(eventId, homeTeam, awayTeam, (LinkedList<OverUnderRawData>) rawdata_list.get(i)));
		}

		
		return matches;
	}
	
	private Map<Long, List<OverUnderRawData>> getMap()
	{
		//if we have more providers then we have to expand our map
		Map<Long, List<OverUnderRawData>> map = new Hashtable<>();
		//creating two entities in the map
		map.put(3000368l, new LinkedList<>());
		map.put(3000107l, new LinkedList<>());		
		return map;
	}
	

}
