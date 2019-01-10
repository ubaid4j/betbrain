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
	boolean add(Entity entity) throws ServletException;
	boolean delete(long id);
	Entity get(Entity entity);
}
