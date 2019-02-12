package com.ubaid.app.model.dao;

import java.util.LinkedList;

import javax.servlet.ServletException;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.objects.Entity;



public interface DAO
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
	
	/**
	 * 
	 * @param id
	 * @param eventPartId
	 * @param providerId
	 * @return all over/under odds from the given provider id
	 */
	LinkedList<Entity> getAll(long id, int eventPartId, long providerId);

	boolean deleteById(long id);
	boolean deleteById(long id, long providerId);
	boolean deleteAll();
	boolean add(Entity entity) throws ServletException;
	Entity get(Entity entity);
}
