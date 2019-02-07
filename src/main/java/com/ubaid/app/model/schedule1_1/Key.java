package com.ubaid.app.model.schedule1_1;

/**
 * this class is for a key which hold (id of (outcome or match) and providerId)
 * we will maintain the hashtable by using key ----> outcome [or trackedMatch]
 * @author UbaidurRehman
 *
 */
public final class Key
{

	/**************************do not delete**********************************/
	
//	@Override
//	public boolean equals(Object obj)
//	{
//		Key key = (Key) obj;
//		if(key.getId() == getId() && key.getProviderId() == getProviderId())
//			return true;
//		return false;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (providerId ^ (providerId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Key other = (Key) obj;
		if (id != other.id)
			return false;
		if (providerId != other.providerId)
			return false;
		return true;
	}

	
	
	/*************************************************************************/
	
	private long id;

	private long providerId;
	
	/**
	 * 
	 * @param id
	 * @param providerId
	 */
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
