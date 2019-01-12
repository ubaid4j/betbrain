package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.dao.AsianHandicapDAO;
import com.ubaid.app.model.objects.Entity;

public class AssianHandicapLogic extends AbstractLogic {

	public AssianHandicapLogic()
	{
		dao = new AsianHandicapDAO();
	}

	@Override
	public LinkedList<Entity> getAll(long id)
	{
		return super.getAll(id);
	}

}
