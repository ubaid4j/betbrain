package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.dao.OverUnderDAO;
import com.ubaid.app.model.objects.Entity;

public class OverUnderLogic extends AbstractLogic
{

	public OverUnderLogic()
	{
		dao = new OverUnderDAO();
	}

	@Override
	public LinkedList<Entity> getAll(long id, int eventPartId)
	{
		return super.getAll(id, eventPartId);
	}

	
}
