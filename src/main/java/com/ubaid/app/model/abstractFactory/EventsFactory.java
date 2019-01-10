package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.builder.resultSetBuilder.EventsBuilder;

public class EventsFactory extends AbstractFactory
{

	public EventsFactory()
	{

	}

	@Override
	public ResultSetBuilder getBuilder()
	{
		return new EventsBuilder();
	}

}
