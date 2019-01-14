package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.singleton.DataSource;

public class DAOOutcome implements OutcomeDAO
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
					"paramFloat1 `threshold`, odds, o.id " +
					"from " +
					"Outcome o left join BettingOffer bo on o.id = bo.outcomeId " +
					"where " +
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
			return builder.build(st.executeQuery());
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		return null;
	}
	
	private String queryBuilder(String query, int size)
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
