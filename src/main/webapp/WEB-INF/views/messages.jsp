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
    <title>Kivbook messages</title>
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
    <!-- validator for inputs-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
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
          <a class="navbar-brand" href="#">Kivbook</a>
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
      <div class="container-fluid">
        <div class="row">
          <div class="col-sm-4">
            <div class="well">
              <h4 class="text-center">Recent chats</h4>
              <ul class="list-group">
              <c:if test="${empty chats}">
				    <p>No recent chats. Start conversation with someone</p>
				</c:if>
              <c:forEach items="${chats}" var="chat">
         		<c:if test="${chat.user1.id == loggedUser.id}">
         			<c:set var = "friend" scope = "session" value = "${chat.user2}"/>
         		</c:if> 
         		<c:if test="${chat.user2.id == loggedUser.id}">
         			<c:set var = "friend" scope = "session" value = "${chat.user1}"/>
         		</c:if>    
                <li class="list-group-item">
                  <div class="media">
                    <div class="media-left">
                      <a href="profile/${friend.id}"><img src="<c:url value="/images/${friend.photo}"/>" class="media-object img-60" alt="<c:url value="/images/${friend.photo}"/>"></a>
                    </div>
                    <div class="media-body">
                      <a href="profile/${friend.id}">
                      	<c:if test="${not empty chat.chat_Lines}"><c:set var="length" value="${fn:length(chat.chat_Lines)}" /></c:if>
                        <h4 class="media-heading black">${friend.firstname} <c:if test="${not empty chat.chat_Lines}">
                        	<small><i><fmt:formatDate value="${chat.chat_Lines[length - 1].created_at}" pattern="yyyy/MM/dd HH:mm"/></i></small>
                        	</c:if>
                        </h4>
                      </a>
                      <form method="POST" action="messages/setChat/${chat.id}">
                      	<button type="submit" class="btn btn-link">
                      		<c:if test="${empty chat.chat_Lines}">Write something</c:if>                     		
                      		<c:if test="${not empty chat.chat_Lines}">${chat.chat_Lines[length - 1].line_text}</c:if>
                      	</button>
                      </form>
                    </div>
                  </div>
                </li>
                </c:forEach>
              </ul>
            </div>
            <div class="well">
              <h4 class="text-center">Start new chat</h4>
              <ul class="list-group">
              	<c:if test="${empty usersToChat}">
				    <p>No other users on Kivbook to write. Tell your friends about Kivbook</p>
				</c:if>              
                <c:forEach items="${usersToChat}" var="friend">  
                <li class="list-group-item">
                  <div class="media">
                    <div class="media-left">
                      <a href="profile/${friend.id}"><img src="<c:url value="/images/${friend.photo}"/>" class="media-object img-60" alt="<c:url value="/images/${friend.photo}"/>"></a>
                    </div>
                    <div class="media-body">
                      <a href="profile/${friend.id}">
                        <h4 class="media-heading black">${friend.firstname}</h4>
                      </a>
                      <form method="POST" action="messages/addChat/${friend.id}">
                      	<button type="submit" class="btn btn-link">Start a conversation</button>
                      </form>
                    </div>
                  </div>
                </li>
                </c:forEach>           
              </ul>
            </div>
          </div>
          <div class="col-sm-8">
          	 
			<c:if test="${not empty selectedChat}">
				<c:if test="${selectedChat.user1.id == loggedUser.id}">
         			<c:set var = "friend" scope = "session" value = "${selectedChat.user2}"/>
         		</c:if> 
         		<c:if test="${selectedChat.user2.id == loggedUser.id}">
         			<c:set var = "friend" scope = "session" value = "${selectedChat.user1}"/>
         		</c:if>  
			<h4 class="text-center">Chat with ${friend.firstname}</h4>
            <ul class="list-group">
            	<c:if test="${empty activeChat}">
					<p class="text-center">No messages so far</p>
				</c:if>            	             
              <c:forEach items="${activeChat}" var="message">  
	              <li class="list-group-item">
	                <div class="media">
	                  <div class="media-left">
	                    <a href="profile/${message.sender.id}"><img src="<c:url value="/images/${message.sender.photo}"/>" class="media-object img-60" alt="<c:url value="/images/${message.sender.photo}"/>"></a>
	                  </div>
	                  <div class="media-body">
	                    <h4 class="media-heading"><a href="profile/${message.sender.id}">${message.sender.firstname}</a> <small><i><fmt:formatDate value="${message.created_at}" pattern="yyyy/MM/dd HH:mm"/></i></small></h4>
	                    <p>${message.line_text}</p>
	                  </div>
	                </div>
	              </li>
              </c:forEach>  
              <script type="text/javascript">
			    	function ajaxMsg() {
				        $.ajax({
				            url : 'ajaxMessages',
				            dataType : 'json',
				            contentType: "application/json;charset=utf-8",
				            success : function(data) {
				            	if (data[0] != "${fn:length(activeChat)}" || data[1] != "${unreadCount}"){
				            		window.location.reload(true); 
				            	}
				            }
				        });
				    }
			  </script> 
			  <script type="text/javascript">
				$( document ).ready(function() {
			    	ajaxMsg();
					var intervalI2 = 0;
					intervalId2 = setInterval(ajaxMsg, 1000);
				});   
			  </script>                   
            </ul>
            <div class="row">
              <div class="col-sm-12">
                <div class="media">
                  <div class="media-left">
                    <img src="<c:url value="/images/${loggedUser.photo}"/>" class="media-object img-60" alt="<c:url value="/images/${loggedUser.photo}"/>">
                  </div>
                  <div class="media-body">
                    <form:form data-toggle="validator" modelAttribute="chat_Line" method="post" action="messages/newMessage">
                      <div class="form-group">
                        <textarea placeholder="Write a message" required="required" class="form-control" rows="2" id="message"></textarea>
                      </div>
                      <div hidden>
                      	<form:input type="text" id="line_text" path="line_text" value=""></form:input>
                      </div>
                      <button type="submit" class="btn btn-primary">Send</button>
                      <script>
		            	$('#message').change(function() {
				    		$('#line_text').val($(this).val());
						});
			          </script>	
                    </form:form>
                  </div>
                </div>
              </div>
            </div>
            </c:if> 
          </div>
        </div>
      </div>
    </div>
  </body>
</html>