
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
		$.when($("#app").load("/app1/src/views/notifications/notifications.jsp")).done(function()
		{
//			start();
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

		console.log(event);

		var data = event.data;
		var match = JSON.parse(data);
		
		console.log(match);
		var row = "<tr>" +"<td>" + match['changeTime']
		+"<td>" + match['homeTeam'] + "</td>"
		+"<td>" + match['awayTeam'] + "</td>"
		+"<td>" + match['homeTeamOdds'] + "</td>"
		+"<td>" + match['drawOdds'] + "</td>"
		+"<td>" + match['awayTeamOdds'] + "</td>"
		+"</td>" + "</tr>";
		
		console.log(row);
		
		$.playSound("http://www.noiseaddicts.com/samples_1w72b820/3724.mp3");		
		
		var table = $("#notification_table");
		table.prepend(row);
	
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
	    		  });
	    		  window.setTimeout(getDataFromButtonForOdds, 2000);

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
			  $.when($("#tracked_event").load("/app1/src/views/tracked/events.jsp")).done(function()
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
function getDataFromButtonForOdds()
{
	var buttons = $('[data-toggle="collapse"]');
	
	$('[data-toggle="collapse"]').click(function(e)
	{
		var $this = $(this);
		var id = $this.attr("aria-controls");
		
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

function setHandlerOnCheckBox()
{
	console.log("setting check box handler");

	
	
	$(".check303").change(function(event)
	{
		$this = $(this);	

		var value = $this.attr("value");
		
		if(this.checked)
		{
			console.log("checked");

						
			$.ajax(
    	    {
    	    	  url: "/app1/_track",
    	    	  type: "get", 
    	    	  data:
    	    	  { 
    	    	    id: value,
    	    	    checked: true
    	    	  },
    	    	  success: function(response)
    	    	  {
		    		  $("#tracked_event").load("/app1/src/views/tracked/events.jsp")
    	    	  },
    	    	  error: function(xhr)
    	    	  {
    	    		  console.log(xhr);
    	    		  var response = $.parseHTML(xhr.responseText);
    	    		  alert($(response).filter( 'h1' ).text());
    	    	  }
    	    });
		}
		else
		{
			$.ajax(
    	    {
    	    	  url: "/app1/_track",
    	    	  type: "get", 
    	    	  data:
    	    	  { 
    	    	    id: value,
    	    	    checked: false
    	    	  },
    	    	  success: function(response)
    	    	  {
		    		  $("#tracked_event").load("/app1/src/views/tracked/events.jsp")
    	    	  },
    	    	  error: function(xhr)
    	    	  {
    	    		  console.log(xhr);
    	    		  var response = $.parseHTML(xhr.responseText);
    	    		  alert($(response).filter( 'h1' ).text());
    	    	  }
    	    });
		}
		
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

function handleOdds()
{
	
}



