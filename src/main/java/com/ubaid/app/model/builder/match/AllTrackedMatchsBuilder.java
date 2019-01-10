package com.ubaid.app.model.builder.match;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;

import com.ubaid.app.model.objects.Match;

public class AllTrackedMatchsBuilder implements AllTrackedMatchBuilder
{

	public AllTrackedMatchsBuilder()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public LinkedList<Match> build(ResultSet rs)
	{
		try
		{			
			LinkedList<Match> matchs = new LinkedList<>();
			while(rs.next())
			{
				long tournamentId = rs.getLong(2);
				long matchid = rs.getLong(3);
				String homeTeam = rs.getString(4);
				String awayTeam = rs.getString(5);
				float homeTeamOdds = rs.getFloat(6);
				float drawOdds = rs.getFloat(7);
				float awayTeamOdds = rs.getFloat(8);
				Timestamp timestamp = rs.getTimestamp(9);
				
				Match match = new Match.Builder()
									.id(matchid)
									.awayTeam(awayTeam)
									.homeTeam(homeTeam)
									.homeTeamOdds(homeTeamOdds)
									.awayTeamOdds(awayTeamOdds)
									.drawOdds(drawOdds)
									.build();
				
				match.setTimestamp(timestamp);
				match.setTournamentId(tournamentId);
				
				matchs.add(match);
			}
			
			return matchs;
			
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return null;
	}

}
