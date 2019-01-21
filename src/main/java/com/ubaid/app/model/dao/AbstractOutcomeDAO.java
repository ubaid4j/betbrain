package com.ubaid.app.model.dao;

import java.util.LinkedList;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.objects.Entity;

public abstract class AbstractOutcomeDAO implements OutcomeDAO {

	public AbstractOutcomeDAO()
	{

	}

	@Override
	public LinkedList<Entity> getAll(long[] ids)
	{
		return null;
	}

	@Override
	public abstract AbstractFactory getFactory();

	@Override
	public Builder getBuilder()
	{
		return getFactory().getBuilder();
	}

	
	
	@Override
	public LinkedList<Entity> getAll(long[] ids, int eventPartId)
	{
		return null;
	}

	String queryBuilder(String query, int size)
	{
		String tempStr = "(";
		for(int i = 0; i < size - 1; i++)
		{
			tempStr += "?, ";
		}
		
		tempStr += "?);";
		query += tempStr;
		
		return query;
	}
}
