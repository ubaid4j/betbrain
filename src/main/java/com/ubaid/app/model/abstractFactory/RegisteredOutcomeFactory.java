package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.builder.resultSetBuilder.RegisteredOutcomeBuilder;

public class RegisteredOutcomeFactory extends AbstractFactory
{

	public RegisteredOutcomeFactory()
	{
	}

	@Override
	public Builder getBuilder()
	{
		return new RegisteredOutcomeBuilder();
	}

}
