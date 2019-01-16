package com.ubaid.app.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.ubaid.app.model.schedule1_1.Scheduler;

public class Controller
{
	private ExecutorService service = null;
	private static Controller controller = null;
	private boolean terminate = false;
	
	
	
	
	private ExecutorService checkNullity(ExecutorService service)
	{
		if(service == null)
			service = Executors.newFixedThreadPool(1);
		return service;
	}
	
	private ExecutorService checkTermination(ExecutorService service)
	{
		if(service.isTerminated())
			service = Executors.newFixedThreadPool(1);
		else
		{
			service.shutdownNow();
			service = Executors.newFixedThreadPool(1);			
		}
		return service;
	}
	
	
	//this method start the Schedular
	public void startSchedular()
	{
		try
		{
			service = checkNullity(service);
			service = checkTermination(service);
			service.execute(new Scheduler());
			service.shutdown();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	
	public void stopSchedular()
	{
		try
		{
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
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
		service = Executors.newFixedThreadPool(1);
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
