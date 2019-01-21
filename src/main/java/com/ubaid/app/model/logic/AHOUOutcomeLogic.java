package com.ubaid.app.model.logic;


import com.ubaid.app.model.dao.AHOUOutcomesDAO;
import com.ubaid.app.model.dao.OutcomeDAO;

public class AHOUOutcomeLogic extends AbstractOutcomeLogic
{
	@Override
	OutcomeDAO getOutcomeDAO()
	{
		return new AHOUOutcomesDAO();
	}

}
