package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ubaid.app.model.abstractFactory.AHOUOutcomesFactory;
import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.singleton.DataSource;

public class AHOUOutcomesDAO extends AbstractOutcomeDAO
{


	@Override
	public LinkedList<Entity> getAll(long[] ids, int eventPartId)
	{
		String query = "select " +
						"bettingTypeId as `Betting Type ID`, " +
						"o.typeId, " +
						"o.id as `outcome id`, " +
						"odds, " +
						"p.name as `Participant Name`, " +
						"e.id as `Match ID`, " +
						"o.paramFloat1 `Thres Hold`, " +
						"spt.id as `Sport id` " +
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
						"bo.bettingTypeId in (48, 47) " +
						"and " +
						"o.eventPartId = ? " +
						"and " +
						"date(e.startTime) between date(curdate()) and date(date_add(date(curdate()), interval 3 day)) " +
						"and " +
						"e.id in ";
		
		query = queryBuilder(query, ids.length);

		/*----------------------------------------Logic---------------------------------*/
		try
		{
			Connection con = DataSource.getConnection();
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, eventPartId);
			
			int index = 2;
			for(long id : ids)
			{
				st.setLong(index++, id);
			}
			
			ResultSetBuilder builder = (ResultSetBuilder) getBuilder();
			return builder.build(st.executeQuery());
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public AbstractFactory getFactory()
	{
		return new AHOUOutcomesFactory();
	}

}
