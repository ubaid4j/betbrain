<%@page import="com.ubaid.app.model.schedule1_1.Scheduler"%>
<%@page import="java.util.List"%>
<%@page import="com.ubaid.app.model.objects.SubEvents"%>
<%@page import="com.ubaid.app.model.objects.Match"%>
<%@page import="com.ubaid.app.model.schedule.TrackedMatchList"%>
<%@page import="com.ubaid.app.model.schedule.Checked"%>


<table class="table table-dark bg-dark">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Home Team</th>
      <th scope="col">Away Team</th>
      <th scope="col">Start Time</th>
      <th scope="col">1</th>
      <th scope="col">X</th>
      <th scope="col">2</th>
      <th scope="col">O/U</th>
      <th scope="col">A/H</th>
    </tr>
  </thead>
  <tbody>
<% 	   
		@SuppressWarnings("unchecked")
		List<Match> list = (List<Match>) session.getAttribute("list_subEvent");
		int size = list.size();
		String eventName = (String) session.getAttribute("eventName");
		for(int i = 0; i < size; i++) {
 %>
    <tr>
      <th scope="row">
      	<div class="form-check form-check-inline">
  			<input class="form-check-input check303"
  				<%= Scheduler.getTrackedNotification().get(list.get(i).getHomeTeamOutcomeId()) != null ? "checked" : "" %>
  				type="checkbox" id="<%= "_" + list.get(i).getId() %>"
  			>
			<input type='hidden' name='homeTeam' value='<%= list.get(i).getHomeTeam() %>' >
			<input type='hidden' name='awayTeam' value='<%= list.get(i).getAwayTeam() %>' >  
			<input type='hidden' name='leagueName' value='<%= eventName %>' >  
			<input type='hidden' name='matchName' value='<%= list.get(i).getHomeTeam() %> vs <%= list.get(i).getAwayTeam() %>' >  
			<input type='hidden' name='homeTeamOdds' value='<%= list.get(i).getHomeTeamOdds() %>'>  
			<input type='hidden' name='awayTeamOdds' value='<%= list.get(i).getAwayTeamOdds() %>'>  
			<input type='hidden' name='drawOdds' value='<%= list.get(i).getDrawOdds() %>'>  
			<input type='hidden' name='homeTeamOutcomeId' value='<%= list.get(i).getHomeTeamOutcomeId() %>'>  
			<input type='hidden' name='awayTeamOutcomeId' value='<%= list.get(i).getAwayTeamOutcomeId() %>'>  
			<input type='hidden' name='drawOutcomeId' value='<%= list.get(i).getDrawOutcomeId() %>'>  
			<input type='hidden' name='bettingType' value='HomeDrawAway'>  

		</div>
      </th>
      <td scope="row"><%= list.get(i).getHomeTeam() %></td>
      <td scope="row"><%= list.get(i).getAwayTeam() %></td>
      <td scope="row"><%= list.get(i).getStartTime() %></td>
      <td scope="row"><%= list.get(i).getHomeTeamOdds() %></td>
      <td scope="row"><%= list.get(i).getDrawOdds() %></td>
      <td scope="row"><%= list.get(i).getAwayTeamOdds() %></td>
      <td scope="row"><button type="button" class="btn btn-secondary btn-sm del_tracked_event" value=<%=list.get(i).getId() %> onclick="handleOdds(<%=list.get(i).getId() %>, '<%= list.get(i).getHomeTeam() %>', '<%= list.get(i).getAwayTeam() %>', 'OU', '<%= eventName%>')">view</button></td>
      <td scope="row"><button type="button" class="btn btn-secondary btn-sm del_tracked_event" value=<%=list.get(i).getId() %> onclick="handleOdds(<%=list.get(i).getId() %>, '<%= list.get(i).getHomeTeam() %>', '<%= list.get(i).getAwayTeam() %>', 'AH', '<%= eventName%>')">view</button></td>

    </tr>
    
    <%}; %>
  </tbody>
</table>
