package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ubaid.app.model.objects.Entity;

public abstract class AbstractBuilder implements ResultSetBuilder
{

	public AbstractBuilder()
	{
	}

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
					entities.add(_buildEntity(resultSet));
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
	
	abstract Entity _buildEntity(ResultSet resultSet) throws SQLException;

}
