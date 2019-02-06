package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.BettingType;
import com.ubaid.app.model.schedule1_1.Outcome;

public class RegisteredOutcomeBuilder extends AbstractBuilder {

	public RegisteredOutcomeBuilder() {
	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new Outcome.Builder()
					.id(resultSet.getLong(1))
					.providerId(resultSet.getLong(2))
					.odds(resultSet.getFloat(4))
					.threshold(resultSet.getFloat(5))
					.leagueName(resultSet.getString(6))
					.matchName(resultSet.getString(7))
					.participant(resultSet.getString(8))
					.homeTeam(resultSet.getString(9))
					.awayTeam(resultSet.getString(10))
					.registerTime(resultSet.getTimestamp(11))
					.changedTime(resultSet.getTimestamp(12))
					.bettingType(BettingType.valueOf(resultSet.getString(13)))
					.build();
	}

}
