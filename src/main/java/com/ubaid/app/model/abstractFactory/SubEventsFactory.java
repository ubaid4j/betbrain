package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.builder.resultSetBuilder.SubEventsBuilder;

public class SubEventsFactory extends AbstractFactory
{

	public SubEventsFactory()
	{
	
	}

	@Override
	public ResultSetBuilder getBuilder()
	{
		return new SubEventsBuilder();
	}

}
