package com.ubaid.app.model.strategy;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

public interface RequestHandler
{
	JSONArray get(Map<String, String[]> map);
	JSONArray get(HttpServletResponse response);
}
