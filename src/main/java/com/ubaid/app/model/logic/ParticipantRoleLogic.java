package com.ubaid.app.model.logic;

import java.util.LinkedList;

import com.ubaid.app.model.SportType;
import com.ubaid.app.model.dao.ParticipentRoleDAO;
import com.ubaid.app.model.objects.Entity;

public class ParticipantRoleLogic extends AbstractLogic
{
	public ParticipantRoleLogic()
	{
		dao = new ParticipentRoleDAO();
	}

	@Override
	public LinkedList<Entity> getAll(SportType type)
	{
		throw new IllegalAccessError("Method not supported");
	}

	@Override
	public LinkedList<Entity> getAll()
	{
		throw new IllegalAccessError("Method not supported");
	}

	@Override
	public LinkedList<Entity> getAll(long id)
	{
		throw new IllegalAccessError("Method not supported");
	}

	/**
	 * it take the ParticipantEvent type Entity and return ParticipantRole type Entity
	 */
	@Override
	public Entity get(Entity entity)
	{
		return dao.get(entity);
	}

}
