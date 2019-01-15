package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import com.ubaid.app.model.objects.Entity;
import com.ubaid.app.model.overunder.OverUnderRawData;

public class OverUnderBuilder extends AbstractBuilder
{

	public OverUnderBuilder()
	{
	}

	@Override
	Entity _buildEntity(ResultSet resultSet) throws SQLException
	{
		return new OverUnderRawData.Builder()
					.odds(resultSet.getFloat(1))
					.key(resultSet.getLong(4))
					.threshold(resultSet.getFloat(3))
					.outcomeId(resultSet.getLong(4))
					.build();
	}
	
	
	

	
	
	@SuppressWarnings("unused")
	private long convert(String str)
	{
		if(str.contains("?coupon"))
		{
			String[] array = str.split("\\|");
			String target = array[array.length - 1];
			try
			{
				long key = Long.parseLong(target);
				return key;
			}
			catch(NumberFormatException exp)
			{
				String[] parts = target.split("E");
				String firstPart = parts[0];
				String secondPart = parts[1];
				try
				{
					double n1 = Double.parseDouble(firstPart);
					int power = Integer.parseInt(secondPart);
					long multiply = (long) Math.pow(10, power);
					long targetNumber = (long) (n1 * multiply);
					return targetNumber;
				}
				catch(NumberFormatException exp2)
				{
					exp.printStackTrace();
				}
			}
		}
		else if(str.contains("&Handicap"))
		{
			try
			{
				//"78322321-1960983480&Handicap=1.5";
				String[] array = str.split(Pattern.quote("&"));
				String firstPart = array[0];
				String[] parts = firstPart.split(Pattern.quote("-"));
				String number = parts[1];
				long key = Long.parseLong(number);
				return key;
			}
			catch(NumberFormatException exp)
			{
				exp.printStackTrace();
			}
		}
		return -1;
	}

}
