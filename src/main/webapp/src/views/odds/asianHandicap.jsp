<%@page import="java.util.List"%>
<%@page import="com.ubaid.app.model.objects.SubEvents"%>
<%@page import="com.ubaid.app.model.objects.Match"%>
<%@page import="com.ubaid.app.model.schedule.TrackedMatchList"%>
<%@page import="com.ubaid.app.model.schedule.Checked"%>


	<table class="table table-dark bg-dark" id="trackedEvents">
	  <thead>
	    <tr>
	      <th scope="col">Threshold</th>
	      <th scope="col">1</th>
	      <th scope="col">2</th>
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
	      <td scope="row"><%= list.get(i).getThresHold() %></td>
	      <td scope="row"><%= list.get(i).getHomeTeamOdds() %></td>
	      <td scope="row"><%= list.get(i).getAwayTeamOdds() %></td>
	    </tr>
	    
	    <%}; %>
	  </tbody>
	</table>
