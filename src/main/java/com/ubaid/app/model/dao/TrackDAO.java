package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.TrackFactory;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.eventParticipant.Track;
import com.ubaid.app.model.singleton.DataSource;

public class TrackDAO extends AbstractDAO
{
	
	private static final String query = "SELECT matchId, parentMatchId, bettingType from track";
	private static final String deleteQuery = "DELETE FROM track where id = ?";
	private static final String addQuery = "INSERT INTO trackedMatch(id, parentMatchId, matchId, bettingType) VALUES(?, ?, ?, ?)";
	
	
	@Override
	String getQuery(QT type)
	{
		return query;
	}

	@Override
	public boolean deleteById(long id)
	{

		try
		{
			Connection con = DataSource.getConnection();
			PreparedStatement statement = con.prepareStatement(deleteQuery);
			statement.setLong(1, id);
			int result = statement.executeUpdate();
			if(result > 0)
				return true;
			
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean add(Entity entity) throws ServletException
	{
		boolean flag = false;
		
		try
		{
			Track track = (Track) entity;
			long id = track.getId();
			long parentMatchId = track.getParentMatchId();
			long matchId = track.getMatchId();
			int bettingType = track.getBettingType();
			Connection con = DataSource.getConnection();
			PreparedStatement statement = con.prepareStatement(addQuery);
			statement.setLong(1, id);
			statement.setLong(2, parentMatchId);
			statement.setLong(3, matchId);
			statement.setInt(4, bettingType);
			
			int row = statement.executeUpdate();
			if(row > 0)
				flag = true;
		}
		catch(SQLException exp)
		{
			System.out.println(exp.getMessage());
			throw new ServletException(exp.getMessage());
		}
		
		return flag;
	}

	@Override
	public LinkedList<Entity> getAll()
	{
		return super.getAll();
	}

	@Override
	public LinkedList<Entity> getAll(SportType type)
	{
		throw new IllegalAccessError("Method Not Supported");
	}

	@Override
	public LinkedList<Entity> getAll(long id)
	{
		throw new IllegalAccessError("Method Not Supported");
	}

	@Override
	AbstractFactory getFactory()
	{
		return new TrackFactory();
	}

}
