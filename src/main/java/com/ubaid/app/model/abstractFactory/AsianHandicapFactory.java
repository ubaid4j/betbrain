package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.builder.resultSetBuilder.AssianHandicapBuilder;

public class AsianHandicapFactory extends AbstractFactory
{

	public AsianHandicapFactory()
	{
	}

	@Override
	public Builder getBuilder()
	{
		return new AssianHandicapBuilder();
	}

}
