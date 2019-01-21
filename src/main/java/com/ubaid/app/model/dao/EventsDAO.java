package com.ubaid.app.model.dao;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.EventsFactory;

public class EventsDAO extends AbstractDAO
{

	private static final String query = "select " +
										"distinct " +
										"removeSpecialCharacter(e2.name) as `Tournament Name`, " +
										"e2.id as `Tournament ID`, " +
										"l.name as `Location Name` " +
										"from " +
										"Event e inner join " +
										"Event e2 on e.parentId = e2.id inner join " +
										"Sport s on e.sportId = s.id inner join " +
										"Location l on e2.venueId = l.id " +
										"where " +
										"date(e.startTime) between date(curdate()) and date(date_add(date(curdate()), interval 3 day)) " +
										"and " +
										"e.name is null " +
										"and " +
										"s.id in (?); ";
	
	@Override
	String getQuery(QT type)
	{
		return query;
	}

	@Override
	AbstractFactory getFactory()
	{
		return new EventsFactory();
	}
}
