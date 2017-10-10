<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <title>Kivbook settings</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:url value="resources/kivbook.css" var="css1"/>
    <c:url value="resources/kivbook-navbar.css" var="css2"/>
    <link rel="stylesheet" type="text/css" href="${css1}">
    <link rel="stylesheet" type="text/css" href="${css2}">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- validator for inputs-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
    <!-- datepicker-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" />
  </head>
  <body>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>                        
          </button>
          <a class="navbar-brand" href="#">Kivbook</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav">
            <li><a href="timeline"><span class="glyphicon glyphicon-home"></span> Home <span class="badge">${newStatuses}</span></a></li>
            <li><a href="profile/${loggedUser.id}"><span class="glyphicon glyphicon-user"></span> My Account</a></li>
            <li><a href="messages"><span class="glyphicon glyphicon-envelope"></span> Messages <span class="badge">${newMessages}</span></a></li>
            <li><a href="users"><span class="glyphicon glyphicon-list-alt"></span> Find friends <span class="badge">${newFriendships}</span></a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="settings"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
            <li><a href="about"><span class="glyphicon glyphicon-info-sign"></span> About</a></li>
            <li><a href="homepage"><span class="glyphicon glyphicon-off"></span> Log out</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="content">
      <div class="container-fluid">
        <div class="col-sm-6">
          <form:form class="well text-center" data-toggle="validator" modelAttribute="user" method="post" action="settings/changeSettings">
          	<div hidden>
            	<form:input path="id" value="${loggedUser.id}"></form:input>
            </div>
            <div class="row">
              <img src="img/04-kenny-mccormick-a.png" class="img-rounded" height="250" width="250" alt="Avatar">
            </div>
            <div class="row">
              <label class="btn btn-primary">
              Change profile picture <input type="file" accept="image/*" hidden>
              </label>
              <button type="submit" class="btn btn-primary">Save new profile picture</button>
            </div>
          </form:form>
          <form:form class="well" data-toggle="validator" modelAttribute="user" method="post" action="settings/changeSettings">
            <h3>Edit information</h3>
            <div hidden>
            	<form:input path="id" value="${loggedUser.id}"></form:input>
            </div>
            <div class="form-group">
              <label for="name">First name</label>
              <form:input path="firstname" type="text" class="form-control" id="name" value="${loggedUser.firstname}"
                placeholder="Please enter your first name" required="required" data-error="First name is required"/>
              <div class="help-block with-errors"></div>
            </div>
            <div class="form-group">
              <label for="surname">Last name</label>
              <form:input path="lastname" type="text" class="form-control" id="surname" value="${loggedUser.lastname}"
                placeholder="Please enter your last name" required="required" data-error="Last name is required"/>
              <div class="help-block with-errors"></div>
            </div>
            <div class="form-group">
              <label for="datetimepicker">Date of birth</label>
              <input type='text' class="form-control" id='datetimepicker' placeholder="Please enter your date of birth"/>
              <script type="text/javascript">
                $(function () {
                    $('#datetimepicker').datetimepicker({
                        format: "YYYY-MM-DD",
                        date: new Date(${datepickerDefault})
                    });
                });
              </script>
              <div hidden>
                <form:input path="dateofbirth" type="date" id="dateofbirth" value="${loggedUser.dateofbirth}"/>
              </div>
              <script type="text/javascript">
                 $(function () {
                   $("#datetimepicker").on("dp.change", function (e) {
   					document.getElementById("dateofbirth").value = document.getElementById('datetimepicker').value;
				 });
                 });
              </script> 
            </div>
            <div class="form-group">
              <label>Gender</label>
              <br>
              <label class="radio-inline"><input type="radio" required="required" id="gender1" name="gender" value="m">Male</label>
              <label class="radio-inline"><input type="radio" required="required" id="gender2" name="gender" value="f">Female</label>
              <script type="text/javascript">
              		$(function () {
              			if (${loggedUser.gender == 'm'} ) {
              				$("#gender1").prop("checked", true);
              			} else {
              				$("#gender2").prop("checked", true);
              			}
                      });
              </script>
            </div>
            <button type="submit" class="btn btn-primary">Save changes</button>
          </form:form>
        </div>
        
        <div class="col-sm-6">
          <form:form class="well" data-toggle="validator" modelAttribute="user" method="post" action="settings/changePassword">
            <h3>Change password</h3>
            <div hidden>
            	<form:input path="id" value="${loggedUser.id}"></form:input>
            </div>
            <div class="form-group">
              <label for="password">New Password</label>
              <form:input path="password" type="password" class="form-control" id="password" value=""
                placeholder="Please enter your new password" required="required" data-minlength="6" data-error="Password is required (Minimum of 6 characters)"/>
              <div class="help-block with-errors">Minimum of 6 characters</div>
            </div>
            <div class="form-group">
              <label for="password2">Confirm password</label>
              <input type="password" class="form-control" id="password2"
                placeholder="Please confirm your new password" required="required" data-match="#password" data-match-error="Password does not match">
              <div class="help-block with-errors"></div>
            </div>
            <button type="submit" class="btn btn-primary">Change password</button>
          </form:form>
          
          <form:form class="well" data-toggle="validator" modelAttribute="user" method="post" action="settings/delete">
            <h3>Delete profile</h3>
            <div hidden>
            	<form:input path="id" value="${loggedUser.id}"></form:input>
            </div>
            <div class="form-group">
              <label for="password">Write 'DELETE' to confirm deleting of profile</label>
              <input type="text" id="delete-match" value="DELETE" hidden>
              <input type="text" class="form-control" id="delete"
                placeholder="DELETE" required="required" data-match="#delete-match" data-error="Write 'DELETE' to confirm deleting of profile">
              <div class="help-block with-errors"></div>
            </div>
            <button type="submit" class="btn btn-danger">DELETE</button>
          </form:form>
        </div>
      </div>
    </div>
  </body>
</html>