package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.builder.resultSetBuilder.ParticipantRoleBuilder;

public class ParticipantRoleFactory extends AbstractFactory
{

	public ParticipantRoleFactory()
	{
	}

	@Override
	public ResultSetBuilder getBuilder()
	{
		return new ParticipantRoleBuilder();
	}

}
