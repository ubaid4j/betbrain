package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.asianhandicap.AssianHandicapRawData;
import com.ubaid.app.model.objects.Entity;

public class AssianHandicapBuilder extends AbstractBuilder
{

	public AssianHandicapBuilder()
	{
	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new AssianHandicapRawData.Builder()
					.odds(resultSet.getFloat(1))
					.participant(resultSet.getString(2))
					.threshold(resultSet.getFloat(3))
					.outcomeId(resultSet.getLong(4))
					.build();
	}

}
