package com.ubaid.app.model;

import java.util.LinkedList;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;

public interface Converter
{
	LinkedList<Match> convert(long eventId, String homeTeam, String awayTeam, LinkedList<Entity> assianHandicapRawData);
}
