<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
              <c:if test="${empty friendships}">
				    <p>No friends. Add someone as friend</p>
				</c:if>
              <c:forEach items="${friendships}" var="friendship">
         		<c:if test="${friendship.user1.id == loggedUser.id}">
         			<c:set var = "friend" scope = "session" value = "${friendship.user2}"/>
         		</c:if> 
         		<c:if test="${friendship.user2.id == loggedUser.id}">
         			<c:set var = "friend" scope = "session" value = "${friendship.user1}"/>
         		</c:if>    
                <li class="list-group-item">
                  <div class="media">
                    <div class="media-left">
                      <a href="profile/${friend.id}"><img src="<c:url value="/images/${friend.photo}"/>" class="media-object img-60" alt="<c:url value="/images/${friend.photo}"/>"></a>
                    </div>
                    <div class="media-body">
                      <a href="profile/${friend.id}">
                        <h4 class="media-heading black">${friend.firstname}</h4>
                      </a>
                      <p class="media-heading">Friends since <fmt:formatDate value="${friendship.created_at}" pattern="yyyy/MM/dd HH:mm"/></p>
                    </div>
                    <div class="media-right">
                    	<form method="POST" action="users/deleteFriend/"${friendship}">
                      		<button type="submit" class="btn btn-link"><span class="glyphicon glyphicon-minus-sign friendicon-minus"></span></button>
                      	</form>
                    </div>
                  </div>
                </li>
                </c:forEach>
              </ul>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="well">
              <h4 class="text-center">Pending requests</h4>
              <ul class="list-group">     
              	<c:if test="${empty pendingFriendships}">
				    <p>No pending requests. Add someone as friend</p>
				</c:if>         
                <c:forEach items="${pendingFriendships}" var="friendship">
         		<c:if test="${friendship.user1.id == loggedUser.id}">
         			<c:set var = "friend" scope = "session" value = "${friendship.user2}"/>
         		</c:if> 
         		<c:if test="${friendship.user2.id == loggedUser.id}">
         			<c:set var = "friend" scope = "session" value = "${friendship.user1}"/>
         		</c:if>    
                <li class="list-group-item">
                  <div class="media">
                    <div class="media-left">
                      <a href="profile/${friend.id}"><img src="<c:url value="/images/${friend.photo}"/>" class="media-object img-60" alt="<c:url value="/images/${friend.photo}"/>"></a>
                    </div>
                    <div class="media-body">
                      <a href="profile/${friend.id}">
                        <h4 class="media-heading black">${friend.firstname}</h4>
                      </a>
                      <p class="media-heading">Requested friendship on: <fmt:formatDate value="${friendship.created_at}" pattern="yyyy/MM/dd HH:mm"/></p>
                    </div>
                    <div class="media-right">
                      <form method="POST" action="users/acceptFriend/"${friendship}">
                      	<button type="submit" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign friendicon-plus"></span></button>
                      </form>
                    </div>
                  </div>
                </li>
                </c:forEach>           
              </ul>
            </div>
            <div class="well">
              <h4 class="text-center">Find new friends</h4>
              <ul class="list-group">
              	<c:if test="${empty usersToFriend}">
				    <p>No other users on Kivbook to connect. Tell your friends about Kivbook</p>
				</c:if>              
                <c:forEach items="${usersToFriend}" var="friend">  
                <li class="list-group-item">
                  <div class="media">
                    <div class="media-left">
                      <a href="profile/${friend.id}"><img src="<c:url value="/images/${friend.photo}"/>" class="media-object img-60" alt="<c:url value="/images/${friend.photo}"/>"></a>
                    </div>
                    <div class="media-body">
                      <a href="profile/${friend.id}">
                        <h4 class="media-heading black">${friend.firstname}</h4>
                      </a>
                      <p class="media-heading">On Kivbook since: <fmt:formatDate value="${friend.created_at}" pattern="yyyy/MM/dd HH:mm"/></p>
                    </div>
                    <div class="media-right">
                      <form method="POST" action="users/addFriend/"${friend}">
                      	<button type="submit" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign friendicon-plus"></span></button>
                      </form>
                    </div>
                  </div>
                </li>
                </c:forEach>           
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>