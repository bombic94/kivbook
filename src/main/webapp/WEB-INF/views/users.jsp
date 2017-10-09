<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <title>Kivbook users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:url value="resources/kivbook.css" var="css1"/>
    <c:url value="resources/kivbook-navbar.css" var="css2"/>
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
        <div class="row">
          <div class="col-sm-6">
            <div class="well">
              <h4 class="text-center">Your friends</h4>
              <ul class="list-group">
                <li class="list-group-item">
                  <div class="media">
                    <div class="media-left">
                      <a href="./profile.html"><img src="img/05-chef-a.png" class="media-object img-60" alt="photo"></a>
                    </div>
                    <div class="media-body">
                      <a href="./profile.html">
                        <h4 class="media-heading black">Chef</h4>
                      </a>
                      <p class="media-heading">Friends since June 13, 2015</p>
                    </div>
                    <div class="media-right">
                      <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-minus-sign friendicon-minus"></span></button>
                    </div>
                  </div>
                </li>
                <li class="list-group-item">
                  <div class="media">
                    <div class="media-left">
                      <a href="./profile.html"><img src="img/06-mackey-a.png" class="media-object img-60" alt="photo"></a>
                    </div>
                    <div class="media-body">
                      <a href="./profile.html">
                        <h4 class="media-heading black">Mackey</h4>
                      </a>
                      <p class="media-heading">Friends since June 13, 2015</p>
                    </div>
                    <div class="media-right">
                      <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-minus-sign friendicon-minus"></span></button>
                    </div>
                  </div>
                </li>
                <li class="list-group-item">
                  <div class="media">
                    <div class="media-left">
                      <a href="./profile.html"><img src="img/03-eric-cartman-a.png" class="media-object img-60" alt="photo"></a>
                    </div>
                    <div class="media-body">
                      <a href="./profile.html">
                        <h4 class="media-heading black">Eric Cartman</h4>
                      </a>
                      <p class="media-heading">Friends since July 27, 2016</p>
                    </div>
                    <div class="media-right">
                      <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-minus-sign friendicon-minus"></span></button>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="well">
              <h4 class="text-center">Find new friends</h4>
              <ul class="list-group">
                <li class="list-group-item">
                  <div class="media">
                    <div class="media-left">
                      <a href="./profile.html"><img src="img/02-kyle-broflovski-a.png" class="media-object img-60" alt="photo"></a>
                    </div>
                    <div class="media-body">
                      <a href="./profile.html">
                        <h4 class="media-heading black">Kyle Broflovski</h4>
                      </a>
                      <p class="media-heading">On Kivbook since January 1, 2017</p>
                    </div>
                    <div class="media-right">
                      <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign friendicon-plus"></span></button>
                    </div>
                  </div>
                </li>
                <li class="list-group-item">
                  <div class="media">
                    <div class="media-left">
                      <a href="./profile.html"><img src="img/01-stan-marsh-a.png" class="media-object img-60" alt="photo"></a>
                    </div>
                    <div class="media-body">
                      <a href="./profile.html">
                        <h4 class="media-heading black">Stan Marsh</h4>
                      </a>
                      <p class="media-heading">On Kivbook since January 1, 2017</p>
                    </div>
                    <div class="media-right">
                      <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign friendicon-plus"></span></button>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>