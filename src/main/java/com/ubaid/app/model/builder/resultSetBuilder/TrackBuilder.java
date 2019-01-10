package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.eventParticipant.Track;

public class TrackBuilder extends AbstractBuilder
{

	public TrackBuilder()
	{
	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new Track.Builder()
					.matchId(resultSet.getLong(1))
					.parentMatchId(resultSet.getLong(2))
					.bettingType(resultSet.getInt(3))
					.build();
	}

}
