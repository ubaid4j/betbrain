package com.ubaid.app.model.logic;


import com.ubaid.app.model.dao.DAOOutcome;
import com.ubaid.app.model.dao.OutcomeDAO;

public class OutcomeLogici extends AbstractOutcomeLogic
{

	public OutcomeLogici()
	{
		
	}

	@Override
	OutcomeDAO getOutcomeDAO()
	{
		return new DAOOutcome();
	}

}
