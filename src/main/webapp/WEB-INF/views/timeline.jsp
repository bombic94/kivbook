<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <title>Kivbook timeline</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="kivbook.css">
    <link rel="stylesheet" type="text/css" href="kivbook-navbar.css">
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
            <li><a href="#"><span class="glyphicon glyphicon-home"></span> Home <span class="badge">7</span></a></li>
            <li><a href="./profile.html"><span class="glyphicon glyphicon-user"></span> My Account</a></li>
            <li><a href="./messages.html"><span class="glyphicon glyphicon-envelope"></span> Messages <span class="badge">0</span></a></li>
            <li><a href="./users.html"><span class="glyphicon glyphicon-list-alt"></span> Find friends <span class="badge">2</span></a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="./settings.html"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
            <li><a href="./about.html"><span class="glyphicon glyphicon-info-sign"></span> About</a></li>
            <li><a href="./homepage.html"><span class="glyphicon glyphicon-off"></span> Log out</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-sm-4">
            <div class="well text-center">
              <h4><a href="./profile.html">My Account</a></h4>
              <img src="img/04-kenny-mccormick-a.png" class="img-circle img-120" alt="Avatar">
            </div>
            <div class="well">
              <h4 class="text-center"><a href="./messages.html">Messages</a></h4>
              <ul class="list-group">
                <li class="list-group-item">
                  <a href="./messages.html">
                    <div class="media">
                      <div class="media-left">
                        <img src="img/05-chef-a.png" class="media-object img-40" alt="photo"> 
                      </div>
                      <div class="media-body">
                        <h4 class="media-heading black">Chef</h4>
                        <p class="media-heading black">Hi, whats new?</p>
                      </div>
                    </div>
                  </a>
                </li>
                <li class="list-group-item">
                  <a href="./messages.html">
                    <div class="media">
                      <div class="media-left">
                        <img src="img/06-mackey-a.png" class="media-object img-40" alt="photo">
                      </div>
                      <div class="media-body">
                        <h4 class="media-heading black">Mackey</h4>
                        <p class="media-heading black">Drugs are bad, mkay?</p>
                      </div>
                    </div>
                  </a>
                </li>
              </ul>
            </div>
            <div class="well">
              <h4 class="text-center"><a href="./users.html">Find friends</a></h4>
              <ul class="list-group">
                <li class="list-group-item">
                  <a href="./users.html">
                    <div class="media">
                      <div class="media-left">
                        <img src="img/02-kyle-broflovski-a.png" class="media-object img-40" alt="photo">
                      </div>
                      <div class="media-body">
                        <h4 class="media-heading black">Kyle Broflovski</h4>
                        <p class="media-heading black">On Kivbook since January 1, 2017</p>
                      </div>
                    </div>
                  </a>
                </li>
                <li class="list-group-item">
                  <a href="./users.html">
                    <div class="media">
                      <div class="media-left">
                        <img src="img/01-stan-marsh-a.png" class="media-object img-40" alt="photo">
                      </div>
                      <div class="media-body">
                        <h4 class="media-heading black">Stan Marsh</h4>
                        <p class="media-heading black">On Kivbook since January 1, 2017</p>
                      </div>
                    </div>
                  </a>
                </li>
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
            <span style="display:inline-block; width: 10em;"></span>
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