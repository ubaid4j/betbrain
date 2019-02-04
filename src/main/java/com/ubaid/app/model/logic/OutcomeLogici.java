package com.ubaid.app.model.logic;


import java.util.LinkedList;

import com.ubaid.app.model.dao.DAOOutcome;
import com.ubaid.app.model.dao.OutcomeDAO;
import com.ubaid.app.model.objects.Entity;

public class OutcomeLogici extends AbstractOutcomeLogic
{

	public OutcomeLogici()
	{
		
	}

	
	
	//TODO
	@Override
	public LinkedList<Entity> getAll(long[] ids)
	{
		return dao.getAll(ids);
	}




	@Override
	OutcomeDAO getOutcomeDAO()
	{
		return new DAOOutcome();
	}

}
