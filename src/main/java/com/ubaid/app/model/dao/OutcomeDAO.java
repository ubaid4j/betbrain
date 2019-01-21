package com.ubaid.app.model.dao;

import java.util.LinkedList;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.builder.Builder;

public interface OutcomeDAO
{
	AbstractFactory getFactory();
	Builder getBuilder();
	LinkedList<Entity> getAll(long[] ids);
	
	/**
	 * 
	 * @param ids
	 * @param eventPartId
	 * @return
	 */
	LinkedList<Entity> getAll(long[] ids, int eventPartId);
}

