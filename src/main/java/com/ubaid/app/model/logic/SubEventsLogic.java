package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.dao.SubEventsDAO;
import com.ubaid.app.model.objects.Entity;

public class SubEventsLogic extends AbstractLogic
{

	public SubEventsLogic()
	{
		dao = new SubEventsDAO();
	}

	@Override
	public LinkedList<Entity> getAll(SportType type)
	{
		throw new IllegalAccessError("Mehtod is not Applicable");
	}

	@Override
	public LinkedList<Entity> getAll()
	{
		throw new IllegalAccessError("Mehtod is not Applicable");
	}

	@Override
	public LinkedList<Entity> getAll(long id)
	{
		return super.getAll(id);
	}

}
