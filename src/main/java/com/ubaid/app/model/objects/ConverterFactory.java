package com.ubaid.app.model.objects;

final public class ConverterFactory
{
	private static Converter converter = null;
	
	private ConverterFactory()
	{
		
	}
	
	public static Converter getConverter()
	{
		if(converter == null)
			converter = new Convert_();
		return converter;
	}
}
