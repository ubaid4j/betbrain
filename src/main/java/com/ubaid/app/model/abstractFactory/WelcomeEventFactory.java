package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.builder.resultSetBuilder.WelcomeEventBuilder;

public class WelcomeEventFactory extends AbstractFactory
{

	public WelcomeEventFactory()
	{

	}

	@Override
	public ResultSetBuilder getBuilder()
	{
		return new WelcomeEventBuilder();
	}

}
