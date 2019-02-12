package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.BettingType;
import com.ubaid.app.model.schedule1_1.Outcome;

public class AHOUOutcomeBuilder extends AbstractBuilder
{

	public AHOUOutcomeBuilder()
	{
	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new Outcome.Builder()
					.bettingType(BettingType.getBettingType(resultSet.getLong(1)))
					.typeId(resultSet.getLong(2))
					.id(resultSet.getLong(3))
					.odds(resultSet.getFloat(4))
					.participant(resultSet.getString(5))
					.matchId(resultSet.getLong(6))
					.threshold(resultSet.getFloat(7))
					.sportName(SportType.getSport(resultSet.getLong(8)).toString())
					.providerId(resultSet.getLong(9))
					.build();
	}

}
