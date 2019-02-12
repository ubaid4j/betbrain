package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.dao.AssianHandicapForThresholdDetection;
import com.ubaid.app.model.objects.Entity;

public class AssianHandicapForThresholdLogic extends AbstractLogic
{

	public AssianHandicapForThresholdLogic()
	{
		dao = new AssianHandicapForThresholdDetection();
	}

	@Override
	public LinkedList<Entity> getAll(long id, int eventPartId, long providerId)
	{
		return dao.getAll(id, eventPartId, providerId);
	}

	
}
