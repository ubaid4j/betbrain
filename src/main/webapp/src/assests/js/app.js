
$(function()
{		
	if(!$(location).attr('href').includes('/app1/src/views/outcomes/removedOutcomes.jsp'))
	{
		console.log("get(sport)");
		get();		
	}
});

function homePage()
{
//	window.location = "/app1/src/index.jsp";
	$.when($(location).attr('href', "/app1/src/index.jsp")).done(function()
	{
		get();		
	});
}

function removedOutcomePage()
{
	$.when($(location).attr('href', "/app1/src/views/outcomes/removedOutcomes.jsp")).done(function()
	{
		getRemovedItem();
	});
}



function get()
{
	
	$.ajax(
	{
		url: "/app1/AppRequestHandler", 
		type: "get",
		data : {
			className:"sport"
		},
		success: function(data)
		{
			var sports = JSON.parse(data);
			
			var ul = '<ul class="nav nav-tabs" id="myTab" role="tablist" style="cursor: pointer;"></ul>';
			var tab_content = '<div class="tab-content" id="myTabContent"></div>';
			var app = $("#app");
			
			$.when($.when($.when(app.children().fadeOut('fast').remove()).done(function()
			{
				app.append(ul);
				app.append(tab_content);
			})).done(function()
			{
				ul = $("#myTab");
				tab_content = $("#myTabContent");
				var li;
				var tab_pane;
				$.each(sports, function(index, sport)
				{
					li = '<li class="nav-item"><a class="nav-link" id="' + sport.name + "_tab"   + '"data-toggle="tab"  data-target="' + "#" + sport.name + '" role="tab" aria-controls="' + sport.name + '" aria-selected="false">' + sport.name + '</a></li>';
					ul.append(li).hide().fadeIn(index * 700);
					
					tab_pane = '<div class="tab-pane fade" id="' + sport.name + '" role="tabpanel" aria-labelledby="' + sport.name +  "-tab" + '" ></div>';
					tab_content.append(tab_pane);
				});
			})).done(function()
			{
				setToggling();
			});					
		},
		error: function(xhr)
		{
			alert(xhr);
		}
		
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
	    sportName = $this.attr("aria-controls");

	    //targeted pane
	    var pane = $("#"+sportName);
	    
	    $.ajax(
	    {
	    	  url: "/app1/AppRequestHandler",
	    	  type: "get", 
	    	  data:
	    	  { 
	    	    name: sportName,
	    	    className : 'events'
	    	  },
	    	  success: function(response)
	    	  {

	    		  $.when($.when(showEvents(response, pane, sportName)).done(function()
	    		  {
	    			  var header = '<tr><th scope="col">#</th><th scope="col">Event Name</th><th scope="col">Location</th></tr>';
	    			  $("#" + sportName + " thead").append(header);
	    			  
	    		  })).done(function()
	    		  {
		    		  //showing the events
		    		  $.when($(sportName + "_tab").tab("show")).done(function()
		    		  {
		    			  window.setTimeout(search, 1000);
		    		  });
	    			  
	    		  });
	    		  

	    	  },
	    	  error: function(xhr)
	    	  {
	    		  alert(xhr);
	    	  }
	    });
	});
}

