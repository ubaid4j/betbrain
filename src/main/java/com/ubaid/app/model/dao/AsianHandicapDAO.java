package com.ubaid.app.model.dao;

import java.util.LinkedList;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.AsianHandicapFactory;
import com.ubaid.app.model.objects.Entity;

public class AsianHandicapDAO extends AbstractDAO
{

	private static final String query = "select " +
										"odds, " +
										"p.name as `Participant Name`, " +
										"o.paramFloat1 `Thres Hold`, " +
										"o.id `outcomeId`, " + 
										"bo.providerId `providerId` " + 
										"from " +
										"BettingOffer bo left join " +
										"Source s on bo.sourceId = s.id left join " +
										"Outcome o on bo.outcomeId = o.id left join " +
										"Event e on o.eventId = e.id left join " +
										"Sport spt on e.sportId = spt.id left outer join " +
										"Participant p on o.paramParticipant1 = p.id left join " +
										"OutcomeType ot on o.typeId= ot.id left join " +
										"EventPart ep on o.eventPartId = ep.id left join " +
										"BettingType bt on bo.bettingTypeId = bt.id " +
										"where " +
										"bo.statusId = 1 and " + 
										"bo.bettingTypeId in (48) and " +
										"s.providerId in (3000368, 3000107) " + 
										"and " +
										"o.eventPartId = ? " +
										"and " +
										"date(e.startTime) between date(curdate()) and date(date_add(date(curdate()), interval 3 day)) " +
										"and " +
										"e.id = ? " +
										"order by s.lastCollectedTime desc; ";
	
	public AsianHandicapDAO()
	{

	}

	@Override
	String getQuery(QT type)
	{
		return query;
	}

	@Override
	AbstractFactory getFactory()
	{
		return new AsianHandicapFactory();
	}

	@Override
	public LinkedList<Entity> getAll(long id)
	{
		return super.getAll(id);
	}

}
