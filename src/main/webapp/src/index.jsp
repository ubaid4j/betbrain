<jsp:include page="./views/partials/header.jsp"/>
	<div class="container-fluid">		
		<div class="row">
			<div class="col">
				<div>
					<button type="button" class="btn btn-secondary front_page_button" id="notification_link" onClick="notificationButtonHandler()" style="float:left;">Notifications</button>			
				</div>
		 	</div>
			<div class="col">
				<div>
					<button type="button" class="btn btn-secondary front_page_button" id="start" onclick="mainPage()" style="float:left">Main Page</button>
				</div>
			</div>
				<div class="col">
				<form style="float:left;margin-top:10px">
					<div class="form-group row" style="margin-left:2px; margin-top:2px">
					    <div class="col-sm-12">
							<input type="text" class="form-control" id="search" placeholder="Search">
						</div>
					</div>
		  		</form> 
			</div>
			<div class="col">
				<div>
					<button type="button" class="btn btn-secondary front_page_button" onclick="trackedEventsDisplay()" style="float:left">Tracked Events</button>
				</div>
			</div>
			<div class="col">
				<div id="flash_message" class="shadow p-3 mb-5 bg-white rounded">
				</div>
			</div>
			<div class="col">
				<div style="float:right; margin-top:10px; margin-right:50px;" id="status_bar">
				</div>
			</div>
			<div class="col">			
				<div style="float:right; margin-right:50px;" id="">
							<button type="button" class="btn  btn-danger btn-sm front_page_button" onclick="deleteAllTrackedEvents()" style="float:left">Delete All tracked Events</button>
				</div>
			</div>
		</div>
 		<div class="row">
			<div class="col-lg-8">
		     	<div class="force-overflow scrollbar style-1 app" id="app"></div>
			</div>
			<div class="col-lg-4">
				<div class="force-overflow scrollbar style-1 app" id="s_w" style="margin-bottom:50px;">	
			</div>
 		</div>		
	</div>
	</div>
<jsp:include page="./views/partials/footer.jsp"/>
