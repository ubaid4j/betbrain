package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.BettingType;
import com.ubaid.app.model.schedule1_1.Outcome;

public class RemovedOutcomeBuilder extends AbstractBuilder
{
	
	public RemovedOutcomeBuilder()
	{
		
	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new Outcome.Builder()
				.id(resultSet.getLong(1))
				.odds(resultSet.getFloat(2))
				.threshold(resultSet.getFloat(3))
				.leagueName(resultSet.getString(4))
				.homeTeam(resultSet.getString(5))
				.awayTeam(resultSet.getString(6))
				.participant(resultSet.getString(7))
				.changedTime(resultSet.getTimestamp(8))
				.bettingType(BettingType.valueOf(resultSet.getString(9)))
				.sportName(resultSet.getString(10))
				.build();
	}

}
