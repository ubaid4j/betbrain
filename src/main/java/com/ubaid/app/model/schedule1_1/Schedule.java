package com.ubaid.app.model.schedule1_1;

import java.util.Hashtable;
import java.util.concurrent.LinkedBlockingQueue;

interface Schedule extends Runnable
{
	static Hashtable<Long, Outcome> trackedOutcomes = new Hashtable<>();
	LinkedBlockingQueue<Outcome> notificationQueue = new LinkedBlockingQueue<>();
	void schedule();
}
