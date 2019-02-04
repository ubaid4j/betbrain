package com.ubaid.app.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ubaid.app.model.schedule1_1.oddsDetection.OddsDetection;
import com.ubaid.app.model.schedule1_1.thresholdDetection.ThresholdDetection;

/**
 * this controller is a singleton class, it contain a threadpool 
 * which is responsible to run scheduler
 * @author ubaid
 *
 */
public class Controller
{
	//class level variables
	private ExecutorService service = null;
	private static Controller controller = null;

	private ExecutorService checkNullity(ExecutorService service)
	{
		if(service == null)
			service = Executors.newFixedThreadPool(2);
		return service;
	}
	
	private ExecutorService checkTermination(ExecutorService service)
	{
		if(service.isTerminated())
			service = Executors.newFixedThreadPool(2);
		else
		{
			service.shutdownNow();
			service = Executors.newFixedThreadPool(2);			
		}
		return service;
	}
	
	
	//this method start the Schedular
	public void startSchedular()
	{
		try
		{
			//first, we check if the schedular is null, if it is null, then creating a pool
			service = checkNullity(service);
			
			//then we check, service is already referenced with a pool, if it is then we shut down it and then again create
			service = checkTermination(service);

			//executing Scheduler
			service.execute(new OddsDetection());
//			service.execute(new ThresholdDetection());
			service.shutdown();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	
	//this method stop the schedular
	public void stopSchedular()
	{
		try
		{
			service.shutdownNow();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	
		
	private Controller()
	{
		service = Executors.newFixedThreadPool(2);
	}
	
	//single ton
	public static Controller getController()
	{
		if(controller == null)
			controller = new Controller();
		return controller;
	}
}
