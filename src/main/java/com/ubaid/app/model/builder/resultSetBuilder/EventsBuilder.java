package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Events;

public class EventsBuilder extends AbstractBuilder
{

	public EventsBuilder()
	{
	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return  new Events.Builder()
				.eventName(resultSet.getString(1))
				.locationName(resultSet.getString(3))
				.id(resultSet.getLong(2))
				.build();
	}

}
