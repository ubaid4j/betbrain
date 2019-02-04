package com.ubaid.app.model.logic;

import java.util.LinkedList;
import java.util.List;

import com.ubaid.app.model.dao.OutcomeDAO;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Key;
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
		throw new UnsupportedOperationException("getAll() is not implemented by " + this.getClass().getName());
	}

	@Override
	public boolean addAll(List<Outcome> outcomes) {
		return false;
	}

	@Override
	public LinkedList<Entity> getAll(Key[] ids)
	{
		throw new UnsupportedOperationException("Method is not implemented for class " + this.getClass().getName());
	}

	abstract OutcomeDAO getOutcomeDAO();
}
