package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.dao.EventsDAO;
import com.ubaid.app.model.objects.Entity;

public class EventsLogic extends AbstractLogic
{
	public EventsLogic()
	{
		dao = new EventsDAO();
	}
		
	@Override
	public LinkedList<Entity> getAll()
	{
		throw new IllegalAccessError("Method is not implemented for this");
	}

}
