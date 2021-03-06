<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="cs-cz">
  <head>
    <title>Kivbook profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <c:url value="resources/kivbook.css" var="css1"/>
    <c:url value="resources/kivbook-navbar.css" var="css2"/>
    <link rel="stylesheet" type="text/css" href="${css1}">
    <link rel="stylesheet" type="text/css" href="${css2}">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  <body>
  	<script type="text/javascript">
    	function ajaxNotif() {
	        $.ajax({
	            url : 'ajaxNotif',
	            dataType : 'json',
	            contentType: "application/json;charset=utf-8",
	            success : function(data) {
	            	$('#newMSG').html(data[0]);
	            	$('#newFRD').html(data[1]);
	            }
	        });
	    }
	</script>
	<script type="text/javascript">
		$( document ).ready(function() {
	    	ajaxNotif();
	    	var intervalId = 0;
	    	intervalId = setInterval(ajaxNotif, 1000);
		});   
	</script>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>                        
          </button>
          <span class="navbar-brand">Kivbook</span>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav">
            <li><a href="timeline"><span class="glyphicon glyphicon-home"></span> Home</a></li>
            <li><a href="profile/${loggedUser.id}"><span class="glyphicon glyphicon-user"></span> My Account</a></li>
            <li><a href="messages"><span class="glyphicon glyphicon-envelope"></span> Messages <span id="newMSG" class="badge"></span></a></li>
            <li><a href="users"><span class="glyphicon glyphicon-list-alt"></span> Find friends <span id="newFRD" class="badge"></span></a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="settings"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
            <li><a href="about"><span class="glyphicon glyphicon-info-sign"></span> About</a></li>
            <li><a href="homepage/logout"><span class="glyphicon glyphicon-off"></span> Log out</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="content">
      <div class="container">
        <div class="row">
          <div class="col-sm-2">
          </div>
          <div class="col-sm-8 well">
            <div class="text-center">
              <h3>${user.firstname} ${user.lastname} (${user.username})</h3>
              <img src="<c:url value="/images/${user.photo}"/>" id="profilePicture" class="img-circle" height="250" width="250" alt="<c:url value="/images/${user.photo}"/>">
            </div>
            <span style="display:inline-block; width: 10em;height: 2em;"></span>
            <div class="row">
              <h4 class="text-center">About ${user.firstname}</h4>
              <div class="col-sm-4 col-xs-4">
                <p>Email</p>
                <p>Age</p>
                <p>Gender</p>
                <p>Number of friends</p>
                <p>On kivbook since</p>
              </div>
              <div class="col-sm-8 col-xs-8">
                <p>${user.email}</p>
                <p>${userAge}</p>
                <p>${userGender}</p>
                <p>${userFriends}</p>
                <p><fmt:formatDate value="${user.created_at}" pattern="yyyy/MM/dd HH:mm"/></p>
              </div>
            </div>
            <c:if test = "${loggedUser.id eq user.id}">
            	<p class="text-center"><a href="settings">Edit profile</a></p>
            </c:if>
          </div>
          <div class="col-sm-2">
          </div>
        </div>
      </div>
    </div>
  </body>
</html>