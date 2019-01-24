package com.ubaid.app.model.logic;

import java.util.LinkedList;
import java.util.List;

import com.ubaid.app.model.dao.OutcomeDAO;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Outcome;

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

	
	
	@Override
	public LinkedList<Entity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAll(List<Outcome> outcomes) {
		// TODO Auto-generated method stub
		return false;
	}

	abstract OutcomeDAO getOutcomeDAO();
}
