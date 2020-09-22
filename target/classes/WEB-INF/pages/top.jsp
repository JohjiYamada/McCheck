<%@page import="java.util.List"%>
<%@page import="sg.com.Johji.servlets.CommonServlet"%>
<%@page import="sg.com.Johji.servlets.TopServlet"%>
<%@page import="sg.com.Johji.GeneralUtils"%>
<html style="font-size: 62.5%;">
<head>
<title>attendance Check</title>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
<script type="text/javascript">
function getParam(name, url) {
	if (!url) url = window.location.href;
	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
		results = regex.exec(url);
	if (!results) return null;
	if (!results[2]) return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}

</script>
</head>
<body style="font-size:1.6rem;line-height: 2;">
	<div class="container">
		Robo Mikako attendance Recorder <%--<a href="<%=GeneralUtils.getContextRoot() %>/help" style="margin-left:10px;">help</a>--%>
		<% if(GeneralUtils.isOpen()) { %>
		<form action="<%=GeneralUtils.getContextRoot() %>/input" method="post" style="margin: 3px;">
			<div class="form-row align-items-center">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<label  for="name">Your Name</label>
					<select name="name" id="name">
						<%
						List<String> members = (List<String>) request.getAttribute("members");
						for (String name : members) {
							%>
							<option value="<%=name%>"><%=name%></option>
							<%
						}
						%>
					</select>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 my-1">
					<label class="mr-sm-2" for="mc">1. Are you MC today?</label>
					<select class="mr-sm-2" name="mc" id="mc">
						<option selected value="No">No</option>
						<option value="Reported Sick">Reported Sick</option>
						<option value='Diagnosed by Doctor as "Suspected"'>Diagnosed by Doctor as "Suspected"</option>
						<option value="Hospitalized">Hospitalized</option>
						<option value="Confirmed to be COVID-19">Confirmed to be COVID-19</option>
						<option value="Quarantined at home">Quarantined at home</option>
					</select>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<label>2. Do you have any travel plan?</label>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 my-1">
						<label class="mr-sm-2" for="yourself">(1) Yourself</label>
						<select class="mr-sm-2" name="yourself" id="yourself">
							<option selected value="No">No</option>
							<option value="Yes">Yes</option>
						</select>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 my-1">
						<label class="mr-sm-2" for="family">(2) Immediate Family</label>
						<select class="mr-sm-2" name="family" id="family">
							<option selected value="No">No</option>
							<option value="Yes">Yes</option>
						</select>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 my-1">
						<label class="mr-sm-2" for="guest">(3) Guests (coming and staying at the same household)</label>
						<select class="mr-sm-2" name="guest" id="guest">
							<option selected value="No">No</option>
							<option value="Yes">Yes</option>
						</select>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 my-1">
					<label class="mr-sm-2" for="office">3. Where do you work today?</label>
					<select class="mr-sm-2" name="office" id="office">
						<option selected value="WFH">WFH</option>
						<option value="SMBC Office">SMBC Office</option>
						<option value="Leave">Leave</option>
					</select>
				</div>
			</div>
			<div class="form-row align-items-center">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
		</form>
		<a href="https://docs.google.com/spreadsheets/d/1jR3wSMGHiGXycvG7fNRKZJfBrM4NB3t5De8grEMtNFo/edit?usp=sharing">current status</a>
		<% } else { %>
		<div>The recorder is closed. Please contact to Taniguchi san</div>
		<% } %>
		<div style="font-size:8px;">version: <%=TopServlet.version %></div>
		
	</div>
<script type="text/javascript">
if(getParam('name')){
	$('#name').val(getParam('name'));
}
</script>

</body>
</html>