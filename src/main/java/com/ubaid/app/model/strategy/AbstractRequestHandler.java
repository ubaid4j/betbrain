package com.ubaid.app.model.strategy;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * this class implement the RequestHandler 
 * and its methods simply return the null
 * @author ubaid
 *
 */
public abstract class AbstractRequestHandler implements RequestHandler
{

	public AbstractRequestHandler()
	{
		
	}

	@Override
	public JSONArray get(Map<String, String[]> map)
	{
		return null;
	}

	@Override
	public JSONArray get(HttpServletResponse response)
	{
		return null;
	}

}
