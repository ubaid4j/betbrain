package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.builder.resultSetBuilder.OddsBuilder;

public class OddsFactory extends AbstractFactory
{

	public OddsFactory()
	{

	}

	@Override
	public ResultSetBuilder getBuilder()
	{
		return new OddsBuilder();
	}

}
