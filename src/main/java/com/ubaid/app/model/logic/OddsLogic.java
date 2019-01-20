package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.dao.OddsDAO;
import com.ubaid.app.model.objects.Entity;

public class OddsLogic extends AbstractLogic
{

	public OddsLogic()
	{
		dao = new OddsDAO();
	}
	
	@Override
	public LinkedList<Entity> getAll(long id, int bettingType, int eventPartId)
	{
		return dao.getAll(id, bettingType, eventPartId);
	}



}
