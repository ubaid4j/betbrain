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
		for(int i = 0; i < size; i++) {
 %>
    <tr>
      <th scope="row">
      	<div class="form-check form-check-inline">
  			<input class="form-check-input check303" <%= TrackedMatchList.hashtable.get((long) list.get(i).getId()) %> type="checkbox" id="<%= "_" + list.get(i).getId() %>" value="<%= list.get(i).getId() %>">
		</div>
      </th>
      <td scope="row"><%= list.get(i).getHomeTeam() %></td>
      <td scope="row"><%= list.get(i).getAwayTeam() %></td>
      <td scope="row"><%= list.get(i).getStartTime() %></td>
      <td scope="row"><%= list.get(i).getHomeTeamOdds() %></td>
      <td scope="row"><%= list.get(i).getDrawOdds() %></td>
      <td scope="row"><%= list.get(i).getAwayTeamOdds() %></td>
      <td scope="row"><button type="button" class="btn btn-secondary btn-sm del_tracked_event" value=<%=list.get(i).getId() %> onclick="handleOdds(<%=list.get(i).getId() %>, '<%= list.get(i).getHomeTeam() %>', '<%= list.get(i).getAwayTeam() %>', 'OU')">view</button></td>
      <td scope="row"><button type="button" class="btn btn-secondary btn-sm del_tracked_event" value=<%=list.get(i).getId() %> onclick="handleOdds(<%=list.get(i).getId() %>, '<%= list.get(i).getHomeTeam() %>', '<%= list.get(i).getAwayTeam() %>', 'AH')">view</button></td>

    </tr>
    
    <%}; %>
  </tbody>
</table>
