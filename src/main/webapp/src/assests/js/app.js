
$(function()
{		
	get();
});



function notificationButtonHandler()
{
	console.log("Button Handling");
	$.get("/app1/notification", function(data)
	{
		console.log(data);
		$.when($("#s_w").load("/app1/src/views/notifications/notifications.jsp")).done(function()
		{
			start();
		});
		
	});

}

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
		
		console.log(row);
		
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
	    	  url: "/app1/_track",
	    	  type: "get", 
	    	  data:
	    	  { 
	    	    id: id,
	    	    checked: false
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



function setToggling()
{
	console.log("Toggling");
	$('[data-toggle="tab"]').click(function(e)
	{
		//setting $this to this
	    var $this = $(this),
	    
	    //getting loadURL
	    loadurl = $this.attr('href'),
	    
	    //getting target element
	    targ = $this.attr("aria-controls");

	    
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
//		    		  window.setTimeout(getDataFromButtonForOdds, 6000);

	    		  });
//	    		  waitForElementToDisplay('[data-toggle="collapse"]', 200);

	    	  },
	    	  error: function(xhr)
	    	  {
	    		  console.log(xhr);
	    	  }
	    });
	});

}




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
			  $.when($("#s_w").load("/app1/src/views/tracked/events.jsp")).done(function()
			  {
	    		  window.setTimeout(trackedEventDeleteButtonHandler, 1000);
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

//getting subevents
function handlerForShowingSubEvents(id, eventName)
{
	
	var targetedButton = $("button[aria-controls='" + id + "']");
	var ae = targetedButton.attr("aria-expanded");
	
	var decision = (ae == 'false');
	
	
	if(decision)
	{
		console.log("Creating get request for subevents");

		//w = window
		var collapse_w = $("#_" + id);
				
		//here data will be disappeared from the second div
		$.when(collapse_w.empty()).done(function()
		{
			collapse_w.load("/app1/src/views/animation/spinner.html");			
		});

		
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


/*
function getDataFromButtonForOdds()
{
	
	console.log("Registering Buttons");
	
	var buttons = $('[data-toggle="collapse"]');
	
	$('[data-toggle="collapse"]').click(function(e)
	{
		var $this = $(this);
		var id = $this.attr("aria-controls");

		console.log("Creating get request for /app1/_SubEvent");

	    $.ajax(
	    {
	    	  url: "/app1/_SubEvent",
	    	  type: "get", 
	    	  data:
	    	  { 
	    	    id: id
	    	  },
	    	  success: function(response)
	    	  {
	    		  $.when($("#_" + id).load("/app1/src/views/landingPage/subEvents.jsp")).done(function()
	    		  {
	    			  console.log("Loading done");
		    		  window.setTimeout(setHandlerOnCheckBox, 1000);
	    			  	   
	    		  });
	    	  },
	    	  error: function(xhr)
	    	  {
	    		  console.log(xhr);
	    	  }
	    });
		
	});
}
*/




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

function handleOdds(eventId, homeTeam, awayTeam, bettingType, eventName)
{
/*
	console.log("Event ID is: " + eventId);
	console.log("Home Team is: " + homeTeam);
	console.log("away Team is: " + awayTeam);
	console.log("betting type is: " + bettingType);
*/
	
	
	//secondary window = sw
	var sw = $("#s_w");
	if(bettingType == "AH")
	{
			
		//here data will be disappeared from the second div
		$.when(sw.empty()).done(function()
		{
			sw.load("/app1/src/views/animation/wait.html");			
		});
		
	
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
				var object = JSON.parse(data);
				$.when(sw.empty()).done(function()
				{
					$.when($("#s_w").load("/app1/src/views/tables/AHtable.html")).done(function()
					{						
						var querySelector = "#AHtable tbody";
						var teams = [homeTeam, awayTeam, 'Assian Handicap', eventName];
						waitForElementToDisplay(querySelector, 100, object, teams);
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
				var object = JSON.parse(data);
				console.log(object);
				$.when(sw.empty()).done(function()
				{
					$.when($("#s_w").load("/app1/src/views/tables/AHtable.html")).done(function()
					{						
						var querySelector = "#AHtable tbody";
						var teams = [homeTeam, awayTeam, 'Over/Under', eventName];
						waitForElementToDisplay(querySelector, 100, object, teams);
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

//this function will append values in the tables
function appendValues(object, teams)
{
	var table = $("#AHtable tbody");
	var header = $("#AHtable thead tr");
	
	if(teams[2] == 'Over/Under')
	{
		$.when(appendInOverUnder(header, table, object, teams)).done(function()
		{
			header.append("<th scope='col'>#</th>");
			header.append("<th scope='col' class='l432' data-defaultsign='_19'>Over</th>");
			header.append("<th scope='col'>#</th>");
			header.append("<th scope='col' class='l432' data-defaultsign='_19'>2 (" + teams[1] + ")</th>");

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


function waitForElementToDisplay(selector, time, object, teams)
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
            waitForElementToDisplay(selector, time, object, teams);
        }, time);
    }
}



