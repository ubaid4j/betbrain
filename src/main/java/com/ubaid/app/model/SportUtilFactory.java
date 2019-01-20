package com.ubaid.app.model;


public class SportUtilFactory
{
	static SportUtil sportUtil = null;
	
	private SportUtilFactory()
	{
	}
	
	public static SportUtil getSportUtil()
	{
		if(sportUtil == null)
			sportUtil = new SportUtil();
		
		return sportUtil;
	}

}
