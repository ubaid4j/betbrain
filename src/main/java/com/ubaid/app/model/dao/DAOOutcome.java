package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.singleton.DataSource;

public class DAOOutcome implements OutcomeDAO
{

	private static final String query = "select " +
										"paramFloat1 `threshold`, odds, o.id " +
										"from " +
										"Outcome o left join BettingOffer bo on o.id = bo.outcomeId " +
										"where " +
										"o.id " +
										"in " +
										"(?); ";


	
	public DAOOutcome()
	{
		
	}

	@Override
	public LinkedList<Entity> getAll(Object[] ids)
	{
		try
		{
			Connection con = DataSource.getConnection();
			PreparedStatement st = con.prepareStatement(query);
			st.setArray(1, con.createArrayOf("bigint", ids)); 
			return builder.build(st.executeQuery());
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		return null;
	}

}
