package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.ParticipantRoleFactory;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.eventParticipant.EventParticipent;
import com.ubaid.app.model.singleton.DataSource;

public class ParticipentRoleDAO extends AbstractDAO
{

	private final static String query = "select " +
										"epr.participantRoleId " +
										"from " +
										"EventParticipantRelation epr  left join " +
										"Participant p  on epr.participantId = p.id " +
										"where " +
										"epr.eventId = ? " +
										"and " +
										"p.name = ?; ";

	@Override
	String getQuery(QT type)
	{
		return query;
	}

	@Override
	public Entity get(Entity entity)
	{
		EventParticipent eventParticipent = (EventParticipent) entity;
		
		try
		{
			long id = eventParticipent.getEventId();
			String name = eventParticipent.getParticipantName();
			
			Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(getQuery(QT.GETBYENTITY));
			statement.setLong(1, id);
			statement.setString(2, name);
			ResultSet resultSet = statement.executeQuery();
			LinkedList<Entity> entities = builder.build(resultSet);
			return entities.get(0);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new IllegalArgumentException();
		}
		
	}

	@Override
	AbstractFactory getFactory()
	{
		return new ParticipantRoleFactory();
	}

}
