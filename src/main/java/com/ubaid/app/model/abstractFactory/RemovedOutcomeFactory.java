package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.builder.resultSetBuilder.RemovedOutcomeBuilder;

public class RemovedOutcomeFactory extends AbstractFactory
{

	public RemovedOutcomeFactory()
	{

	}

	@Override
	public Builder getBuilder()
	{
		return new RemovedOutcomeBuilder();
	}

}
