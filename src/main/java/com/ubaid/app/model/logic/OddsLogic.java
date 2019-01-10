package com.ubaid.app.model.logic;

import com.ubaid.app.model.dao.OddsDAO;

public class OddsLogic extends AbstractLogic
{

	public OddsLogic()
	{
		dao = new OddsDAO();
	}

}
