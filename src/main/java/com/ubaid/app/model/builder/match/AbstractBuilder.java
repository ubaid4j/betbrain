package com.ubaid.app.model.builder.match;

import com.ubaid.app.model.logic.OddsLogic;
import com.ubaid.app.model.logic.ParticipantRoleLogic;

public abstract class AbstractBuilder implements IMatchBuilder
{

	protected OddsLogic oddsLogic;
	protected ParticipantRoleLogic roleLogic;

	
	public AbstractBuilder()
	{
		oddsLogic = new OddsLogic();
		roleLogic = new ParticipantRoleLogic();
	}
}
