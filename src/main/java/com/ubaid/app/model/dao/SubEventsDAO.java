package com.ubaid.app.model.dao;

import java.util.LinkedList;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.SubEventsFactory;
import com.ubaid.app.model.objects.Entity;

public class SubEventsDAO extends AbstractDAO
{

	private static final String query = 
					"select " +
					"epr.eventId, " +
					"e.startTime as `Start Time`, " +
					"p.name as participantName, " +
					"pr.id as roleId, " +
					"pr.name as roleName " +
					"from " +
					"EventParticipantRelation epr  inner join " +
					"Participant p  on epr.participantId = p.id inner join " +
					"ParticipantRole pr on pr.id = epr.participantRoleId inner join " +
					"Event e on e.id = epr.eventId inner join " +
					"Event e2 on e.parentId = e2.id inner join " +
					"Sport s on e.sportId = s.id " +
					"where " +
					"date(e.startTime) between date(curdate()) and date(date_add(date(curdate()), interval 3 day)) " +
					"and " +
					"e.name is null " +
					"and " +
					"pr.id in (1, 2) " +
					"and " +
					"e2.id = ?; ";

	

	@Override
	String getQuery(QT type)
	{
		return query;
	}

	@Override
	public LinkedList<Entity> getAll()
	{
		throw new IllegalAccessError("Mehtod is not Applicable");
	}

	@Override
	public LinkedList<Entity> getAll(SportType type)
	{
		throw new IllegalAccessError("Mehtod is not Applicable");
	}

	@Override
	AbstractFactory getFactory()
	{
		return new SubEventsFactory();
	}


	
}
