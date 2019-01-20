package com.ubaid.app.model.logic;

import java.util.LinkedList;

import javax.servlet.ServletException;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.objects.Entity;

public interface Logic
{
	LinkedList<Entity> getAll();
	LinkedList<Entity> getAll(SportType type);
	LinkedList<Entity> getAll(long id);
	LinkedList<Entity> getAll(long id, int eventPartId);
	/**
	 * 
	 * @param id
	 * @param bettingType
	 * @param eventPartId
	 * @return
	 */
	LinkedList<Entity> getAll(long id, int bettingType, int eventPartId);
	boolean add(Entity entity) throws ServletException;
	boolean delete(long id);
	Entity get(Entity entity);
}
