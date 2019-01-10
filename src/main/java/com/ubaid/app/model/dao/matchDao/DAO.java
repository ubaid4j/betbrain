package com.ubaid.app.model.dao.matchDao;

import java.util.LinkedList;

import com.ubaid.app.model.objects.Match;

public interface DAO
{
	Match getMatch(long id);
	boolean addChangedOddsMatch(Match match);
	LinkedList<Match> getAllMatches();
}
