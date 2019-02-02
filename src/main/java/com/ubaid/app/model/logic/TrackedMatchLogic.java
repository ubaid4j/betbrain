package com.ubaid.app.model.logic;

import javax.servlet.ServletException;

import com.ubaid.app.model.dao.TrackedMatchDAO;
import com.ubaid.app.model.objects.Entity;

public class TrackedMatchLogic extends AbstractLogic
{

	public TrackedMatchLogic()
	{
		dao = new TrackedMatchDAO();
	}

	@Override
	public boolean add(Entity entity) throws ServletException
	{
		return dao.add(entity);
	}

	@Override
	public boolean delete(long id)
	{
		return dao.deleteById(id);
	}

	@Override
	public boolean deleteAll()
	{
		return dao.deleteAll();
	}
	
	
	
	
	

}
