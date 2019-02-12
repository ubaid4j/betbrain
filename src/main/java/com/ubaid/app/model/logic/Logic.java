package com.ubaid.app.model.logic;

import java.util.LinkedList;

import javax.servlet.ServletException;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.objects.Entity;

public interface Logic
{
	LinkedList<Entity> getAll();
	boolean deleteAll();
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
	
	/**
	 * 
	 * @param id
	 * @param eventPartId
	 * @param providerId
	 * @return
	 */
	LinkedList<Entity> getAll(long id, int eventPartId, long providerId);
	boolean add(Entity entity) throws ServletException;
	boolean delete(long id);
	/**
	 * 
	 * @param id
	 * @param providerId
	 * @return if entity in the database deleted or not
	 */
	boolean delete(long id, long providerId);
	Entity get(Entity entity);
}
