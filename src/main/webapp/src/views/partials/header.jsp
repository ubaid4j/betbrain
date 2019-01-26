<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>BetBrain Scrapper</title>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link href="/app1/src/assests/css/bootstrap-sortable.css" rel="stylesheet">
		<link href="/app1/src/assests/css/app.css" rel="stylesheet">
		
	</head>

	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">
    			<img src="/app1/src/resources/imgs/scraper.png" width="30" height="30" alt="">
			</a>
	  		<a class="navbar-brand" href="/app1/src/index.jsp">BetBrain Scrapper</a>
	  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    		<span class="navbar-toggler-icon"></span>
	  		</button>
	  		
	  		<div class="collapse navbar-collapse" id="navbarSupportedContent">
    			<ul class="navbar-nav mr-auto">
				     <li class="nav-item">
				       <button class="nav-link btn btn-secondary btn-sm" onClick="homePage()">Home</button>
	  			     </li>
				     <li class="nav-item">
				       <button class="nav-link btn btn-secondary btn-sm" onClick="removedOutcomePage()">Removed Outcomes</button>						
			      	 </li>
	  			</ul>
	  		</div>
		</nav>
