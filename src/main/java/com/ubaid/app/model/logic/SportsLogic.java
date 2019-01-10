package com.ubaid.app.model.logic;

import com.ubaid.app.model.dao.SportDAO;

public class SportsLogic extends AbstractLogic
{
	public SportsLogic()
	{
		dao = new SportDAO();
	}
}
