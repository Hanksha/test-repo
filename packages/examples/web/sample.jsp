<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="The examples package ships with Martini by default and contains several services and API files that demonstrate many of Martini's features.">

    <title>Retrieve HTML Data from a Gloop Service - Examples Package Homepage</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../../examples/static/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="../../examples/static/css/cover.css" >
    <link rel="stylesheet" href="../../examples/static/css/gradient.css" >
  </head>

  <body>
   <div class="gradient-bg"></div>
   <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
      <header class="masthead">
        <div class="inner">
          <h3 class="masthead-brand"></h3>
          <nav class="nav nav-masthead justify-content-center">
          </nav>
        </div>
      </header>
      <div class="row mb-auto">
      	<div class="col-md-3"></div>
      	<div class="col-md-6">
      	<h4 class="text-center">This JSP page came from Gloop. You can find out more about this feature <b><a target="_blank" href="https://docs.torocloud.com/martini/latest/quick-start/resources/examples-package/rendering-jsp/">here</a></b>.</h4>
      	</div>
      </div>

      <main role="main" class="inner cover text-center">
      	<jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate var="currentDate" value="${now}" type="both" dateStyle="short" timeStyle="short" />
        <div id="displayResponse" style="display:none;margin-bottom:30px">
            <h1 style="text-shadow: #868686 2px 2px;">Welcome, ${firstName} ${lastName}!</h1><br/>
            <c:out value="${currentDate}" />
        </div>
        <form id="helloForm">
            <div class="form-row">
            	<div class="col-md-3"></div>
                <div class="form-group col-md-6">
                <!--
                <div class="alert alert-info text-left" role="alert">
		          <b>NOTE: </b><br/><br/>
		          This JSP page came from Gloop.<br/><br/>
		          If you click the "Submit" button, the form-data will be sent to the service, then the page will reload with
		            the new data.
		        </div>
		         -->
                <div class="form-group col-md-12">
                    <div class="form-group">
                        <label for="exampleInputEmail1" class="float-left">First Name</label>
                        <input type="text" class="form-control form-control-lg" name="firstname" id="exampleInputEmail1" value="${firstName}" aria-describedby="emailHelp" placeholder="Enter First Name">
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <div class="form-group">
                        <label for="exampleInputEmail1" class="float-left">Last Name</label>
                        <input type="text" class="form-control form-control-lg" name="lastname" id="exampleInputEmail1" value="${lastName}" aria-describedby="emailHelp" placeholder="Enter Last Name">
                    </div>
                </div>
				<div class="col">
				<button type="button" class="col btn-lg btn btn-pink-moon" onclick="sayHello()">SUBMIT</button>
				</div>
                </div>
            </div>
        </form>
      </main>
      <footer class="mastfoot mt-auto">
      </footer>
    </div>

    <script>
        var port = location.port || ( location.protocol === "https:" ? "443" : "80" );
        var url = [ window.location.protocol, "//", window.location.hostname, ":", port, "/api/" ].join( "" );
        // Get url and port of Martini instance
        function sayHello() {
            // Create AJAX Request
            var xhttp = new XMLHttpRequest();
            xhttp.open( "POST", "" ); // POSTS to self
            xhttp.setRequestHeader( "Content-type", "application/json" )
            xhttp.setRequestHeader( "Accept", "application/json" )

            xhttp.onreadystatechange = function () {
                document.open();
                document.write( xhttp.responseText );   // Reload the page with response page
                var content = document.getElementById( "displayResponse" )
                content.style.display = "block";    // Display Server Time
            };

            // Get Form data
            var form = document.forms[ "helloForm" ]
            var data = JSON.stringify(
                {
                    "firstname": form.elements[ "firstname" ].value,
                    "lastname": form.elements[ "lastname" ].value
                } );

            // Send Request
            xhttp.send( data );
        }
    </script>
  </body>
</html>
