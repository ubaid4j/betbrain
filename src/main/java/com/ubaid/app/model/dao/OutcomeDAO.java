package com.ubaid.app.model.dao;

import java.util.LinkedList;
import java.util.List;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.schedule1_1.Key;
import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.builder.Builder;

public interface OutcomeDAO
{
	AbstractFactory getFactory();
	Builder getBuilder();
	
	/**
	 * 
	 * @param ids
	 * @return getAll outcomes on outcome ids
	 */
	LinkedList<Entity> getAll(long[] ids);
	
	/**
	 * 
	 * @param ids
	 * @return getAll outcomes on outcome ids and provider id
	 */
	LinkedList<Entity> getAll(Key[] ids);
	
	/**
	 * 
	 * @param ids
	 * @param eventPartId
	 * @return
	 */
	LinkedList<Entity> getAll(long[] ids, int eventPartId);
	
	/**
	 * 
	 * @param outcomes
	 * @return
	 */
	boolean addAll(List<Outcome> outcomes);
	
	/**
	 * 
	 * @return all outcomes
	 */
	LinkedList<Entity> getAll();
}

