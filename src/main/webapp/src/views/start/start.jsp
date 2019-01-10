<%@page import="java.util.LinkedList"%>
<%@page import="com.ubaid.app.model.objects.WelcomeEvents"%>

<table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Betting Type</th>
      <th scope="col">Betting Type Name</th>
      <th scope="col">Odds</th>
      <th scope="col">Participant Name</th>
      <th scope="col">Event ID</th>
      <th scope="col">Last Changed Time</th>
      <th scope="col">Last Collected Time</th>
      <th scope="col">OutComeTypeName</th>
      <th scope="col">Threshold</th>
      <th scope="col">Sport Name</th>
      <th scope="col">Event Start Time</th>
      <th scope="col">Event Part ID</th>
      <th scope="col">Event Part Name</th>
      

    </tr>
  </thead>
  <tbody>
<% 	   
		@SuppressWarnings("unchecked")
		LinkedList<WelcomeEvents> list = (LinkedList<WelcomeEvents>) session.getAttribute("list");
		int size = list.size();
		for(int i = 0; i < size; i++) {
 %>
    <tr>
      <th scope="row"><%= i + 1 %></th>
      <td scope="row"><%= list.get(i).getBettingTypeId() %></td>
      <td scope="row"><%= list.get(i).getBettingTypeName() %></td>
      <td scope="row"><%= list.get(i).getOdds() %></td>
      <td scope="row"><%= list.get(i).getParticipantName() %></td>
      <td scope="row"><%= list.get(i).getEventID() %></td>
      <td scope="row"><%= list.get(i).getLastChangedTime() %></td>
      <td scope="row"><%= list.get(i).getLastCollectedTime() %></td>
      <td scope="row"><%= list.get(i).getOutComeTypeName() %></td>
      <td scope="row"><%= list.get(i).getThreshold() %></td>
      <td scope="row"><%= list.get(i).getSportName() %></td>
      <td scope="row"><%= list.get(i).getEventStartTime() %></td>
      <td scope="row"><%= list.get(i).getEventPartId() %></td>

    </tr>
    
    <%}; %>
  </tbody>
</table>