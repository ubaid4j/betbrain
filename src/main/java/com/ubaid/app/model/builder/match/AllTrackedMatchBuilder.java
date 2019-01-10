package com.ubaid.app.model.builder.match;

import java.sql.ResultSet;
import java.util.LinkedList;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.objects.Match;

public interface AllTrackedMatchBuilder extends Builder
{
	public LinkedList<Match> build(ResultSet resultSet);
}
