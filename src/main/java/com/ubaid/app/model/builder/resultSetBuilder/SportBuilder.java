package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Sport;

public class SportBuilder extends AbstractBuilder
{

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new Sport.Builder()
				.id(resultSet.getLong(1))
				.name(resultSet.getString(2))
				.build();
	}
	
}