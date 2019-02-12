package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.AsianHandicapFactory;
import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.singleton.DataSource;

public class AssianHandicapForThresholdDetection extends AbstractDAO
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
											"s.providerId in (?) " + 
											"and " +
											"o.eventPartId = ? " +
											"and " +
											"date(e.startTime) between date(curdate()) and date(date_add(date(curdate()), interval 3 day)) " +
											"and " +
											"e.id = ? " +
											"order by s.lastCollectedTime desc; ";
	
	
	
	public AssianHandicapForThresholdDetection()
	{

	}

	
	

	@Override
	public LinkedList<Entity> getAll(long id, int eventPartId, long providerId)
	{
		try
		{
			Connection con = DataSource.getConnection();
			PreparedStatement st = con.prepareStatement(query);
			st.setLong(1, providerId);
			st.setInt(2, eventPartId);
			st.setLong(3, id);
			
			ResultSet rs = st.executeQuery();
			ResultSetBuilder builder = (ResultSetBuilder) getFactory().getBuilder();
			return builder.build(rs);
		}
		catch(SQLException exp)
		{
			throw new RuntimeException("AssianHandicapForThreshold SQL Problem");
		}

	}




	@Override
	String getQuery(QT type)
	{
		return null;
	}

	@Override
	AbstractFactory getFactory()
	{
		return new AsianHandicapFactory();
	}

}
