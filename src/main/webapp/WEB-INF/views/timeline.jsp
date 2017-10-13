<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <title>Kivbook timeline</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
            <div class="well text-center">
              <h4><a href="profile/${loggedUser.id}">My Account</a></h4>
              <img src="<c:url value="/images/${loggedUser.photo}"/>" id="profilePicture" class="img-circle img-120" alt="<c:url value="/images/${loggedUser.photo}"/>">
            </div>
            <div class="well">
              <h4 class="text-center"><a href="messages">Recent chats</a></h4>
              <ul class="list-group">
              <c:if test="${empty chats}">
				    <p>No recent chats. Start conversation with someone</p>
				</c:if>
              <c:forEach begin="0" end="1" items="${chats}" var="chat">
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
              <h4 class="text-center"><a href="users">Pending requests</a></h4>
              <ul class="list-group">     
              	<c:if test="${empty pendingFriendships}">
				    <p>No pending requests. Add someone as friend</p>
				</c:if>         
                <c:forEach begin="0" end="1" items="${pendingFriendships}" var="friendship">
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
                      <form method="POST" action="users/acceptFriend/${friendship.id}">
                      	<button type="submit" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign friendicon-plus"></span></button>
                      </form>
                    </div>
                  </div>
                </li>
                </c:forEach>           
              </ul>
            </div>
            <div class="well">
              <h4 class="text-center"><a href="users">Find new friends</a></h4>
              <ul class="list-group">
              	<c:if test="${empty usersToFriend}">
				    <p>No other users on Kivbook to connect. Tell your friends about Kivbook</p>
				</c:if>              
                <c:forEach begin="0" end="1" items="${usersToFriend}" var="friend">  
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
                      <form method="POST" action="users/addFriend/${friend.id}">
                      	<button type="submit" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign friendicon-plus"></span></button>
                      </form>
                    </div>
                  </div>
                </li>
                </c:forEach>           
              </ul>
            </div>
          </div>
          <div class="col-sm-8">
            <div class="row">
              <div class="col-sm-12">
                <div class="media">
                  <div class="media-left">
                    <img src="<c:url value="/images/${loggedUser.photo}"/>" class="media-object img-60" alt="<c:url value="/images/${loggedUser.photo}"/>">
                  </div>
                  <div class="media-body">
                    <form data-toggle="validator" method="POST" action="timeline/newStatus" enctype="multipart/form-data">
                      <div class="form-group">
                        <textarea placeholder="Tell us what's new" required="required" class="form-control" rows="3" id="status"></textarea>
                        <div id="status-img-div" style="display:none">
                      		<img src="" id="status-img" class="img-thumbnail img-250" alt="status-img">
                      	</div>
                      </div>                      
                      <div hidden>
                      	<input type="text" id="text" name="text" value=""></input>
                      </div>
                      <label class="btn btn-default">
                      Add a picture <input type="file" id="file" name="file" accept="image/*" onchange="loadFile(event)" hidden>
                      </label>
                      <button type="submit" class="btn btn-primary">Post</button>
                      <script>
		            	$('#status').change(function() {
				    		$('#text').val($(this).val());
						});
			          </script>	
			          <script>
						  var loadFile = function(event) {
						    var pic = document.getElementById('status-img');
						    pic.src = URL.createObjectURL(event.target.files[0]);
						    var div = document.getElementById('status-img-div');
						    div.style.display = 'block'; 
						  };
					  </script>	
                    </form> 
                  </div>  
                </div>
              </div>
            </div>
            <span style="display:inline-block; width: 10em;height: 1em;"></span>
            <c:forEach items="${statuses}" var="status">
            <div class="well">
              <div class="media">
                <div class="media-left">
                  <a href="profile/${status.user.id}">
                    <img src="<c:url value="/images/${status.user.photo}"/>" class="media-object img-60" alt="<c:url value="/images/${status.user.photo}"/>">
                  </a>
                </div>              
                <div class="media-body">
                  <h4 class="media-heading"><a href="profile/${status.user.id}">${status.user.firstname}</a> <small><i><fmt:formatDate value="${status.created_at}" pattern="yyyy/MM/dd HH:mm"/></i></small></h4>
                  <p class="media-heading"> ${status.status_text}</p>
                  <c:if test="${not empty status.photo}">
                  	<div>
                      <img src="<c:url value="/images/${status.photo}"/>" id="status-img" class="img-thumbnail img-120" alt="status-img">
                    </div>
                  </c:if>
                  <div class="media-heading">
                  	<form method="POST" action="timeline/like/${status.id}">
                  	  
                   	  <button type="submit" class="btn btn-default invisible-button">
                        <span class="glyphicon glyphicon-thumbs-up" style=" 
                          <c:if test="${not empty userLikes}">
                   			<c:forEach items="${userLikes}" var="like">
								<c:if test="${like.status.id == status.id}">
			         				color: blue;
			         			</c:if>		         	
                    		</c:forEach>
                      	  </c:if>">
                      	</span>
                      </button>
                      <button type="button" data-toggle="collapse" data-target="#comments${status.id}" class="btn btn-default invisible-button">
                        <span class="glyphicon glyphicon-comment"></span>
                      </button>  
                      <small>${fn:length(status.likes)} likes, ${fn:length(status.comments)} comments</small>
                    </form>                   
                    <div class="collapse" id="comments${status.id}">
                      
                      <c:forEach items="${status.comments}" var="comment">
	                      <div class="media">
	                        <div class="media-left">
	                          <a href="profile/${comment.user.id}">
	                          <img src="<c:url value="/images/${comment.user.photo}"/>" class="media-object img-40" alt="<c:url value="/images/${comment.user.photo}"/>">
	                          </a>
	                        </div>
	                        <div class="media-body">
	                          <h5 class="media-heading"><a href="profile/${comment.user.id}">${comment.user.firstname}</a> <small><i><fmt:formatDate value="${comment.created_at}" pattern="yyyy/MM/dd HH:mm"/></i></small></h5>
	                          <h6 class="black">${comment.comment_text}</h6>
	                        </div>
	                      </div>
                      </c:forEach>
                      <div class="media">
                        <form data-toggle="validator" method="POST" action="timeline/newComment/${status.id}">
                          <div class="media-left">
                            <img src="<c:url value="/images/${loggedUser.photo}"/>" class="media-object img-40" alt="<c:url value="/images/${loggedUser.photo}"/>">
                          </div>
                          <div class="media-body">
                              <div class="form-group">
                                <textarea placeholder="Write a comment" required="required" class="form-control" rows="1" id="commenttext${status.id}"></textarea>
                              </div>
                          </div>
                          <div hidden>
                      		<input type="text" id="comment_text${status.id}" name="comment_text" value=""></input>
                      	  </div>
                          <div class="media-right">
                            <button type="submit" class="btn btn-default btn-sm">
                              <span class="	glyphicon glyphicon-comment"></span> Comment
                            </button>
                          </div> 
                          <script>
			            	$('#commenttext${status.id}').change(function() {
					    		$('#comment_text${status.id}').val($(this).val());
							});
				          </script>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            </c:forEach>
            <div class="text-center">
              <ul class="pagination">
              	<c:forEach var="i" begin="1" end="${pages}">
                	<li <c:if test="${i eq activePage}">class="active"</c:if>>
                	<c:url var="url" value="/timeline">
					   <c:param name="page" value="${i}"/>
					</c:url>
					<a href="${url}">${i}</a></li>
                </c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>