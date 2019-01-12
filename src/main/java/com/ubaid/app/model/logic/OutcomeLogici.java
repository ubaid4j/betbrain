package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.objects.Entity;

public class OutcomeLogici implements OutcomeLogic
{

	public OutcomeLogici()
	{
		
	}

	@Override
	public LinkedList<Entity> getAll(Object[] ids)
	{		
		return dao.getAll(ids);
	}

}
