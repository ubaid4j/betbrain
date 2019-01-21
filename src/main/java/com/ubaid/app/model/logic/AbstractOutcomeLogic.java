package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.dao.OutcomeDAO;
import com.ubaid.app.model.objects.Entity;

public abstract class AbstractOutcomeLogic implements OutcomeLogic
{

	public AbstractOutcomeLogic()
	{

	}

	@Override
	public LinkedList<Entity> getAll(long[] ids)
	{
		return getOutcomeDAO().getAll(ids);
	}
	
	

	@Override
	public LinkedList<Entity> getAll(long[] ids, int eventPartId)
	{
		return getOutcomeDAO().getAll(ids, eventPartId);
	}

	abstract OutcomeDAO getOutcomeDAO();
}
