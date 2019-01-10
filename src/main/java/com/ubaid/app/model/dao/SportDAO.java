package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.SportFactory;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.singleton.DataSource;

public class SportDAO extends AbstractDAO
{

	private static final String query = "select id, name from Sport where id in (1, 7, 8, 20, 90);";

	@Override
	public LinkedList<Entity> getAll()
	{
		try
		{
			Connection connection = DataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			return builder.build(preparedStatement.executeQuery());
		}
		catch(SQLException exp)
		{
			throw new IllegalAccessError(exp.getMessage());
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new IllegalAccessError(exp.getMessage());
		}
	}

	@Override
	public LinkedList<Entity> getAll(SportType type) {
		return null;
	}

	@Override
	String getQuery(QT type)
	{
		return query;
	}

	@Override
	AbstractFactory getFactory()
	{
		return new SportFactory();
	}

}
