package com.ubaid.app.model.dao;

import java.util.LinkedList;
import java.util.List;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Key;
import com.ubaid.app.model.schedule1_1.Outcome;

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
	public LinkedList<Entity> getAll(Key[] ids)
	{
		throw new UnsupportedOperationException("Method is not implemented for this class " + this.getClass().getName());
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

	
	
	@Override
	public boolean addAll(List<Outcome> outcomes)
	{
		return false;
	}
	
	

	@Override
	public LinkedList<Entity> getAll()
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
