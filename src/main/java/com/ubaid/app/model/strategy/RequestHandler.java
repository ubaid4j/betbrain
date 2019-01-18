package com.ubaid.app.model.strategy;

import java.util.Map;

import org.json.JSONArray;

public interface RequestHandler
{
	JSONArray get(Map<String, String[]> map);
}
