package com.ubaid.app.model.abstractFactory;

import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.builder.resultSetBuilder.TrackBuilder;

public class TrackFactory extends AbstractFactory
{

	public TrackFactory()
	{

	}

	@Override
	public ResultSetBuilder getBuilder()
	{
		return new TrackBuilder();
	}

}
