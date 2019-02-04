package com.ubaid.app.model.logic;

import java.util.LinkedList;
import java.util.List;

import com.ubaid.app.model.dao.DAOOutcome;
import com.ubaid.app.model.dao.OutcomeDAO;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Key;
import com.ubaid.app.model.schedule1_1.Outcome;

public interface OutcomeLogic
{
	OutcomeDAO dao = new DAOOutcome();
	
	/**
	 * 
	 * @param ids
	 * @return get all outcomes on ids [assuming that there is one provider]
	 */
	LinkedList<Entity> getAll(long[] ids);
	
	/**
	 * 
	 * @param ids (keys)
	 * @return get all outcomes on outcomeId and providerId
	 */
	LinkedList<Entity> getAll(Key[] ids);
	
	/**
	 * 
	 * @param ids
	 * @param eventPartId
	 * @return list of outcomes
	 */
	LinkedList<Entity> getAll(long[] ids, int eventPartId);
	
	/**
	 * 
	 * @return all the list of outcomes
	 */
	LinkedList<Entity> getAll();
	
	/**
	 * 
	 * @param outcomes
	 * @return true on adding all the outcomes
	 */
	boolean addAll(List<Outcome> outcomes);
}
