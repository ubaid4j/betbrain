package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.RegisteredOutcomeFactory;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.singleton.DataSource;

public class RegisteredOutcomeDAO extends AbstractDAO
{


	private static final String query = "INSERT INTO registeredOutcome VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String query1 = "SELECT * FROM registeredOutcome limit 10"; //TODO remove limit
	private static final String delQuery = "DELETE FROM registeredOutcome WHERE id = ? and providerId = ?";
	private static final String delAllQuery = "DELETE FROM registeredOutcome";
	
	public RegisteredOutcomeDAO()
	{
	}

	@Override
	String getQuery(QT type)
	{
		return query1;
	}

	@Override
	public boolean add(Entity entity) throws ServletException
	{
		try
		{
			Outcome outcome = (Outcome) entity;
			
			Connection con = DataSource.getConnection();
			PreparedStatement st = con.prepareStatement(query);
			st.setLong(1, outcome.getId());
			st.setLong(2, outcome.getProviderId());
			st.setLong(3, -1);
			st.setFloat(4, outcome.getOdds());
			st.setFloat(5, outcome.getThreshold());
			st.setString(6, outcome.getLeagueName());
			st.setString(7, outcome.getMatchName());
			st.setString(8, outcome.getParticipant());
			st.setString(9, outcome.getHomeTeam());
			st.setString(10, outcome.getAwayTeam());
			st.setTimestamp(11, outcome.getRegisterTime());
			st.setTimestamp(12, outcome.getChangedTime());
			st.setString(13, outcome.getBettingType().toString());
			st.executeUpdate();
			
			return true;
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteById(long id, long providerId)
	{
		try
		{
			Connection con = DataSource.getConnection();
			PreparedStatement st = con.prepareStatement(delQuery);
			st.setLong(1, id);
			st.setLong(2, providerId);
			st.executeUpdate();
			return true;
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return false;
	}

	@Override
	public LinkedList<Entity> getAll()
	{
		return super.getAll();
	}

	
	
	@Override
	AbstractFactory getFactory()
	{
		return new RegisteredOutcomeFactory();
	}

	@Override
	public boolean deleteAll()
	{
		try
		{
			Connection con = DataSource.getConnection();
			PreparedStatement st = con.prepareStatement(delAllQuery);
			st.executeUpdate();
		}
		catch(SQLException exp)
		{
			return false;
		}
		
		return true;
	}
	
	
	

}
