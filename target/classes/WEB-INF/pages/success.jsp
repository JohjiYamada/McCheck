<%@page import="sg.com.Johji.GeneralUtils"%>
<html>
<head>
<title>[success]Temperature Check</title>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
</head>
<body style="font-size: 20px;">
	<div class="container">
		<div>Thank you for using Robo Mikako!</div>
		<div class="row align-items-center">	
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				Your Name: <%= request.getAttribute("name") %>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				1. <%= request.getAttribute("mc").equals("1") ? "Yes" : "No" %>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				2. (1) <%= request.getAttribute("yourself").equals("1") ? "Yes" : "No" %> (2) <%= request.getAttribute("family").equals("1") ? "Yes" : "No" %> (3) <%= request.getAttribute("guest").equals("1") ? "Yes" : "No" %>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				3. <%= request.getAttribute("office").equals("1") ? "Yes" : "No" %>
			</div>
			
		</div>
		<a class="btn btn-primary" href="<%=GeneralUtils.getContextRoot() %>/top?name=<%= request.getAttribute("name") %>" role="button">to Top</a>
	</div>
</body>
</html>