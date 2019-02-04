package com.ubaid.app.model.schedule1_1;

/**
 * this class is for a key which hold (id of (outcome or match) and providerId)
 * we will maintain the hashtable by using key ----> outcome [or trackedMatch]
 * @author UbaidurRehman
 *
 */
public final class Key
{

	private long id;
	private long providerId;
	
	public Key(long id, long providerId)
	{
		setId(id);
		setProviderId(providerId);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}


}
