package com.ubaid.app.model.logic.matchLogic;

import java.util.LinkedList;

import com.ubaid.app.model.objects.Match;

public interface Logic
{
	Match getMatch(long id);
	boolean addChangedOddsMatch(Match match);
	LinkedList<Match> getAllMatches();
}
