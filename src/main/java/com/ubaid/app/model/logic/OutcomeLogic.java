package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.dao.DAOOutcome;
import com.ubaid.app.model.dao.OutcomeDAO;
import com.ubaid.app.model.objects.Entity;

public interface OutcomeLogic
{
	OutcomeDAO dao = new DAOOutcome();
	LinkedList<Entity> getAll(long[] ids);
	
}
