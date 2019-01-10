<%@page import="com.ubaid.app.model.objects.Sport"%>
<%@page import="java.util.List"%>

	<ul class="nav nav-tabs" id="myTab" role="tablist" style="cursor: pointer;">
		
		<% @SuppressWarnings("unchecked")
		   List<Sport> sportList = (List<Sport>) session.getAttribute("sportList"); %>
		<%int size = sportList.size();
		  
			System.out.println(size);
		 for(int i = 0; i < size; i++)
		 	{ %>
			<li class="nav-item">
				<a class="nav-link" id="<%= sportList.get(i).getName() + "_tab" %>" data-toggle="tab"  data-target="<%= "#" + sportList.get(i).getName() %>" role="tab" aria-controls="<%= sportList.get(i).getName() %>" aria-selected="false"><%=sportList.get(i).getName() %></a>
		  	</li>
	  	<%} %>
	  			  	
	</ul>


	
	<div class="tab-content" id="myTabContent">
	  
	  <% for(int i = 0; i < size; i++)
	  {%>		  
		  <div class="tab-pane fade" id="<%= sportList.get(i).getName() %>" role="tabpanel" aria-labelledby="<%= sportList.get(i).getName() + "-tab" %>" ><%= sportList.get(i).getName() %></div>
	  <%} %>
	</div>					      				
