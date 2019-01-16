
$(function()
{		
	get();
});

function get()
{
	$.get("/app1/_Sport", function(data)
	{
		var object = JSON.parse(data);
		var url  = object.url;
		$("#app").load(url, function(e)
		{
			setToggling();
		});
	});
}

function setToggling()
{

	$('[data-toggle="tab"]').click(function(e)
	{
		//setting $this to this
	    var $this = $(this),
	    
	    //getting loadURL
	    loadurl = $this.attr('href'),
	    
	    //getting target element
	    targ = $this.attr("aria-controls");

	    //targeted tab
	    var tab = $("#"+targ);
	    
	    $.ajax(
	    {
	    	  url: "/app1/_Events",
	    	  type: "get", 
	    	  data:
	    	  { 
	    	    name: targ
	    	  },
	    	  success: function(response)
	    	  {
	    		  //loading all the events
	    		  $("#" +targ).load("/app1/src/views/landingPage/events.jsp");
	    		  
	    		  //showing the events
	    		  $.when($(targ + "_tab").tab("show")).done(function()
	    		  {
	    			  window.setTimeout(search, 1000);
	    		  });

	    	  },
	    	  error: function(xhr)
	    	  {
	    		  console.log(xhr);
	    	  }
	    });
	});
}

function search()
{

	$("#search").on("keyup", function()
	{
	    var value = $(this).val().toLowerCase();
	    var _class = null;
	    
	    $("#events tr").filter(function()
	    {
	    	var text = $(this).text();
	    
	    	var display = $(this).text().toLowerCase().indexOf(value) > -1;
	    	var display2 = $(this).val();
	    	console.log(display2);
	    	
	    	if(display == true)
    		{
	    		_class = $(this).attr("class");
	    		$("." + _class).show();

    		}
	    	else
	    	{
	    		if(!($(this).attr("class") == _class))
	    			$(this).hide();
	    	}
	    	
	    });
	});
}


//getting match of the league
function handlerForShowingSubEvents(id, eventName)
{
	//getting the button
	var targetedButton = $("button[aria-controls='" + id + "']");
	
	//getting the state of button [if expanded or not]
	var ae = targetedButton.attr("aria-expanded");
	
	//getting the boolean [if button is expanded then decision will false, otherwise true]
	var decision = (ae == 'false');
	
	//if true
	if(decision)
	{

		//w = window
		var collapse_w = $("#_" + id);
				
		//here data will be disappeared from the second [the expanded area]
		$.when(collapse_w.empty()).done(function()
		{
			collapse_w.load("/app1/src/views/animation/spinner.html");			
		});


		//creating a request to _SubEvents
		//we are sending the id of event [match] and match name 
		$.ajax(
	    {
			  url: "/app1/_SubEvent",
			  type: "get", 
			  data:
			  { 
			    id: id,
			    eventName: eventName
			  },
			  success: function(response)
			  {
				    //on success response, first empty the spinner 
				    //then load the subEvents .jsp file 
				    //and then set Handler on the check boxes
					$.when(collapse_w.empty()).done(function()
					{
						  $.when(collapse_w.load("/app1/src/views/landingPage/subEvents.jsp").hide().fadeIn(1000)).done(function()
						  {
				    		  window.setTimeout(setHandlerOnCheckBox, 500);				  	   
						  });					
					});				  
			  },
			  error: function(xhr)
			  {
					$.when(collapse_w.empty()).done(function()
					{
						  $.when(collapse_w.load("<h3>Unknown Error</h3>").hide().fadeIn(1000)).done(function()
						  {
							  console.log("Error");
						  });					
					});				  

			  }
		});			
	}
}



function notificationButtonHandler()
{
	console.log("Button Handling");
	$.get("/app1/notification", function(data)
	{
		console.log(data);
		$.when($("#s_w").fadeOut('slow').empty()).done(function(){
			$.when($("#s_w").load("/app1/src/views/notifications/notifications.jsp").fadeIn('slow')).done(function()
			{
				start();									
			});
		});
	});
}

//global notification row
var notification_row = new Array();

function mainPage()
{
	$.get("/app1/_Sport", function(data)
	{
		var object = JSON.parse(data);
		var url  = object.url;
		$("#app").load(url, function(e)
		{
			setToggling();
		});

	});
}




