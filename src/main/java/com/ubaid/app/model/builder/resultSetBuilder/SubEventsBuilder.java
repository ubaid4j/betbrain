package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.SubEvents;

public class SubEventsBuilder extends AbstractBuilder
{

	public SubEventsBuilder()
	{

	}

/*	
	@Override
	public LinkedList<Entity> build(ResultSet resultSet)
	{
		LinkedList<Entity> list = new LinkedList<>();
		
		try
		{
			while(resultSet.next())
			{
				SubEvents event = new SubEvents.Builder()
									.startTime(resultSet.getTimestamp(2))
									.id(resultSet.getLong(1))
									.eventName(resultSet.getString(3))
									.roleId(resultSet.getInt(4))
									.roleName(resultSet.getString(5))
									.build();
				list.add(event);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;

	}
*/
	
	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new SubEvents.Builder()
				.startTime(resultSet.getTimestamp(2))
				.id(resultSet.getLong(1))
				.eventName(resultSet.getString(3))
				.roleId(resultSet.getInt(4))
				.roleName(resultSet.getString(5))
				.build();
	}

}
