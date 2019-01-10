package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.builder.match.AllTrackedMatchsBuilder;

public class AllMatchFactory extends AbstractFactory
{

	public AllMatchFactory()
	{
		
	}

	@Override
	public Builder getBuilder()
	{
		return new AllTrackedMatchsBuilder();
	}

}
