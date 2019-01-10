package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Odds;

public class OddsBuilder extends AbstractBuilder
{

	public OddsBuilder()
	{

	}
/*
	@Override
	public LinkedList<Entity> build(ResultSet resultSet)
	{
		LinkedList<Entity> entities = new LinkedList<Entity>();
		try
		{
	
			while(resultSet.next())
			{
				try
				{
					Entity entity = new Odds.Builder()
							.odds(resultSet.getFloat(1))
							.name(resultSet.getString(2))
							.threshold(resultSet.getFloat(3))
							.build();
					entities.add(entity);
				}
				catch(SQLException exp)
				{
					exp.printStackTrace();
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		
		return entities;
	}
*/
	
	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new Odds.Builder()
				.odds(resultSet.getFloat(1))
				.name(resultSet.getString(2))
				.threshold(resultSet.getFloat(3))
				.build();
	}

}
