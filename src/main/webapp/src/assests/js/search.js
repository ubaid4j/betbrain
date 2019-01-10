$(function()
{		
	search();
});

function search()
{
	$("#search").on("keyup", function()
	{
	    var value = $(this).val().toLowerCase();
	
	    $("#trackedEvents tr").filter(function()
	    {
	    
	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    
	    });
	});
}
