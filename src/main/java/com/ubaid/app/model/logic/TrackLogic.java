package com.ubaid.app.model.logic;

import java.util.LinkedList;

import javax.servlet.ServletException;

import com.ubaid.app.model.dao.TrackDAO;
import com.ubaid.app.model.objects.Entity;


public class TrackLogic extends AbstractLogic
{

	@Override
	public LinkedList<Entity> getAll()
	{
		return super.getAll();
	}

	public TrackLogic()
	{
		dao = new TrackDAO();
	}

	@Override
	public boolean add(Entity entity) throws ServletException
	{
		return dao.add(entity);
	}

	@Override
	public boolean delete(long id)
	{
		try
		{
			return dao.deleteById(id);			
		}
		catch(Exception exp)
		{
			throw new IllegalAccessError(exp.getMessage());
		}
	}
	
	

}