//showing the events
function showEvents(data, pane, sportName)
{
	
	//let data is in the form data = [{id, name, location}{}{}];
	var openTable = '<table class="table table-dark leagues" id="' + sportName  + '">';
	var thead = '<thead></thead>';
	var tbody = '<tbody></tbody>';
	var closeTable = '</table>'
		
	//creating a table
	var table = openTable + thead + tbody + closeTable;
	
	//parsing the data
	data = JSON.parse(data);

	console.log(data);
	
	//removing the previous table from the pane
	$.when(pane.children().remove()).done(function()
	{
		//appending new table
		$.when(pane.append(table)).done(function()
		{
			var openRow, closeRow, td1, td2, td3, td4, row;
			closeRow = '</tr>';

			var row2, td21, div1, div2, input1, input2, input3, close;
			var table = $("#" + sportName + " tbody");
			
			//loop the JSON object
			$.when($.each(data, function(index, event)
			{
			
				openRow = '<tr class="' + event.hash + '">';
				td1 = '<th scope="row">'+ (index + 1) + '</th>';
				td2 = '<td scope="row">' + 
			      		'<button onclick="handlerForShowingSubEvents(' + event.id + ',\'' + event.name + '\',\'' + sportName + '\')" class="btn btn-primary btn-sm" type="button" data-toggle="collapse" data-target="' +  "#" + event.hash + '" aria-expanded="false" aria-controls="' + event.id + '">' + event.name + '</button>' +
			      	  '</td>';
				td3 = '<td scope="row">' + event.location + '</td>';

				//creating first row
				row = openRow + td1 + td2 + td3 + closeRow;

				td21 = '<td scope="row" colspan="5">';
				
				div1 = '<div class="collapse" id="' + event.hash + '">';
				div2 = '<div class="card card-body bg-dark">';
				div3 = '<div id="' + "_" + event.id +  '"></div>'
				
				input1 = '<input type="hidden" value="' + (index + 1 )+ '">';
				input2 = '<input type="hidden" value="' + event.name + '">';
				input3 = '<input type="hidden" value="' + event.location +  '">';
				close = '</div></div></td></tr>';
				
				//creating second row
				row2 = openRow + td21 + div1 + div2 + div3 + input1 + input2 + input3 + close;
			
				//appending in the table
				table.append(row).append(row2);
				
			})).done(function(){});
		});
	});
}