function start()
{
	console.log("So, we are in the push notification method");
	
	var source = null;
	
	source = new EventSource("/app1/_notification");

	source.onopen = function(){console.log("Connected....");};

	
	source.onmessage = function(event)
	{


		var data = event.data;
		var match = JSON.parse(data);
		
		console.log(match);
		
		console.log(match);
		var row = "<tr>" +"<td scope='row'>" + match['lastUpdateTime'] + "</td>"
							+"<td scope='row'>" + match['leagueName'] + "</td>"
							+"<td scope='row'>" + match['matchName'] + "</td>"
							+"<td scope='row'>" + match['participant'] + "</td>"
							+"<td scope='row'>" + match['oldOdds'] + " -> " + match['odds']+ "</td>"
							+"<td scope='row'>" + match['threshold'] + " -> " + match['oldThreshold'] + "</td>"
				+ "</tr>";
		
		
		notification_row.push(row);
		
		$.playSound("http://www.noiseaddicts.com/samples_1w72b820/3724.mp3");		
		
		var table = $("#notification_table");
		table.prepend(row).hide().fadeIn('slow');
		
	};

	source.onopen = function(){console.log("Connected....");};
	source.onerror = function(){console.log("Error occured");};
}


function trackedEventDeleteButtonHandler()
{
	$(".del_tracked_event").click(function()
	{
		var $this = $(this);
		var id = $this.val();
		
		
		var targetRow = $this.closest('tr');

		console.log(targetRow);

		$.ajax(
	    {
	    	  url: "/app1/_deleteRegisteredOutcome",
	    	  type: "get", 
	    	  data:
	    	  { 
	    	    id: id,
	    	  },
	    	  success: function(response)
	    	  {
	    		  targetRow.fadeOut("slow");
	    	  },
	    	  error: function(xhr)
	    	  {
	    		  console.log(xhr);
	    		  var response = $.parseHTML(xhr.responseText);
	    		  alert($(response).filter( 'h1' ).text());
	    	  }
	    });

		
		
	});
}








$("#refresh1").click(function()
{
	console.log("wait");
	$.get("/app1/start", function(data)
	{

		var object = JSON.parse(data);
		var url  = object.url;
		
		$("#app").load(url);
	});	
});


//this function is responsible to show trackedEvent page
function trackedEventsDisplay()
{
	$.ajax(
    {
		  url: "/app1/_track",
		  type: "get", 
		  data:
		  { 
		    checked: null
		  },
		  success: function(response)
		  {
			 var array = JSON.parse(response);
			 console.log(array);

			 $.when(appendTrackedEvents(array)).done(function()
			 {
				var head = $("#trackedEvents thead tr");
				head.append("<th scope='col'>Del</th>");
				head.append("<th scope='col' data-defaultsign='az' data-sortcolumn='true'>League</th>");
				head.append("<th scope='col' data-defaultsign='az'>Match</th>");
				head.append("<th scope='col' data-defaultsign='az'>BettingType</th>");
				head.append("<th scope='col' data-defaultsign='az'>Affected Team</th>");
				$.bootstrapSortable({ applyLast: true });	
			 });			 
		  },
		  error: function(xhr)
		  {
			  console.log(xhr);
			  var response = $.parseHTML(xhr.responseText);
			  alert($(response).filter( 'h1' ).text());
		  }
    });

}

function appendTrackedEvents(array)
{
	 var _table = "<table class='table table-dark bg-dark table-hover sortable' id='trackedEvents'>" +
	 					"<thead>" +
	 						"<tr></tr>" +
	 					"</thead>" +
	 					"<tbody>" +
	 					"</tbody>" +
	 			  "</table>"
	 var ss = $("#s_w");

	 $.when(ss.fadeIn('slow').empty()).done(function()
	 {
		 $.when(ss.append(_table)).done(function()
	     {
			 var table = $("#trackedEvents tbody");
			 
			 $.when($.each(array, function(index, event)
		     {
				 var row = "<tr>" +
								"<td scope='row'>" + 
									"<button type='button' class='btn btn-secondary btn-sm del_tracked_event' value=" + event.outcomeId + ">del</button>" +
								"</td>" +
								"<td scope='row'>" + event.leagueName + "</td>" +
								"<td scope='row'>" + event.matchName + "</td>" +
								"<td scope='row'>" + event.bettingType + "</td>" +
								"<td scope='row'>" + event.participant + "</td>" +
							"</tr>";
				 table.append(row).hide().fadeIn(index*700);		
		     })).done(function()
		     {
		    	 trackedEventDeleteButtonHandler();
		     });

	     });
	
	 });
}

