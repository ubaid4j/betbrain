package com.ubaid.app.controller;

import com.ubaid.app.model.schedule1_1.Key;

public class Test
{
	public static void main(String [] args)
	{
		Key key = new Key(7308445711124628l, 3000107);
		Key key2 = new Key(7308445711124628l, 3000107);

		int hash1 = key.hashCode();
		int hash2 = key.hashCode();
		
		boolean d = key == key2;
		boolean d2 = key.equals(key2);
		
		
		
	}
	
}