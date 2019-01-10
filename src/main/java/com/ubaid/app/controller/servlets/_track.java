package com.ubaid.app.controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ubaid.app.model.logic.TrackLogic;
import com.ubaid.app.model.logic.matchLogic.Logic;
import com.ubaid.app.model.logic.matchLogic.MatchLogic;
import com.ubaid.app.model.objects.Match;
import com.ubaid.app.model.objects.eventParticipant.Track;
import com.ubaid.app.model.schedule.Checked;
import com.ubaid.app.model.schedule.TrackedMatchList;

public class _track extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	TrackLogic logic = null;
    Logic matchLogic = null;   
	
    public _track()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			logic = new TrackLogic();
			matchLogic = new MatchLogic();
			
			String _matchId = (String) request.getParameter("id");
//			String _bettingType = (String) request.getParameter("bettingType");
//			String _parentMatchId = (String) request.getParameter("parentMatchId");
			String _checked = (String) request.getParameter("checked");
			boolean checked = Boolean.parseBoolean(_checked);
			long matchId = Long.parseLong(_matchId);
			long parentMatchId = 0;
			int bettingType = 0;
			
			
			Track trackedMatch = new Track.Builder()
										.matchId(matchId)
										.parentMatchId(parentMatchId)
										.bettingType(bettingType)
										.build();
			
			long match_id = trackedMatch.getId();
			
			if(checked)
			{
					logic.add(trackedMatch);
					TrackedMatchList.trackedMatches.put(match_id, matchLogic.getMatch(matchId));
					TrackedMatchList.hashtable.put(match_id, Checked.Checked);
			
			}
			else
			{
				logic.delete(match_id);				
				TrackedMatchList.trackedMatches.remove(matchId);
				TrackedMatchList.hashtable.remove(matchId);
				
			}
				
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
        }
		finally
		{
			List<Match> matches = new ArrayList<>();
			Enumeration<Match> _matches = TrackedMatchList.trackedMatches.elements();
			while(_matches.hasMoreElements())
			{
				matches.add(_matches.nextElement());
			}
			
			request.getSession().setAttribute("tracked", matches);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

}
