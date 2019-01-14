package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Outcome;

public class RegisteredOutcomeBuilder extends AbstractBuilder {

	public RegisteredOutcomeBuilder() {
	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new Outcome.Builder()
					.id(resultSet.getLong(1))
					.odds(resultSet.getFloat(3))
					.threshold(resultSet.getFloat(4))
					.leagueName(resultSet.getString(5))
					.matchName(resultSet.getString(6))
					.participant(resultSet.getString(7))
					.homeTeam(resultSet.getString(8))
					.awayTeam(resultSet.getString(9))
					.registerTime(resultSet.getTimestamp(10))
					.changedTime(resultSet.getTimestamp(11))
					.build();
	}

}
