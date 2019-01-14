package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.objects.Entity;

public class OutcomeLogici implements OutcomeLogic
{

	public OutcomeLogici()
	{
		
	}

	@Override
	public LinkedList<Entity> getAll(long[] ids)
	{		
		return dao.getAll(ids);
	}

}
