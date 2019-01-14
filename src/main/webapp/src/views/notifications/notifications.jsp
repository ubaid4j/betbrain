<%@page import="java.util.List"%>
<%@page import="com.ubaid.app.model.objects.SubEvents"%>
<%@page import="com.ubaid.app.model.objects.Match"%>
<%@page import="com.ubaid.app.model.schedule.TrackedMatchList"%>
<%@page import="com.ubaid.app.model.schedule.Checked"%>


<table class="table table-dark bg-dark" id="notification_table">
  <thead>
    <tr>
      <th scope="col">Update Time</th>
      <th scope="col">League</th>
      <th scope="col">Match</th>
      <th scope="col">Affected Team</th>
      <th scope="col">Odds Changed</th>
      <th scope="col">Threshold Changed</th>
    </tr>
  </thead>
  <tbody>
<% 	   
		@SuppressWarnings("unchecked")
		List<Match> list = (List<Match>) session.getAttribute("matches");
		int size = list.size();
		for(int i = 0; i < size; i++) {
 %>
    <tr>
      
      <td scope="row"><%= list.get(i).getTimestamp() %></td>
      <td scope="row"><%= list.get(i).getHomeTeam() %></td>
      <td scope="row"><%= list.get(i).getAwayTeam() %></td>
      <td scope="row"><%= list.get(i).getHomeTeamOdds() %></td>
      <td scope="row"><%= list.get(i).getDrawOdds() %></td>
      <td scope="row"><%= list.get(i).getAwayTeamOdds() %></td>

    </tr>
    
    <%}; %>
  </tbody>
</table>