/*--------------------------------------------------------handling odds-------------------------------------------------*/

function handleOdds(eventId, homeTeam, awayTeam, bettingType, eventName)
{
	//secondary window = sw
	var sw = $("#s_w");
	if(bettingType == "AH")
	{			
		//here data will be disappeared from the second div
		$.when(sw.empty()).done(function()
		{
			sw.load("/app1/src/views/animation/wait.html");			
		});
		
		//creating get request for /_AssianHandicap
		$.ajax(
		{
			url: "/app1/_AssianHandicap",
			type: "get",
			data: {
				id: eventId,
				homeTeam: homeTeam,
				awayTeam: awayTeam,
			},
			success: function(data)
			{
				//on success, getting data [arrays of assian handicap odds]
				var object = JSON.parse(data);
				$.when(sw.empty()).done(function()
				{
					$.when($("#s_w").load("/app1/src/views/tables/AHtable.html")).done(function()
					{						
						var querySelector = "#AHtable tbody";
						//creating teams variable to store some info
						var teams = [homeTeam, awayTeam, 'Assian Handicap', eventName];
						//passing them a method which wait for AHtable to dispaly and then 
						//pass this data according to requirement
						append(querySelector, 100, object, teams);
					});
	
				});
			},
			error: function(data)
			{
				alert(data);
			}
		});
	}
	else if(bettingType == 'OU')
	{
		//here data will be disappeared from the second div
		$.when(sw.empty()).done(function()
		{
			sw.load("/app1/src/views/animation/wait.html");			
		});
		
		//creating request for _OverUnder
		$.ajax(
		{
			url: "/app1/_OverUnder",
			type: "get",
			data: {
				id: eventId,
				homeTeam: homeTeam,
				awayTeam: awayTeam,
			},
			success: function(data)
			{
				//on success: parsing data [array of over under odds]
				var object = JSON.parse(data);
				$.when(sw.empty()).done(function()
				{
					$.when($("#s_w").load("/app1/src/views/tables/AHtable.html")).done(function()
					{						
						var querySelector = "#AHtable tbody";
						//creating teams array to store important info
						var teams = [homeTeam, awayTeam, 'Over/Under', eventName];
						//passing to method which will wait until AHtable fully loaded 
						//and pass next according to odds type
						append(querySelector, 100, object, teams);
					});
	
				});
			},
			error: function(data)
			{
				alert(data);
			}
		});
	}
}

function append(selector, time, object, teams)
{
    if(document.querySelector(selector)!=null)
    {
    	appendValues(object, teams);
        return;
    }
    else
    {
        setTimeout(function()
        {
        	append(selector, time, object, teams);
        }, time);
    }
}



//this function will append values in the tables
function appendValues(object, teams)
{
	var table = $("#AHtable tbody");
	var header = $("#AHtable thead tr");
	
	//checking Odds type
	if(teams[2] == 'Over/Under')
	{
		$.when(appendInOverUnder(header, table, object, teams)).done(function()
		{
			header.append("<th scope='col'>#</th>");
			header.append("<th scope='col' class='l432' data-defaultsign='_19'>Over</th>");
			header.append("<th scope='col'>#</th>");
			header.append("<th scope='col' class='l432' data-defaultsign='_19'>Under</th>");

			$.bootstrapSortable({ applyLast: true });
		});
	}
	else if(teams[2] == 'Assian Handicap')
	{
		$.when(appendInAssianHandicapTable(header, table, object, teams)).done(function()
		{
			header.append("<th scope='col'>#</th>");
			header.append("<th scope='col' class='l432' data-defaultsign='_19'>1 (" + teams[0] + ")</th>");
			header.append("<th scope='col'>#</th>");
			header.append("<th scope='col' class='l432' data-defaultsign='_19'>2 (" + teams[1] + ")</th>");

			$.bootstrapSortable({ applyLast: true });

		});
		
	}
	
	$("#status_bar").empty();
	$("#status_bar").append("<h3>"+ teams[2] +"</h3>")
}

