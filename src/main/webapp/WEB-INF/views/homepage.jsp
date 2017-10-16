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
    <title>Kivbook homepage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <c:url value="resources/kivbook.css" var="css1"/>
    <c:url value="resources/kivbook-navbar.css" var="css2"/>
    <link rel="stylesheet" type="text/css" href="${css1}">
    <link rel="stylesheet" type="text/css" href="${css2}">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- validator for inputs-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
    <!-- captcha-->
    <script src="https://www.google.com/recaptcha/api.js"></script>
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
      </div>
    </nav>
    <div class="content">
      <div class="container-fluid">
        <div class="row">
        
          <!-- Modal -->
		  <div class="modal fade" id="myModal" role="dialog">
		    <div class="modal-dialog">
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Kivbook info</h4>
		        </div>
		        <div class="modal-body">
		          <p>${message}</p>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		      </div>
		    </div>
		  </div>
		  <script type="text/javascript">
           	$('#myModal').modal({ show: false})
          </script> 
		  <c:if test="${not empty message}">  
		    <script type="text/javascript">
                	$('#myModal').modal('show');
            </script> 
		  </c:if>	  
          <div class="col-sm-8">
            <p>Kivbook is a simple social network developed for needs of Department of Informatics and Computer Science</p>
            <h3 class="text-center">There are <strong>${userCount}</strong> users of Kivbook at this moment</h3>
            
            
		  </div>			
          <div class="col-sm-4 well">
            <ul class="nav nav-pills">
              <li class="active"><a data-toggle="pill" href="#login">Login</a></li>
              <li><a data-toggle="pill" href="#signup">Sign up</a></li>
            </ul>
            <div class="tab-content">
              <div id="login" class="tab-pane fade in active">
                <h3>Login</h3>
                <form:form data-toggle="validator" modelAttribute="user" method="post" action="homepage/login">
                  <div class="form-group">
                    <label for="login-username">Username</label>
                    <form:input path="username" type="text" class="form-control" id="login-username"
                      placeholder="Please enter your username" required="required" pattern="^[a-zA-Z0-9_@]+$" data-error="Username is required (only lowercase/uppercase letters, numbers, '_', '@')"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="login-password">Password</label>
                    <form:input path="password" type="password" class="form-control" id="login-password"
                      placeholder="Please enter your password" required="required" pattern="^[a-zA-Z0-9_@]+$" data-minlength="6" data-error="Password is required (Minimum of 6 characters, only lowercase/uppercase letters, numbers, '_', '@')"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="checkbox">
                    <label><form:checkbox path="rememberMe"/>Keep me logged in</label>
                  </div>
                  <button type="submit" class="btn btn-default" id="loginBtn" data-loading-text="Logging in...">Log in</button>
                  <script>
					  $('#loginBtn').on('click', function () {
					    var $btn = $(this).button('loading')
					  })
				  </script> 
                </form:form>
              </div>
              <div id="signup" class="tab-pane fade">
                <h3>Sign up</h3>
                 <form:form data-toggle="validator" modelAttribute="user" method="post" action="homepage/register">
                  <div class="form-group">
                    <label for="name">First name *</label>
                    <form:input path="firstname" type="text" class="form-control" id="name"
                      placeholder="Please enter your first name" required="required" pattern="^[a-zA-Z]+$" data-error="First name is required (only lowercase/uppercase letters)"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="surname">Last name *</label>
                    <form:input path="lastname" type="text" class="form-control" id="surname"
                      placeholder="Please enter your last name" required="required" pattern="^[a-zA-Z]+$" data-error="Last name is required (only lowercase/uppercase letters)"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="username">Username *</label>
                    <form:input path="username" type="text" class="form-control" id="username"
                      placeholder="Please enter your username" required="required" pattern="^[a-zA-Z0-9_@]+$" data-error="Username is required (only lowercase/uppercase letters, numbers, '_', '@')"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="datetimepicker">Date of birth</label>
                    <input type="text" class="form-control" id='datetimepicker' onkeydown="return false" placeholder="Please enter your date of birth"/>
                    <script type="text/javascript">
                      $(function () {
                          $('#datetimepicker').datetimepicker({
                              format: "YYYY-MM-DD",
                              defaultDate: "",
                          });
                      });
                     </script> 
                     <div hidden>
	                     <form:input path="dateofbirth" type="date" id="dateofbirth"/>
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
                    <label>Gender *</label>
                    <br>
                    <label class="radio-inline"><form:radiobutton path="gender" required="required" id="gender1" name="gender" value="m"/>Male</label>
                    <label class="radio-inline"><form:radiobutton path="gender" required="required" id="gender2" name="gender" value="f"/>Female</label>
                  </div>
                  <div class="form-group">
                    <label for="email">Email address *</label>
                    <form:input path="email" type="email" class="form-control" id="email"
                      placeholder="Please enter your email" required="required" data-error="Valid email is required"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="password">Password *</label>
                    <form:input path="password" type="password" class="form-control" id="password"
                      placeholder="Please enter your password" required="required" pattern="^[a-zA-Z0-9_@]+$" data-minlength="6" data-error="Password is required (Minimum of 6 characters, only lowercase/uppercase letters, numbers, '_', '@')"/>
                    <div class="help-block with-errors">Minimum of 6 characters</div>
                  </div>
                  <div class="form-group">
                    <label for="password2">Confirm password *</label>
                    <input type="password" class="form-control" id="password2"
                      placeholder="Please confirm your password" required="required" pattern="^[a-zA-Z0-9_@]+$" data-match="#password" data-match-error="Password does not match">
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="g-recaptcha" data-callback="correctCaptcha" data-sitekey="6LcTzjEUAAAAAN5D7WuKyKYP6yf4t0GCGoP5edFr"></div>
                  <div class="checkbox" hidden>
                    <label><input type="checkbox" required="required" id="captchaOK"></label>
                    <div class="help-block with-errors"></div>
                  </div>
                  <script type="text/javascript">
                    var correctCaptcha = function(response) {
			  	      if (response.length > 0) {
			        	$("#captchaOK").prop("checked", true);
		  	    	  }
				    };
                  </script>
	              <div class="checkbox">
                    <label><input type="checkbox" required="required" data-error="You must agree">I agree with conditions *</label>
                    <div class="help-block with-errors"></div>
                  </div>       
                  <button type="submit" class="btn btn-default" id="signupBtn" data-loading-text="Signing up...">Sign up</button>
                  <script>
					  $('#signupBtn').on('click', function () {
					    var $btn = $(this).button('loading')
					  })
				  </script>
                  <div class="row">
                    <div class="col-md-12">
                      <p class="text-muted"><strong>*</strong> These fields are required.</p>
                    </div>
                  </div>
                </form:form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>