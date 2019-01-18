package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.servlet.ServletException;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.singleton.DataSource;

public abstract class AbstractDAO implements DAO
{

	AbstractFactory factory;
	ResultSetBuilder builder;
	public AbstractDAO()
	{
		builder = (ResultSetBuilder) getFactory().getBuilder();
	}

	@Override
	public LinkedList<Entity> getAll()
	{
		LinkedList<Entity> entities;
		try
		{
			Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(getQuery(QT.GETALL));
			ResultSet resultSet = statement.executeQuery();
			entities = builder.build(resultSet);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new IllegalArgumentException();
		}
		
		return entities;
	}
	
	@Override
	public LinkedList<Entity> getAll(SportType type)
	{
		LinkedList<Entity> entities;
		try
		{
			Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(getQuery(QT.GETALLBYSPORT));
			statement.setInt(1, type.getValue());
			ResultSet resultSet = statement.executeQuery();
			entities = builder.build(resultSet);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			throw new IllegalArgumentException();
		}
		
		return entities;
	}

	abstract String getQuery(QT type);

	@Override
	public LinkedList<Entity> getAll(long id)
	{
		LinkedList<Entity> entities;
		try
		{
			Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(getQuery(QT.GETALLBYID));
			statement.setLong(1, id);
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
	public boolean deleteById(long id)
	{
		throw new IllegalAccessError("Method not supported");
	}

	@Override
	public boolean add(Entity entity) throws ServletException
	{
		throw new IllegalAccessError("Method not supported");
	}

	@Override
	public Entity get(Entity entity)
	{
		throw new IllegalAccessError("Method not supported");
	}
	
	
	
	@Override
	public LinkedList<Entity> getAll(long id, int eventPartId) {
		// TODO Auto-generated method stub
		return null;
	}

	abstract AbstractFactory getFactory();
	
	

}