function appendInAssianHandicapTable(header, table, object, teams)
{
	$.when($.each(object, function(index, event)
	{

		var condition1 = event.outcome1Checked;
		var condition2 = event.outcome2Checked;
	
		var row = "<tr>" +
						"<td scope='row'>" + 
							"<div class='form-check'>" + 
								"<input class='form-check-input ahoucheckbox' type='checkbox' name='outcomeId'" + (condition1 ? 'Checked' : '') + " value='" + event.outcome1 + "' id=''>" +
								"<input type='hidden' name='homeTeam' value='" + teams[0] + "' >"  +
								"<input type='hidden' name='awayTeam' value='"+ teams[1] + "' >"  +
								"<input type='hidden' name='participant' value='" + teams[0] + "' >"  +
								"<input type='hidden' name='leagueName' value='" + teams[3] + "' >"  +
								"<input type='hidden' name='matchName' value='" + teams[0] + " VS " + teams[1] + "' >"  +
								"<input type='hidden' name='threshold' value='"+ event.homeTeamThreshold + "' >"  +
								"<input type='hidden' name='odds' value='"+ event.homeTeamOdds + "' >"  +
								"<input type='hidden' name='bettingType' value='"+ "AssianHandicap" + "' >"  +

							"</div>" +
						"</td>" +

						"<td scope='row' class='l432'>" +
							"<span>" + event.homeTeamThreshold + "</span>&nbsp;&nbsp; <span class='odds'>" + event.homeTeamOdds + "</span>" +
						"</td>" +

						
						"<td scope='row'>" + 
							"<div class='form-check'>" + 
								"<input class='form-check-input ahoucheckbox' name='outcomeId' type='checkbox'" + (condition2 ? 'Checked' : '') + " value='" + event.outcome2 + "' id=''>" +
								"<input type='hidden' name='homeTeam' value='" + teams[0] + "' >"  +
								"<input type='hidden' name='awayTeam' value='"+ teams[1] + "' >"  +
								"<input type='hidden' name='participant' value='" + teams[1] + "' >"  +
								"<input type='hidden' name='leagueName' value='" + teams[3] + "' >"  +
								"<input type='hidden' name='matchName' value='" + teams[0] + " VS " + teams[1] + "' >"  +
								"<input type='hidden' name='threshold' value='"+ event.awayTeamThreshold + "' >"  +
								"<input type='hidden' name='odds' value='"+ event.awayTeamOdds + "' >"  +
								"<input type='hidden' name='bettingType' value='"+ "AssianHandicap" + "' >"  +

							"</div>" +
						"</td>" +
	
						"<td scope='row' class='l432'>" +
							"<span>" + event.awayTeamThreshold + "</span>&nbsp;&nbsp; <span class='odds'>" + event.awayTeamOdds + "</span>" +
						"</td>" +
					
					"</tr>";
		
					table.append(row).hide().fadeIn(index*700);		
										
	})).done(function()
	{
		onChangeOfAHOUCheckbox();
	});	
}

function appendInOverUnder(header, table, object, teams)
{
	$.when($.each(object, function(index, event)
	{

		var condition1 = event.outcome1Checked;
		var condition2 = event.outcome2Checked;

		
		var row = "<tr>" +
						"<td scope='row'>" + 
							"<div class='form-check'>" + 
								"<input class='form-check-input ahoucheckbox' type='checkbox' name='outcomeId'" + (condition1 ? 'Checked' : '') + " value='" + event.outcome1 + "' id=''>" +
								"<input type='hidden' name='homeTeam' value='" + teams[0] + "' >"  +
								"<input type='hidden' name='awayTeam' value='"+ teams[1] + "' >"  +
								"<input type='hidden' name='participant' value='" + "Under" + "' >"  +
								"<input type='hidden' name='leagueName' value='" + teams[3] + "' >"  +
								"<input type='hidden' name='matchName' value='" + teams[0] + " VS " + teams[1] + "' >"  +
								"<input type='hidden' name='threshold' value='"+ event.threshold + "' >"  +
								"<input type='hidden' name='odds' value='"+ event.underTeamOdds + "' >"  +
								"<input type='hidden' name='bettingType' value='"+ "OverUnder" + "' >"  +

							"</div>" +
						"</td>" +

						"<td scope='row' class='l432'>" +
							"<span>" + event.threshold + "</span>&nbsp;&nbsp; <span class='odds'>" + event.underTeamOdds + "</span>" +
						"</td>" +

						
						"<td scope='row'>" + 
							"<div class='form-check'>" + 
								"<input class='form-check-input ahoucheckbox' type='checkbox' name='outcomeId'" + (condition2 ? 'Checked' : '') + " value='" + event.outcome2 + "' id=''>" +
								"<input type='hidden' name='homeTeam' value='" + teams[0] + "' >"  +
								"<input type='hidden' name='awayTeam' value='"+ teams[1] + "' >"  +
								"<input type='hidden' name='participant' value='" + "Over" + "' >"  +
								"<input type='hidden' name='leagueName' value='" + teams[3] + "' >"  +
								"<input type='hidden' name='matchName' value='" + teams[0] + " VS " + teams[1] + "' >"  +
								"<input type='hidden' name='threshold' value='"+ event.threshold + "' >"  +
								"<input type='hidden' name='odds' value='"+ event.overTeamOdds + "' >"  +
								"<input type='hidden' name='bettingType' value='"+ "OverUnder" + "' >"  +
	
							"</div>" +
						"</td>" +

						"<td scope='row' class='l432'>" +
							"<span>" + event.threshold + "</span>&nbsp;&nbsp; <span class='odds'>" + event.overTeamOdds + "</span>" +
						"</td>" +
					
					"</tr>";
		
					table.append(row).hide().fadeIn(index*700);		
					
					
	})).done(function()
	{
		onChangeOfAHOUCheckbox();
	});
}



