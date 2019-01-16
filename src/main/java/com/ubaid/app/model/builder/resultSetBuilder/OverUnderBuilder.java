package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.overunder.OverUnderRawData;

public class OverUnderBuilder extends AbstractBuilder
{

	public OverUnderBuilder()
	{
	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new OverUnderRawData.Builder()
					.odds(resultSet.getFloat(1))
					.typeId(resultSet.getLong(2))
					.threshold(resultSet.getFloat(3))
					.outcomeId(resultSet.getLong(4))
					.build();
	}
}
