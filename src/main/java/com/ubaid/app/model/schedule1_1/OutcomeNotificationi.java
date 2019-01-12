package com.ubaid.app.model.schedule1_1;

public class OutcomeNotificationi implements OutcomeNotification
{

	public OutcomeNotificationi()
	{

	}

	@Override
	public Outcome next()
	{
		Outcome outcome = null;
		try
		{
			outcome = Schedule.notificationQueue.take();
		}
		catch (InterruptedException e)
		{
			System.out.println("Thread Interrupted at OutcomeNotificationi");
		}
		
		return outcome;
	}

}
