package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.builder.match.MatchBuilder;

public class MatchFactory extends AbstractFactory
{

	public MatchFactory()
	{
	}

	@Override
	public Builder getBuilder()
	{
		return new MatchBuilder();
	}

}
