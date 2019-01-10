package com.ubaid.app.model.notification;

import org.json.JSONObject;

/**
 * 
 * @author ubaid
 * this interface simply return notification [a whole match] when odds changed.
 */
public interface INotification
{
	public JSONObject getNotification();
}
