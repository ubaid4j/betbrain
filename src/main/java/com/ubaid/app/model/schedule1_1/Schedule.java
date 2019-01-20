package com.ubaid.app.model.schedule1_1;

import java.util.Hashtable;
import java.util.concurrent.LinkedBlockingQueue;

import com.ubaid.app.model.schedule1_1.thresholdDetection.TrackedMatches;

public interface Schedule extends Runnable
{
	static Hashtable<Long, Outcome> trackedOutcomes = new Hashtable<>();
	static Hashtable<Integer, Hashtable<Long, TrackedMatches>> trackedMatches = new Hashtable<>(); //sport -> [matchid -> tracked match]
	LinkedBlockingQueue<Outcome> notificationQueue = new LinkedBlockingQueue<>();
	
	void schedule();
}
