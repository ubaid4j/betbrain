package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.thresholdDetection.TrackedMatches;

public class TrackedMatchBuilder extends AbstractBuilder
{

	public TrackedMatchBuilder()
	{

	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new TrackedMatches.Builder()
				.matchId(resultSet.getLong(1))
				.homeTeam(resultSet.getString(3))
				.awayTeam(resultSet.getString(4))
				.leagueName(resultSet.getString(5))
				.sportName(resultSet.getString(6))
				.build();
	}

}
