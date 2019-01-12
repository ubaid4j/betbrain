<%@page import="java.util.LinkedList"%>
<%@page import="com.ubaid.app.model.objects.Events"%>


<table class="table table-dark" id="events">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Event Name</th>
      <th scope="col">Location</th>
    </tr>
  </thead>
  <tbody>
<% 	   
		@SuppressWarnings("unchecked")
		LinkedList<Events> list = (LinkedList<Events>) session.getAttribute("list");
		int size = list.size();
		for(int i = 0; i < size; i++) {
 %>
    <tr class="<%= list.get(i).getHash() %>">
      <th scope="row"><%= i + 1 %></th>
      <td scope="row">
      	<button onclick="handlerForShowingSubEvents(<%=list.get(i).getId()%>, '<%= list.get(i).getEventName() %>')" class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="<%= "#" + list.get(i).getHash() %>" aria-expanded="false" aria-controls="<%=list.get(i).getId()%>">
	      	<%= list.get(i).getEventName() %>
		</button>
				
      </td>
      <td scope="row"><%= list.get(i).getLocationName() %></td>

		
    </tr>
    	
   	<tr class="<%= list.get(i).getHash() %>">
 		<td scope="row" colspan="5">
	 		<div class="collapse" id="<%= list.get(i).getHash() %>">
	  			<div class="card card-body bg-dark">
	  				<div id="<%= "_" + list.get(i).getId()%>">
	  				
	  				</div>
					<input type="hidden" value="<%= i + 1 %>"> 
					<input type="hidden" value="<%= list.get(i).getEventName() %>"> 
					<input type="hidden" value="<%= list.get(i).getLocationName() %>"> 
					 				
	  			</div>
			</div>
 		</td>
 	</tr>
    
        
    <%}; %>
  </tbody>
</table>


