package com.ubaid.app.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubaid.app.model.schedule.TrackedMatchList;

public class Controller
{
	private ExecutorService service;
	private static Controller controller = null;
	private boolean terminate = false;
	
	public static void pauseNotificationSystem()
	{
//		Controller controller = Controller.getController();
//		controller.setTerminate(true);
//		controller.getService().shutdownNow();
	}
	
	public static void resumeNotificationSystem()
	{
//		Controller controller = Controller.getController();
//		ExecutorService service = Executors.newFixedThreadPool(1);
//		service.execute(new TrackedMatchList());
//		controller.setSevice(service);
//		controller.setTerminate(false);
	}
	
	
	public void setSevice(ExecutorService service)
	{
		this.service = service;
	}
	
	public ExecutorService getService()
	{
		return service;
	}
	
	private Controller()
	{
		
	}
	
	public static Controller getController()
	{
		if(controller == null)
			controller = new Controller();
		return controller;
	}

	public boolean isTerminate() {
		return terminate;
	}

	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}
}
