package com.ubaid.app.model.objects;

import java.util.List;

public interface Converter
{
	/**
	 * 
	 * @param events
	 * @param eventPartId
	 * @param bettingTypeid
	 * @return list of matches [having home away and draw odds]
	 */
	List<Match> convert(List<SubEvents> events, int eventPartId,  int bettingTypeid);	
}


