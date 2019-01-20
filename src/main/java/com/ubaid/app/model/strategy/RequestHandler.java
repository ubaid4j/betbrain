package com.ubaid.app.model.strategy;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * this inerface handling the http requests
 * it has two methods, get which accept map, 
 * and second get accept a response (this is implemented by only one class)
 * @author ubaid
 *
 */
public interface RequestHandler
{
	JSONArray get(Map<String, String[]> map);
	JSONArray get(HttpServletResponse response);
}
