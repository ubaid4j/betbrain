package com.ubaid.app.model.logic.matchLogic;

import java.util.LinkedList;

import com.ubaid.app.model.dao.matchDao.DAO;
import com.ubaid.app.model.dao.matchDao.MatchDAO;
import com.ubaid.app.model.objects.Match;

public class MatchLogic implements Logic
{

	DAO dao = null;
	
	public MatchLogic()
	{
		dao = new MatchDAO();
	}

	@Override
	public Match getMatch(long id)
	{
		return dao.getMatch(id);
	}

	@Override
	public boolean addChangedOddsMatch(Match match)
	{
		return dao.addChangedOddsMatch(match);
	}

	@Override
	public LinkedList<Match> getAllMatches() {
		return dao.getAllMatches();
	}

}
