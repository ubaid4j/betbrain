package com.ubaid.app.model.dao.matchDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.AllMatchFactory;
import com.ubaid.app.model.abstractFactory.MatchFactory;
import com.ubaid.app.model.builder.match.AllTrackedMatchBuilder;
import com.ubaid.app.model.builder.match.MatchBuilder;
import com.ubaid.app.model.objects.Match;
import com.ubaid.app.model.singleton.DataSource;

public class MatchDAO implements DAO
{
	AbstractFactory factory = null;
	MatchBuilder builder;
	private final static String query = "INSERT INTO trackHistory VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String getAll = "SELECT * FROM trackHistory";
	
	public MatchDAO()
	{
		factory = new MatchFactory();
		builder = (MatchBuilder) factory.getBuilder();
	}
	
	@Override
	public Match getMatch(long id)
	{
		return builder.build(id);
	}

	@Override
	public boolean addChangedOddsMatch(Match match)
	{
		try
		{
			System.out.println("Add track history in table");
			Connection con = DataSource.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setLong(1, -1);
			statement.setLong(2, -1);
			statement.setLong(3, match.getId());
			statement.setString(4, match.getHomeTeam());
			statement.setString(5, match.getAwayTeam());
			statement.setFloat(6, match.getHomeTeamOdds());
			statement.setFloat(7, match.getAwayTeamOdds());
			statement.setFloat(8, match.getDrawOdds());
			statement.setTimestamp(9, match.getTimestamp());
			statement.executeUpdate();
			return true;
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		catch(NullPointerException exp)
		{
			exp.printStackTrace();
		}
		
		return false;
	}

	@Override
	public LinkedList<Match> getAllMatches()
	{
		try
		{
			Connection con = DataSource.getConnection();
			PreparedStatement statement = con.prepareStatement(getAll);
			ResultSet rs = statement.executeQuery();
			AbstractFactory factory = new AllMatchFactory();
			AllTrackedMatchBuilder builder = (AllTrackedMatchBuilder) factory.getBuilder();
			return builder.build(rs);

		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
			throw new IllegalArgumentException();
		}
		
	}

}
