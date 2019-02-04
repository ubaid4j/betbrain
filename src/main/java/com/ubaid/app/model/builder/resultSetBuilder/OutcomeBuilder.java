package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Outcome;

public class OutcomeBuilder extends AbstractBuilder
{

	public OutcomeBuilder()
	{
		
	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new Outcome.Builder()
					.threshold(resultSet.getFloat(1))
					.odds(resultSet.getFloat(2))
					.id(resultSet.getLong(3))
					.providerId(resultSet.getLong(4))
					.build();
	}

}
