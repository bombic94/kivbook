<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <title>Kivbook profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:url value="../resources/kivbook.css" var="css1"/>
    <c:url value="../resources/kivbook-navbar.css" var="css2"/>
    <link rel="stylesheet" type="text/css" href="${css1}">
    <link rel="stylesheet" type="text/css" href="${css2}">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
            <li><a href="../timeline"><span class="glyphicon glyphicon-home"></span> Home <span class="badge">${newStatuses}</span></a></li>
            <li><a href="../profile/${loggedUser.id}"><span class="glyphicon glyphicon-user"></span> My Account</a></li>
            <li><a href="../messages"><span class="glyphicon glyphicon-envelope"></span> Messages <span class="badge">${newMessages}</span></a></li>
            <li><a href="../users"><span class="glyphicon glyphicon-list-alt"></span> Find friends <span class="badge">${newFriendships}</span></a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="../settings"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
            <li><a href="../about"><span class="glyphicon glyphicon-info-sign"></span> About</a></li>
            <li><a href="../homepage"><span class="glyphicon glyphicon-off"></span> Log out</a></li>
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
              <img src="<c:url value="../resources/img/${user.photo}"/>" class="img-circle img-250" alt="Avatar">
            </div>
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
                <p><fmt:formatDate value="${user.created_at}" pattern="yyyy/MM/dd/ HH:mm"/></p>
              </div>
            </div>
            <c:if test = "${loggedUser.id eq user.id}">
            	<p class="text-center"><a href="../settings">Edit profile</a></p>
            </c:if>
          </div>
          <div class="col-sm-2">
          </div>
        </div>
      </div>
    </div>
  </body>
</html>