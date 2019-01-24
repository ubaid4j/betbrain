package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.RemovedOutcomeFactory;
import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.singleton.DataSource;

public class RemovedOutcomeDAO extends AbstractOutcomeDAO
{
	
	private static final String getQuery = "select id, odds, threshold,"
			+ " leagueName, homeTeam, awayTeam, participant, "
			+ "removedTime, bettingTypeId, sportName " + 
				"from removedOutcomes;";

	private static String insertQuery = "INSERT INTO removedOutcomes VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"; 
	
	public RemovedOutcomeDAO()
	{
		
	}

	@Override
	public AbstractFactory getFactory()
	{
		return new RemovedOutcomeFactory();
	}

	@Override
	public boolean addAll(List<Outcome> outcomes)
	{
		try
		{
			Connection con = DataSource.getConnection();
			PreparedStatement st = con.prepareStatement(insertQuery);
			
			for(Outcome outcome : outcomes)
			{
				try
				{
					st.setLong(1, outcome.getId());
					st.setLong(2, -1);
					st.setFloat(3, outcome.getOdds());
					st.setFloat(4, outcome.getThreshold());
					st.setString(5, outcome.getLeagueName());
					st.setString(6, outcome.getHomeTeam());
					st.setString(7, outcome.getAwayTeam());
					st.setString(8, outcome.getParticipant());
					st.setTimestamp(9, outcome.getChangedTime());
					st.setString(10, outcome.getBettingType().toString());
					st.setString(11, outcome.getSportName());
					st.addBatch();
					
				}
				catch(SQLException exp)
				{
					exp.printStackTrace();
				}
			}
			
			st.executeBatch();
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public LinkedList<Entity> getAll()
	{
		ResultSetBuilder builder = (ResultSetBuilder) getFactory().getBuilder();

		try
		{
			Connection con = DataSource.getConnection();
			PreparedStatement st = con.prepareStatement(getQuery);
			return builder.build(st.getResultSet());			
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
			return null;
		}
	}
	
	
	
	

}
