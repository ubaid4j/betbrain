package com.ubaid.app.model.overunder;

import java.util.Hashtable;
import java.util.LinkedList;

import com.ubaid.app.model.Converter;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;

public class OverUnderConverter implements Converter
{

	public OverUnderConverter()
	{
		
	}

	@Override
	public LinkedList<Match> convert(long eventId, String homeTeam, String awayTeam, LinkedList<Entity> overUnderRawData)
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
					OverUnderRawData rawData = (OverUnderRawData) overUnderRawData.poll();
					
					//if list is empty then breaking loop
					if(rawData == null)
						break;
					
					//building a match (with following entities)
					match = new Match.Builder()
							.id(eventId)
							.homeTeam(homeTeam)
							.awayTeam(awayTeam)
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
							//finding the over odd and under odd
							//larger coupon number will be under
							//under will have outcome1
							//smaller coupon number will be over
							//over will have outcome2
							
							if(rawData.getKey() < rawData2.getKey())
							{
								//setting over odds to match of rawData (having smaller key)
								match.setOverOdds(rawData.getOdds());
								match.setUnderOdds(rawData2.getOdds());
								match.setAwayTeamThreshold1(rawData.getThreshold());
								match.setHomeTeamThreshold1(rawData.getThreshold());
								match.setOutcome1(rawData2.getOutcomeId());
								match.setOutcome2(rawData.getOutcomeId());
								matches.add(match);
								break;
							}
							else
							{
								//setting over odds to match of rawData2 (having smaller key)
								match.setOverOdds(rawData2.getOdds());
								match.setUnderOdds(rawData.getOdds());
								match.setAwayTeamThreshold1(rawData.getThreshold());
								match.setHomeTeamThreshold1(rawData.getThreshold());
								match.setOutcome1(rawData.getOutcomeId());
								match.setOutcome2(rawData2.getOutcomeId());
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
	

}
