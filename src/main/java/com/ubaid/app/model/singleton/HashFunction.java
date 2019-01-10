package com.ubaid.app.model.singleton;

import java.util.HashMap;

public class HashFunction
{
	private static HashMap<Character, Character> hashMap = createHashFunction();
	private static HashMap<Character, Character> hashMap2 = createHashFunction2();
	private HashFunction()
	{
		
	}

	
	private static HashMap<Character, Character> createHashFunction()
	{
		if(hashMap != null)
		{
			hashMap.put('0', 'a');
			hashMap.put('1', 'b');
			hashMap.put('2', 'c');
			hashMap.put('3', 'd');
			hashMap.put('4', 'e');
			hashMap.put('5', 'f');
			hashMap.put('6', 'e');
			hashMap.put('7', 'j');
			hashMap.put('8', 'k');
			hashMap.put('9', 'l');
		}
		else
		{
			hashMap = new HashMap<>();
			hashMap.put('0', 'a');
			hashMap.put('1', 'b');
			hashMap.put('2', 'c');
			hashMap.put('3', 'd');
			hashMap.put('4', 'e');
			hashMap.put('5', 'f');
			hashMap.put('6', 'e');
			hashMap.put('7', 'j');
			hashMap.put('8', 'k');
			hashMap.put('9', 'l');
		}
		
		return hashMap;
	}

	private static HashMap<Character, Character> createHashFunction2()
	{
		if(hashMap2 != null)
		{
			hashMap2.put('0', 'm');
			hashMap2.put('1', 'n');
			hashMap2.put('2', 'o');
			hashMap2.put('3', 'p');
			hashMap2.put('4', 'q');
			hashMap2.put('5', 'r');
			hashMap2.put('6', 's');
			hashMap2.put('7', 't');
			hashMap2.put('8', 'u');
			hashMap2.put('9', 'v');
		}
		else
		{
			hashMap2 = new HashMap<>();
			hashMap2.put('0', 'm');
			hashMap2.put('1', 'n');
			hashMap2.put('2', 'o');
			hashMap2.put('3', 'p');
			hashMap2.put('4', 'q');
			hashMap2.put('5', 'r');
			hashMap2.put('6', 's');
			hashMap2.put('7', 't');
			hashMap2.put('8', 'u');
			hashMap2.put('9', 'v');
		}
		
		return hashMap2;
	}

	
	
	public static String getHash(String id)
	{
		String str = "";
		for(int i = 0; i < id.length(); i++)
		{
			str += hashMap.get(id.charAt(i));
		}
		
		return str;
	}
	
	public static String getHash2(String id)
	{
		String str = "";
		for(int i = 0; i < id.length(); i++)
		{
			str += hashMap2.get(id.charAt(i));
		}
		
		return str;
	}

}