/*Handler on check boxes*/

function setHandlerOnCheckBox()
{
	console.log("setting check box handler");

	$(".check303").change(function(event)
	{
		$this = $(this);	
		
		var checked = this.checked;
		var id = $this.attr("value");
		var form = $this.parent();
		var inputs = form.children();
		
		var map = {};
				
		$.when(inputs.each(function(){map[$(this).attr("name")] = $(this).val()})).done(function()
		{						

			var data =JSON.stringify(map);
			
			console.log(map);
			
			$.ajax(
    	    {
    	    	  url: "/app1/HADRegistrar",
    	    	  type: "get", 
    	    	  data:
    	    	  { 
    	    	    data: data,
    	    	    checked: checked
    	    	  },
    	    	  success: function(response)
    	    	  {
    	    		  var notification;
    	    		  
    	    		  if(checked)
    	    			  notification = map.leagueName + " odds added";
    	    		  else
    	    			  notification = map.leagueName + " odds removed";
    	    			  
    	    		  $("#flash_message").text(notification); 
    	    			 
    	    		  
    	    		  $("#flash_message").show('slow', function()
    	    		  {
    	    			 setTimeout(function()
    	    			 {
    	    				$("#flash_message").hide('slow'); 
    	    			 }, 1000);
    	    		  });	
    	    		  
    	    	  },
    	    	  error: function(xhr)
    	    	  {
    	    		  console.log(xhr);
    	    		  var response = $.parseHTML(xhr.responseText);
    	    		  alert($(response).filter( 'h1' ).text());
    	    	  }
    	    });				
		});				
	});
}


function onChangeOfAHOUCheckbox()
{
	$(".ahoucheckbox").change(function(event)
	{
		$this = $(this);	
		
		var checked = this.checked;
		var id = $this.attr("value");
		var form = $this.parent();
		var inputs = form.children();
		var map = {};
				
		$.when(inputs.each(function(){map[$(this).attr("name")] = $(this).val()})).done(function()
		{						

			var isAH = map.bettingType == "AH";			
			var data =JSON.stringify(map);
			
			$.ajax(
    	    {
    	    	  url: "/app1/_AHOURegisterar",
    	    	  type: "get", 
    	    	  data:
    	    	  { 
    	    	    data: data,
    	    	    checked: checked,
    	    	    isAH : isAH
    	    	  },
    	    	  success: function(response)
    	    	  {
    	    		  var notification;
    	    		  
    	    		  if(checked)
    	    			  notification = map.leagueName + " odds added";
    	    		  else
    	    			  notification = map.leagueName + " odds removed";
    	    			  
    	    		  $("#flash_message").text(notification); 
    	    			 
    	    		  
    	    		  $("#flash_message").show('slow', function()
    	    		  {
    	    			 setTimeout(function()
    	    			 {
    	    				$("#flash_message").hide('slow'); 
    	    			 }, 1000);
    	    		  });	
    	    		  
    	    	  },
    	    	  error: function(xhr)
    	    	  {
    	    		  console.log(xhr);
    	    		  var response = $.parseHTML(xhr.responseText);
    	    		  alert($(response).filter( 'h1' ).text());
    	    	  }
    	    });				
		});				
	});
	
}

/*------------------------------------------------------------------------------------*/







