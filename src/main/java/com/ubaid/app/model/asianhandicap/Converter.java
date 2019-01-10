package com.ubaid.app.model.asianhandicap;

import java.util.List;

import com.ubaid.app.model.objects.Match;

public interface Converter
{
	List<Match> convert(long eventId, String homeTeam, String awayTeam, AssianHandicapRawData data);
}