//search the events
function search()
{

	$("#search").on("keyup", function()
	{
	    var value = $(this).val().toLowerCase();
	    var _class = null;
	    
	    $(".leagues tr").filter(function()
	    {
	    	var text = $(this).text();
	    
	    	var display = $(this).text().toLowerCase().indexOf(value) > -1;
	    	var display2 = $(this).val();
	    	
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
function handlerForShowingSubEvents(id, eventName, sportName)
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
			  url: "/app1/AppRequestHandler",
			  type: "get", 
			  data:
			  { 
				className: 'subEvents',
				sportName: sportName,
			    id: id
			  },
			  success: function(response)
			  {
				    //on success response, first empty the spinner 
				    //then load the subEvents .jsp file 
				    //and then set Handler on the check boxes
					$.when(collapse_w.empty()).done(function()
					{						
						  showSubEvents(response, collapse_w, eventName, sportName);
					});				  
			  },
			  error: function(xhr)
			  {
					$.when(collapse_w.empty()).done(function()
					{
						  $.when(collapse_w.load("<h3>Unknown Error</h3>").hide().fadeIn(1000)).done(function()
						  {
						  });					
					});				  

			  }
		});			
	}
}

//this function is used to show the matches of leagues
function showSubEvents(response, collapse_w, eventName, sportName)
{
	let openTable = '<table class="table table-dark bg-dark table-hover">',
		thead = '<thead></thead>',
		tbody = '<tbody></tbody>',
		closeTable = '</table>'
		table = openTable + thead + tbody + closeTable;
	
	let header = '<tr><th scope="col">#</th><th scope="col">Home Team</th><th scope="col">Away Team</th><th scope="col">Start Time</th><th scope="col">1</th><th scope="col">X</th><th scope="col">2</th><th scope="col">O/U</th><th scope="col">A/H</th></tr>';
	
	let data = JSON.parse(response);
	
	
	//[{isChecked, homeTeam, awayTeam, leagueName, matchName, homeTeamOdds, awayTeamOdds, drawOdds,
	//homeTeamOutcomeId, awayTeamOutcomeId, drawOutcomeId, bettingType}, {}, {}]
	
	let openRow = "<tr>", closeRow = "</tr>",
		checkBox, td1, td2, td3, td4, td5, td6, td7, td8, td9;
	
	if(data.length > 0)
	{
		$.when($.when(collapse_w.append(table)).done(function()
		{
			table = collapse_w.find("table");
			
			$.when($.each(data, function(index, match)
			{
				checkbox = '<th scope="row">' +
							  '<div class="form-check form-check-inline">' + 
							     '<input class="form-check-input check303" ' + match.isChecked + ' type="checkbox" id="' + ("_" + match.id) + '">' + 
							     "<input type='hidden' name='homeTeam' value='" + match.homeTeam +  "' >" + 
							     "<input type='hidden' name='awayTeam' value='" + match.awayTeam +  "' >" +
							     "<input type='hidden' name='leagueName' value='" + eventName + "' >" + 
							     "<input type='hidden' name='matchName' value='" + match.homeTeam + ' vs ' + match.awayTeam + "' >" + 
							     "<input type='hidden' name='homeTeamOdds' value='" + match.homeTeamOdds + "' >" + 
							     "<input type='hidden' name='awayTeamOdds' value='" + match.awayTeamOdds + "' >" + 
							     "<input type='hidden' name='drawOdds' value='" + match.drawOdds + "' >" + 
							     "<input type='hidden' name='homeTeamOutcomeId' value='" + match.homeTeamOutcomeId + "' >" + 
							     "<input type='hidden' name='awayTeamOutcomeId' value='" + match.awayTeamOutcomeId + "' >" + 
							     "<input type='hidden' name='drawOutcomeId' value='" + match.drawOutcomeId + "' >" + 
							     "<input type='hidden' name='matchId' value='" + match.id + "' >" + 
							     "<input type='hidden' name='sportName' value='" + match.sportName + "' >" + 
							     "<input type='hidden' name='bettingType' value='" + 'HomeDrawAway' + "' >" + 
						       '</div>' +
							'</th>';
				
				td1 = '<td scope="row">' + match.homeTeam + '</td>';
				td2 = '<td scope="row">' + match.awayTeam + '</td>';
				td3 = '<td scope="row">' + match.startTime + '</td>';
				td4 = '<td scope="row">' + match.homeTeamOdds + '</td>';
				td5 = '<td scope="row">' + match.drawOdds + '</td>';
				td6 = '<td scope="row">' + match.awayTeamOdds + '</td>';

				td7 = '<td scope="row"><button type="button" class="btn btn-secondary btn-sm" value="' + match.id + '"onclick="handleOdds(' + match.id + ', \'' + match.homeTeam + '\', \'' + match.awayTeam +  '\' , \'OU\', \'' + eventName + '\', \'' + sportName +'\')">view</button></td>';
				td8 = '<td scope="row"><button type="button" class="btn btn-secondary btn-sm" value="' + match.id + '"onclick="handleOdds(' + match.id + ', \'' + match.homeTeam + '\', \'' + match.awayTeam +  '\' , \'AH\', \'' + eventName + '\', \'' + sportName +'\')">view</button></td>';
							 
				row = openRow + checkbox + td1 + td2 + td3 + td4 + td5 + td6 + td7 + td8 + td9 + closeRow;
				
				table.append(row).hide().fadeIn(index * 700);
			})).done(function()
			{
				table.find("thead").append(header).hide().fadeIn('slow');
				setHandlerOnCheckBox();
			});			
						
		})).done(function()
		{
			
		});
		
	}
	else
	{
		collapse_w.append("<h6>Oooops! " + eventName + " league contain no match in 3 day interval</h6>")
	}
	
}




function notificationButtonHandler()
{
	$.ajax(
	{
		url: "/app1/AppRequestHandler",
		type: "get",
		data: {
			className: "notificationPermission"
		},
		success: function(response)
		{
			response = JSON.parse(response);

			
			$.when($("#s_w").fadeOut('slow').empty()).done(function()
			{
				$.when($("#s_w").load("/app1/src/views/notifications/notifications.jsp").fadeIn('slow')).done(function()
				{
					if(response[0].action)
						start();									
				});
			});
			
		},
		error: function(xhr)
		{
			
		}
	});
	
}


function mainPage()
{
	$.get("/app1/AppRequestHandler", function(data)
	{
		var object = JSON.parse(data);
		var url  = object.url;
		$("#app").load(url, function(e)
		{
			setToggling();
		});

	});
}



//how to implement this: will discuss later
function start()
{
	
	var source = null;
	
	source = new EventSource("/app1/AppRequestHandler");

	source.onopen = function(){console.log("Connected....");};

	
	source.onmessage = function(event)
	{


		var data = event.data;
		var match = JSON.parse(data);
		
		console.log(match);
		
		var row = "<tr>" +"<td scope='row'>" + match['lastUpdateTime'] + "</td>"
							+"<td scope='row'>" + match['leagueName'] + "</td>"
							+"<td scope='row'>" + match['matchName'] + "</td>"
							+"<td scope='row'>" + match['participant'] + "</td>"
							+"<td scope='row'>" + match['oldOdds'] + " -> " + match['odds']+ "</td>"
							+"<td scope='row'>" + match['oldThreshold'] + " -> " + match['threshold'] + "</td>"
							+"<td scope='row'>" + match['status'] + "</td>"

				+ "</tr>";
		
		
		
		$.playSound("http://www.noiseaddicts.com/samples_1w72b820/3724.mp3");		
		
		var table = $("#notification_table");
		table.prepend(row).hide().fadeIn('slow');
		
	};

	source.onerror = function(response){console.log(response);};
}


function trackedEventDeleteButtonHandler()
{
	$(".del_tracked_event").click(function()
	{
		var $this = $(this);
		var id = $this.val();
		
		
		var targetRow = $this.closest('tr');


		$.ajax(
	    {
	    	  url: "/app1/AppRequestHandler",
	    	  type: "get", 
	    	  data:
	    	  { 
	    		className: 'deleteRegisteredEvents',
	    	    id: id
	    	  },
	    	  success: function(response)
	    	  {
	    		  let data = JSON.parse(response);
	    		  if(data[0].action == 'deleted')
	    	      {
		    		  targetRow.fadeOut("slow");	    			  
	    	      }
	    	  },
	    	  error: function(xhr)
	    	  {
	    		  var response = $.parseHTML(xhr.responseText);
	    		  alert($(response).filter( 'h1' ).text());
	    	  }
	    });
	});
}



//this function is responsible to show trackedEvent page
function trackedEventsDisplay()
{
	$.ajax(
    {
		  url: "/app1/AppRequestHandler",
		  type: "get", 
		  data:
		  {
			className: 'trackedEventsDisplay',
		    checked: null
		  },
		  success: function(response)
		  {
			 var array = JSON.parse(response);

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

function handleOdds(eventId, homeTeam, awayTeam, bettingType, eventName, sportName)
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
			url: "/app1/AppRequestHandler",
			type: "get",
			data: {
				className: 'assianHandicap',
				sportName: sportName,
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
			url: "/app1/AppRequestHandler",
			type: "get",
			data: {
				className: 'overUnder',
				sportName: sportName,
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
			header.append("<th scope='col' class='l432' data-defaultsign='_19' data-defaultsort='desc'>Under</th>");
			header.append("<th scope='col'>#</th>");
			header.append("<th scope='col' class='l432' data-defaultsign='_19' >Over</th>");

			$.bootstrapSortable({ applyLast: true });
		});
	}
	else if(teams[2] == 'Assian Handicap')
	{
		$.when(appendInAssianHandicapTable(header, table, object, teams)).done(function()
		{
			header.append("<th scope='col'>#</th>");
			header.append("<th scope='col' class='l432' data-defaultsign='_19' data-defaultsort='desc'>1 (" + teams[0] + ")</th>");
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



/*Handler on check boxes---------------------for subEvents---------------------------------------------*/
/*-------------------------------------------tracked match too----------------------------------------------------*/

function setHandlerOnCheckBox()
{

	$(".check303").change(function(event)
	{
		$this = $(this);	
		$(".check303").prop("disabled", true);
		var checked = this.checked;
		var id = $this.attr("value");
		var form = $this.parent();
		var inputs = form.children();
		
		var map = {};
				
		$.when(inputs.each(function(){map[$(this).attr("name")] = $(this).val()})).done(function()
		{						

			var data =JSON.stringify(map);
			
			
			$.ajax(
    	    {
    	    	  url: "/app1/AppRequestHandler",
    	    	  type: "get", 
    	    	  data:
    	    	  { 
    	    		className: 'hodRegistrar',
    	    	    data: data,
    	    	    checked: checked
    	    	  },
    	    	  success: function(response)
    	    	  {
    	    		  var notification;
    	    		  response = JSON.parse(response);
    	    			$(".check303").prop("disabled", false);

    	    		  if(response[0].action = 'y')
    	    		  {
        	    		  if(checked)
        	    			  notification = map.leagueName + " odds added";
        	    		  else
        	    			  notification = map.leagueName + " odds removed";
        	    			  
        	    		  flashMessage(notification);    	    			  
    	    		  }
    	    		  
    	    	  },
    	    	  error: function(xhr)
    	    	  {
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
    	    	  url: "/app1/AppRequestHandler",
    	    	  type: "get", 
    	    	  data:
    	    	  { 
    	    		className : 'AHOURegistrar',
    	    	    data: data,
    	    	    checked: checked,
    	    	    isAH : isAH
    	    	  },
    	    	  success: function(response)
    	    	  {
    	    		  var notification;
    	    		  response = JSON.parse(response);
    	    		  
    	    		  if(response[0].action == 'y')
    	    		  {
        	    		  if(checked)
        	    			  notification = map.leagueName + " odds added";
        	    		  else
        	    			  notification = map.leagueName + " odds removed";
        	    			  
        	    		  flashMessage(notification);
    	    		  }
    	    		  
    	    	  },
    	    	  error: function(xhr)
    	    	  {
    	    		  var response = $.parseHTML(xhr.responseText);
    	    		  alert($(response).filter( 'h1' ).text());
    	    	  }
    	    });				
		});				
	});
	
}

/*-----------------------------------------------Flash Message Notification---------------------------*/
function flashMessage(text)
{
	  $("#flash_message").text(text); 
		 
	  
	  $("#flash_message").show('slow', function()
	  {
		 setTimeout(function()
		 {
			$("#flash_message").hide('slow'); 
		 }, 1000);
	  });	
	
}


/*------------------------------------------------------------------------------------*/
function deleteAllTrackedEvents()
{
	$.ajax(
	{
		url  : "/app1/AppRequestHandler",
		type : "get",
		data : {
			className: "deleteAllTrackedEvent"
		},
		success: function(response)
		{
			response = JSON.parse(response);
			
			if(response[0].action == true)
			{

				$.when($("#trackedEvents tr").fadeOut('slow')).done(function()
				{
					flashMessage("All tracked Events Removed");					
				});
			}
		},
		error : function(xhr)
		{
			alert(xhr);
		}
		
	});
}

/*-----------------------------------Removed Outcomes--------------------------------*/
function getRemovedItem()
{
	
	
	$.ajax(
	{
		url: "/app1/AppRequestHandler",
		type: "get",
		data: {
			className: "RemovedOutcomes"
		},
		success: function(response)
		{
			response = JSON.parse(response);
			console.log(response);
			$.when($.each(response, function(index, event)
			{
				
				var row = "<tr>" + 
						
							"<td scope='row'>" + event.lastUpdateTime + "</td>" + 
							"<td scope='row'>" + event.leagueName + "</td>" + 
							"<td scope='row'>" + event.matchName + "</td>" + 
							"<td scope='row'>" + event.participant + "</td>" + 
							"<td scope='row'>" + event.threshold + "</td>" + 
							"<td scope='row'>" + event.odds + "</td>" + 
							"<td scope='row'>" + event.bettingType + "</td>" + 
							"<td scope='row'>" + event.sport + "</td>" + 
							
						 "</tr>";
							
				
			})).done(function()
			{

			});			
		},
		error: function(xhr)
		{
			console.log(xhr);
		}
		
	});
}




