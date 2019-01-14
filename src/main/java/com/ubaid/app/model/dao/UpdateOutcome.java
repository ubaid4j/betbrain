package com.ubaid.app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ubaid.app.model.schedule1_1.Outcome;
import com.ubaid.app.model.singleton.DataSource;

public class UpdateOutcome implements UpdateRegisterOCDAO
{

	private static final String query = "UPDATE registeredOutcome SET odds = ?, threshold = ?, lastUpdatedTime = ? where id = ?";
	
	public UpdateOutcome()
	{

	}

	@Override
	public void updateOutcomes(List<Outcome> outcomes)
	{
		try
		{
			Connection connection = DataSource.getConnection();
			PreparedStatement st = connection.prepareStatement(query);
			for(Outcome outcome : outcomes)
			{
				try
				{
					st.setFloat(1, outcome.getOdds());
					st.setFloat(2, outcome.getThreshold());
					st.setTimestamp(3, outcome.getChangedTime());
					st.setLong(4, outcome.getId());
					st.addBatch();
				}
				catch(SQLException exp)
				{
					exp.printStackTrace();
				}
			}
			
			st.executeBatch();
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
	}

}
