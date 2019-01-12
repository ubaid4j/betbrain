package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.builder.resultSetBuilder.OutcomeBuilder;

public class OutcomeBuilderFactory extends AbstractFactory
{

	public OutcomeBuilderFactory()
	{
	}

	@Override
	public Builder getBuilder()
	{
		return new OutcomeBuilder();
	}

}
