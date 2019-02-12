package com.ubaid.app.model.logic;


import java.util.LinkedList;

import com.ubaid.app.model.dao.AHOUOutcomesDAO;
import com.ubaid.app.model.dao.OutcomeDAO;
import com.ubaid.app.model.objects.Entity;

public class AHOUOutcomeLogic extends AbstractOutcomeLogic
{
	@Override
	OutcomeDAO getOutcomeDAO()
	{
		return new AHOUOutcomesDAO();
	}

	@Override
	public LinkedList<Entity> getAll(long[] id, int eventPartId, long providerId)
	{
		return super.getAll(id, eventPartId, providerId);
	}
	
	

}
