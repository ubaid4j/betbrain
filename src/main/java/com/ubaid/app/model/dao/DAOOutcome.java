package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.OutcomeBuilderFactory;
import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.singleton.DataSource;

public class DAOOutcome extends AbstractOutcomeDAO
{
	
	public DAOOutcome()
	{
		
	}

	@Override
	public LinkedList<Entity> getAll(long[] ids)
	{
		try
		{
			
			String query = 	"select " +
					"paramFloat1 `threshold`, odds, o.id, bo.providerId " +
					"from " +
					"Outcome o left join BettingOffer bo on o.id = bo.outcomeId " +
					"where " +
					"bo.statusId = 1 " +
					"and " +
					"o.id " +
					"in ";
			
			Connection con = DataSource.getConnection();
			//--------------------------logic------------------------
			int size = ids.length;
			query = queryBuilder(query, size);
			PreparedStatement st = con.prepareStatement(query);
			int index = 1;
			for(long id : ids)
			{
				st.setLong(index++, id);
			}

			//--------------------------logic------------------------
			ResultSetBuilder builder = (ResultSetBuilder) getBuilder();
			return builder.build(st.executeQuery());
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		return null;
	}

	@Override
	public AbstractFactory getFactory()
	{
		return new OutcomeBuilderFactory();
	}

}
