package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Odds;

public class OddsBuilder extends AbstractBuilder
{

	public OddsBuilder()
	{

	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new Odds.Builder()
				.odds(resultSet.getFloat(1))
				.name(resultSet.getString(2))
				.threshold(resultSet.getFloat(3))
				.outcomeId(resultSet.getLong(4))
				.providerId(resultSet.getLong(5))
				.build();
	}

}
