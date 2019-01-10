package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.builder.resultSetBuilder.SportBuilder;

public class SportFactory extends AbstractFactory
{

	public SportFactory()
	{
	}

	@Override
	public ResultSetBuilder getBuilder()
	{
		return new SportBuilder();
	}

}
