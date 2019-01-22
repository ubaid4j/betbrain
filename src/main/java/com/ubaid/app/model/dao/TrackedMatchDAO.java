package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.TrackedMatchFactory;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.thresholdDetection.TrackedMatches;
import com.ubaid.app.model.singleton.DataSource;

public class TrackedMatchDAO extends AbstractDAO
{

	private static final String query = "INSERT INTO trackedMatch(id, homeTeam, awayTeam, leagueName, sportName) VALUES(?, ?, ?, ?, ?)";
	private static final String delQuery = "DELETE FROM trackedMatch where id = ?";
	private static final String getAllQuery = "SELECT * FROM trackedMatch";
	
	public TrackedMatchDAO()
	{
	}

	@Override
	String getQuery(QT type)
	{
		return getAllQuery;
	}

	@Override
	AbstractFactory getFactory()
	{
		return new TrackedMatchFactory();
	}

	@Override
	public boolean add(Entity entity) throws ServletException
	{
		try
		{
			TrackedMatches trackedMatch = (TrackedMatches) entity;
			Connection con = DataSource.getConnection();
			PreparedStatement st = con.prepareStatement(query);
			st.setLong(1, trackedMatch.getMatchId());
			st.setString(2, trackedMatch.getHomeTeam());
			st.setString(3, trackedMatch.getAwayTeam());
			st.setString(4, trackedMatch.getLeagueName());
			st.setString(5, trackedMatch.getSportName());
			st.executeUpdate();
		
		}
		catch(SQLException exp)
		{
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteById(long id)
	{
		try
		{
			Connection con = DataSource.getConnection();
			PreparedStatement st = con.prepareStatement(delQuery);
			st.setLong(1, id);
			st.executeUpdate();
		}
		catch(SQLException exp)
		{
			return false;
		}
		
		return true;
	}
}
