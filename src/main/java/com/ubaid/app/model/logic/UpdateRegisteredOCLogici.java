package com.ubaid.app.model.logic;

import java.util.List;

import com.ubaid.app.model.dao.UpdateOutcome;
import com.ubaid.app.model.dao.UpdateRegisterOCDAO;
import com.ubaid.app.model.schedule1_1.Outcome;

public class UpdateRegisteredOCLogici implements UpdateRegisteredOCLogic
{

	public UpdateRegisteredOCLogici()
	{
		
	}

	@Override
	public void updateOC(List<Outcome> outcomes)
	{
		UpdateRegisterOCDAO dao = new UpdateOutcome();
		dao.updateOutcomes(outcomes);
	}

}
