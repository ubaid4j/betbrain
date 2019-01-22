package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.builder.resultSetBuilder.TrackedMatchBuilder;

public class TrackedMatchFactory extends AbstractFactory {

	public TrackedMatchFactory()
	{
	}

	@Override
	public Builder getBuilder()
	{
		return new TrackedMatchBuilder();
	}

}
