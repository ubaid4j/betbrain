<%@page import="java.util.List"%>
<%@page import="com.ubaid.app.model.objects.SubEvents"%>
<%@page import="com.ubaid.app.model.objects.Match"%>
<%@page import="com.ubaid.app.model.schedule.TrackedMatchList"%>
<%@page import="com.ubaid.app.model.schedule.Checked"%>


	<table class="table table-dark bg-dark" id="trackedEvents">
	  <thead>
	    <tr>
	      <th scope="col">Delete</th>
	      <th scope="col">League</th>
	      <th scope="col">Home Team</th>
	      <th scope="col">Away Team</th>
	    </tr>
	  </thead>
	  <tbody>
	<% 	   
			@SuppressWarnings("unchecked")
			List<Match> list = (List<Match>) session.getAttribute("tracked");
			int size = list.size();
			for(int i = 0; i < size; i++) {
	 %>
	    <tr>
	      <td scope="row"><button type="button" class="btn btn-secondary btn-sm del_tracked_event" value=<%=list.get(i).getId() %>>del</button></td>
	      <td scope="row"><%= list.get(i).getTournamentName() %></td>
	      <td scope="row"><%= list.get(i).getHomeTeam() %></td>
	      <td scope="row"><%= list.get(i).getAwayTeam() %></td>
	      <td scope="row"><%= list.get(i).getHomeTeamOdds() %></td>
	    </tr>
	    
	    <%}; %>
	  </tbody>
	</table>
