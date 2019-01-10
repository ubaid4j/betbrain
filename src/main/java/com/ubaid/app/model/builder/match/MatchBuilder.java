package com.ubaid.app.model.builder.match;

import java.util.LinkedList;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;
import com.ubaid.app.model.objects.Odds;
import com.ubaid.app.model.objects.eventParticipant.EventParticipent;
import com.ubaid.app.model.objects.eventParticipant.ParticipantRole;

public class MatchBuilder extends AbstractBuilder
{

	

	private Match getMatch(long match_id)
	{
		Match match = new Match.Builder().build();

		match.setId(match_id);
		LinkedList<Entity> partial_matches = oddsLogic.getAll(match_id);


		for(int j = 0; j < partial_matches.size(); j++)
		{
			Odds partial_match = (Odds) partial_matches.get(j);
			if(partial_match.getName() == null)
			{
				match.setDrawOdds(partial_match.getOdds());
				continue;
			}
				
			EventParticipent eventParticipent = new EventParticipent.Builder()
					.eventId(match_id)
					.participantName(partial_match.getName())
					.build();

			ParticipantRole role = (ParticipantRole) roleLogic.get(eventParticipent);
			
			if(role.getRole() == 1)
			{
				match.setHomeTeam(partial_match.getName());
				match.setHomeTeamOdds(partial_match.getOdds());
			}
			else if(role.getRole() == 2)
			{
				match.setAwayTeam(partial_match.getName());
				match.setAwayTeamOdds(partial_match.getOdds());
			}
		}						
		
		return match;
	}

	@Override
	public Match build(long id)
	{
		return getMatch(id);
	}


}
