package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.OddsFactory;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.singleton.DataSource;

public class OddsDAO extends AbstractDAO
{


	private static final String  query = "select " +
											"odds, " +
											"p.name as `Participant Name`, " +
											"o.paramFloat1 `Thres Hold`, " +
											"o.id `outcome id`, " +
											"s.providerId `Provider Id` " + 
											"from " +
											"BettingOffer bo inner join " +
											"Source s on bo.sourceId = s.id inner join " +
											"Outcome o on bo.outcomeId = o.id inner join " +
											"Event e on o.eventId = e.id inner join " +
											"Sport spt on e.sportId = spt.id left outer join " +
											"Participant p on o.paramParticipant1 = p.id inner join " +
											"EventPart ep on o.eventPartId = ep.id left join " +
											"Betting"
											+ "Type bt on bo.bettingTypeId = bt.id " +
											"where " +
											"bo.bettingTypeId in (?) " +
											"and " +
											"o.eventPartId = ? " +
											"and " +
											"date(e.startTime) between date(curdate()) and date(date_add(date(curdate()), interval 3 day)) " +
											"and " +
											"e.id = ? " +
											"and " +
											"s.providerId = 3000107 " + 
											"order by s.lastCollectedTime desc " +
											"limit 3; ";
	
	@Override
	public LinkedList<Entity> getAll()
	{
		throw new IllegalAccessError("Method is not implemented");
	}

	@Override
	public LinkedList<Entity> getAll(SportType type)
	{
		throw new IllegalAccessError("Method is not implemented");
	}

	@Override
	public LinkedList<Entity> getAll(long id)
	{
		return super.getAll(id);
	}

	@Override
	public LinkedList<Entity> getAll(long id,int bettingType, int eventPartId)
	{
		LinkedList<Entity> entities;
		try
		{
			Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(getQuery(QT.GETALLBYID));
			statement.setInt(1, bettingType);
			statement.setInt(2, eventPartId);
			statement.setLong(3, id);
			ResultSet resultSet = statement.executeQuery();
			entities = builder.build(resultSet);
		}
		catch(Exception exp)
		{
			throw new IllegalArgumentException();
		}
		
		return entities;	

	}

	@Override
	String getQuery(QT type)
	{
		return query;
	}

	
	@Override
	AbstractFactory getFactory()
	{
		return new OddsFactory();
	}

}
