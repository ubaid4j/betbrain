package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.builder.resultSetBuilder.OverUnderBuilder;

public class OverUnderFactory extends AbstractFactory
{

	public OverUnderFactory()
	{
		
	}

	@Override
	public Builder getBuilder()
	{
		return new OverUnderBuilder();
	}

}
