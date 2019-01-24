package com.ubaid.app.model.logic;

import java.util.LinkedList;
import java.util.List;

import com.ubaid.app.model.dao.OutcomeDAO;
import com.ubaid.app.model.dao.RemovedOutcomeDAO;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Outcome;

public class RemovedOutComeLogic extends AbstractOutcomeLogic
{

	public RemovedOutComeLogic()
	{
	}

	@Override
	OutcomeDAO getOutcomeDAO()
	{
		return new RemovedOutcomeDAO();
	}

	@Override
	public LinkedList<Entity> getAll()
	{
		return getOutcomeDAO().getAll();
	}

	@Override
	public boolean addAll(List<Outcome> outcomes)
	{
		return getOutcomeDAO().addAll(outcomes);
	}
	
	

}
