package com.ubaid.app.model.overunder;

public enum OVERUNDER
{
    OVER(13), UNDER(14);

	private final long typeId;
	
	private OVERUNDER(long typeId)
	{
		this.typeId = typeId;
	}
	
    public long getValue() { return typeId; }

}
