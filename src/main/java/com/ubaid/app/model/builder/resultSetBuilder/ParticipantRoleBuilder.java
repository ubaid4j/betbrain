package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.eventParticipant.ParticipantRole;

public class ParticipantRoleBuilder extends AbstractBuilder
{

	public ParticipantRoleBuilder()
	{
	}
	
	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new ParticipantRole.Builder()
				.role(resultSet.getInt(1))
				.build();
	}

}
