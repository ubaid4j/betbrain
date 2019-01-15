<jsp:include page="./views/partials/header.jsp"/>
	<div class=".container-fluid ">		

		<div>
			<button type="button" class="btn btn-secondary front_page_button" id="notification_link" onClick="notificationButtonHandler()" style="float:left;">Notifications</button>			
		</div>
 
		<div>
			<button type="button" class="btn btn-secondary front_page_button" id="start" onclick="mainPage()" style="float:left">Main Page</button>
		</div>
 
		<form style="float:left;margin-top:10px">
			<div class="form-group row" style="margin-left:2px; margin-top:2px">
			    <div class="col-sm-12">
					<input type="text" class="form-control" id="search" placeholder="Search">
				</div>
			</div>
  		</form> 

		<div>
			<button type="button" class="btn btn-secondary front_page_button" id="start" onclick="" style="float:left">Main Page</button>
		</div>


		<div id="flash_message" class="shadow p-3 mb-5 bg-white rounded">
				
		</div>

		<div style="float:right; margin-top:10px; margin-right:50px;" id="status_bar">
		</div>



 		<div style="clear:both"></div>
 			
 
     	<div class="force-overflow scrollbar style-1 app" id="app"></div>
 
 
		<div class="force-overflow scrollbar style-1 app" id="s_w" style="margin-bottom:50px; width:600px">
			
		</div>
		


		
		
	</div>
<jsp:include page="./views/partials/footer.jsp"/>
