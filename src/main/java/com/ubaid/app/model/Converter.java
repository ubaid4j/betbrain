package com.ubaid.app.model;

import java.util.LinkedList;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.objects.Match;

/**
 * this converter is only valid for one provider.
 * if there more than one provider, then it will not give correct result
 * we have to change this algo to fix it for multiple providers 07-Feb-2019 Thursday
 * @author UbaidurRehman
 *
 */
public interface Converter
{
	LinkedList<Match> convert(long eventId, String homeTeam, String awayTeam, LinkedList<Entity> assianHandicapRawData);
}
