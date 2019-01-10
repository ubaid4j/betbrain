package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.WelcomeEvents;

public class WelcomeEventBuilder extends AbstractBuilder
{
	
	
/*	
	@Override
	public LinkedList<Entity> build(ResultSet resultSet)
	{
		LinkedList<Entity> entities = new LinkedList<Entity>();
		System.out.println(resultSet.toString());
		System.out.println("Welcome in builder");
		try
		{
	
			while(resultSet.next())
			{
				try
				{
					Entity entity = new WelcomeEvents.Builder()
							.bettingTypeId(resultSet.getLong(1))
							.bettingTypeName(resultSet.getString(2))
							.odds(resultSet.getFloat(3))
							.ParticipantName(resultSet.getString(4))
							.eventID(resultSet.getLong(5))
							.lastChangedTime(resultSet.getTimestamp(6))
							.lastCollectedTime(resultSet.getTimestamp(7))
							.outComeTypeName(resultSet.getString(8))
							.threshold(resultSet.getFloat(9))
							.sportName(resultSet.getString(10))
							.eventStartTime(resultSet.getTimestamp(11))
							.eventPartId(resultSet.getInt(12))
							.build();
					System.out.println(entity.toString());
					entities.add(entity);
				}
				catch(SQLException exp)
				{
					exp.printStackTrace();
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		
		return entities;
	}
*/
	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new WelcomeEvents.Builder()
				.bettingTypeId(resultSet.getLong(1))
				.bettingTypeName(resultSet.getString(2))
				.odds(resultSet.getFloat(3))
				.ParticipantName(resultSet.getString(4))
				.eventID(resultSet.getLong(5))
				.lastChangedTime(resultSet.getTimestamp(6))
				.lastCollectedTime(resultSet.getTimestamp(7))
				.outComeTypeName(resultSet.getString(8))
				.threshold(resultSet.getFloat(9))
				.sportName(resultSet.getString(10))
				.eventStartTime(resultSet.getTimestamp(11))
				.eventPartId(resultSet.getInt(12))
				.build();
	}

}
