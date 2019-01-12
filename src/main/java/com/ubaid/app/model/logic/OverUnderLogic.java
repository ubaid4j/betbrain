package com.ubaid.app.model.logic;

import com.ubaid.app.model.dao.OverUnderDAO;

public class OverUnderLogic extends AbstractLogic
{

	public OverUnderLogic()
	{
		dao = new OverUnderDAO();
	}

}
