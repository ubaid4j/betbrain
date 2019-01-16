package com.ubaid.app.model.logic;

import java.util.LinkedList;

import javax.servlet.ServletException;

import com.ubaid.app.model.dao.RegisteredOutcomeDAO;
import com.ubaid.app.model.objects.Entity;

public class RegisteredOutcomeLogic extends AbstractLogic
{

	@Override
	public boolean delete(long id)
	{
		return dao.deleteById(id);
	}

	@Override
	public boolean add(Entity entity) throws ServletException
	{
		return dao.add(entity);
	}

	
	
	@Override
	public LinkedList<Entity> getAll() {
		return super.getAll();
	}

	public RegisteredOutcomeLogic()
	{
		dao = new RegisteredOutcomeDAO();
	}

}
