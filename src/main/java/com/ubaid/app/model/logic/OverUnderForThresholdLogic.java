package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.dao.OverUnderForThresholdDetection;
import com.ubaid.app.model.objects.Entity;

public class OverUnderForThresholdLogic extends AbstractLogic
{

	public OverUnderForThresholdLogic()
	{
		dao = new OverUnderForThresholdDetection();
	}

	@Override
	public LinkedList<Entity> getAll(long id, int eventPartId, long providerId)
	{
		return dao.getAll(id, eventPartId, providerId);
	}
	
	

}
