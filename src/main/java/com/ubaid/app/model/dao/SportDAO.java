package com.ubaid.app.model.dao;

import java.util.LinkedList;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.SportFactory;
import com.ubaid.app.model.objects.Entity;

public class SportDAO extends AbstractDAO
{

	@Override
	public LinkedList<Entity> getAll(SportType type) {
		return null;
	}

	@Override
	String getQuery(QT type)
	{
		return buildQuery();
	}

	@Override
	AbstractFactory getFactory()
	{
		return new SportFactory();
	}
	
	private String buildQuery()
	{
		
		String internalPart = "";
		for(SportType sportType : SportType.values())
		{
			internalPart += sportType.getValue() + ", ";
		}
		
		internalPart = internalPart.substring(0, internalPart.length() - 2);
		String query = String.format("select id, removeSpecialCharacter(name) from Sport where id in (%s);", internalPart);
		return query;
	}

}
