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
              <h4 class="text-center"><a href="users">Pending requests</a></h4>
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
                    <img src="img/04-kenny-mccormick-a.png" class="media-object img-60" alt="photo">
                  </div>
                  <div class="media-body">
                    <form data-toggle="validator">
                      <div class="form-group">
                        <textarea placeholder="Tell us what's new" required="required" class="form-control" rows="3" id="status"></textarea>
                      </div>
                      <label class="btn btn-default">
                      Upload a picture <input type="file" accept="image/*" hidden>
                      </label>
                      <button type="submit" class="btn btn-primary">Post</button>
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <span style="display:inline-block; width: 10em;height: 1em;"></span>
            <div class="well">
              <div class="media">
                <div class="media-left">
                  <a href="./profile.html">
                    <img src="img/05-chef-a.png" class="media-object img-60" alt="photo">
                  </a>
                </div>              
                <div class="media-body">
                  <h4 class="media-heading"><a href="./profile.html">Chef</a> <small><i>January 21, 2017, 20:58</i></small></h4>
                  <p class="media-heading"> Hey kids, how you all doing</p>
                  <div class="media-heading">
                    <button type="button" class="btn btn-default invisible-button">
                      <span class="glyphicon glyphicon-thumbs-up"></span>
                    </button>
                    <button type="button" data-toggle="collapse" data-target="#comments1" class="btn btn-default invisible-button">
                      <span class="glyphicon glyphicon-comment"></span>
                    </button>  
                    <small>5 likes, 2 comments</small>
                    <div class="collapse" id="comments1">
                      <div class="media">
                        <div class="media-left">
                          <a href="./profile.html">
                          <img src="img/04-kenny-mccormick-a.png" class="media-object img-40" alt="photo">
                          </a>
                        </div>
                        <div class="media-body">
                          <h5 class="media-heading"><a href="./profile.html">Kenny</a> <small><i>January 21, 2017, 21:00</i></small></h5>
                          <h6 class="black">Pretty good Chef, how about you?</h6>
                        </div>
                      </div>
                      <div class="media">
                        <div class="media-left">
                          <a href="./profile.html">
                          <img src="img/05-chef-a.png" class="media-object img-40" alt="photo">
                          </a>
                        </div>
                        <div class="media-body">
                          <h5 class="media-heading"><a href="./profile.html">Chef</a> <small><i>January 21, 2017, 21:05</i></small></h5>
                          <h6 class="black">I'm gonna make love to the woman!</h6>
                        </div>
                      </div>
                      <div class="media">
                        <form data-toggle="validator">
                          <div class="media-left">
                            <img src="img/04-kenny-mccormick-a.png" class="media-object img-40" alt="photo">
                          </div>
                          <div class="media-body">
                              <div class="form-group">
                                <textarea placeholder="Write a comment" required="required" class="form-control" rows="1" id="comment1"></textarea>
                              </div>
                          </div>
                          <div class="media-right">
                            <button type="submit" class="btn btn-default btn-sm">
                              <span class="	glyphicon glyphicon-comment"></span> Comment
                            </button>
                          </div> 
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="well">
              <div class="media">
                <div class="media-left">
                  <a href="./profile.html">
                    <img src="img/05-chef-a.png" class="media-object img-60" alt="photo">
                  </a>
                </div>              
                <div class="media-body">
                  <h4 class="media-heading"><a href="./profile.html">Chef</a> <small><i>January 21, 2017, 20:58</i></small></h4>
                  <p class="media-heading"> Hey kids, how you all doing</p>
                  <div class="media-heading">
                    <button type="button" class="btn btn-default invisible-button">
                      <span class="glyphicon glyphicon-thumbs-up"></span>
                    </button>
                    <button type="button" data-toggle="collapse" data-target="#comments2" class="btn btn-default invisible-button">
                      <span class="glyphicon glyphicon-comment"></span>
                    </button>  
                    <small>5 likes, 2 comments</small>
                    <div class="collapse" id="comments2">
                      <div class="media">
                        <div class="media-left">
                          <a href="./profile.html">
                          <img src="img/04-kenny-mccormick-a.png" class="media-object img-40" alt="photo">
                          </a>
                        </div>
                        <div class="media-body">
                          <h5 class="media-heading"><a href="./profile.html">Kenny</a> <small><i>January 21, 2017, 21:00</i></small></h5>
                          <h6 class="black">Pretty good Chef, how about you?</h6>
                        </div>
                      </div>
                      <div class="media">
                        <div class="media-left">
                          <a href="./profile.html">
                          <img src="img/05-chef-a.png" class="media-object img-40" alt="photo">
                          </a>
                        </div>
                        <div class="media-body">
                          <h5 class="media-heading"><a href="./profile.html">Chef</a> <small><i>January 21, 2017, 21:05</i></small></h5>
                          <h6 class="black">I'm gonna make love to the woman!</h6>
                        </div>
                      </div>
                      <div class="media">
                        <form data-toggle="validator">
                          <div class="media-left">
                            <img src="img/04-kenny-mccormick-a.png" class="media-object img-40" alt="photo">
                          </div>
                          <div class="media-body">
                              <div class="form-group">
                                <textarea placeholder="Write a comment" required="required" class="form-control" rows="1" id="comment2"></textarea>
                              </div>
                          </div>
                          <div class="media-right">
                            <button type="submit" class="btn btn-default btn-sm">
                              <span class="	glyphicon glyphicon-comment"></span> Comment
                            </button>
                          </div> 
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="text-center">
              <ul class="pagination">
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>