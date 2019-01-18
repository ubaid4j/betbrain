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
	boolean deleteById(long id);
	boolean add(Entity entity) throws ServletException;
	Entity get(Entity entity);
}
