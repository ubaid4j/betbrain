package com.ubaid.app.model.singleton;

import java.util.Vector;

import com.ubaid.app.model.objects.Entity;

public class WList
{

	private static Vector<Entity> wlist = createWList();
	
	private WList()
	{
	}

	private static Vector<Entity> createWList()
	{
		if(wlist == null)
		{
			wlist = new Vector<>();
		}
		
		return wlist;
	}
	
	public static Vector<Entity> getWList()
	{
		return wlist;
	}
	
}
