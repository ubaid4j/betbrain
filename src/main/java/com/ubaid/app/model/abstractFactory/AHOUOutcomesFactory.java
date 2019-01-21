package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.builder.resultSetBuilder.AHOUOutcomeBuilder;

public class AHOUOutcomesFactory extends AbstractFactory
{

	public AHOUOutcomesFactory()
	{

	}

	@Override
	public Builder getBuilder()
	{
		return new AHOUOutcomeBuilder();
	}

}
