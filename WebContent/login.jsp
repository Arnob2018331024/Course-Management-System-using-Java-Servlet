<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="styles/loginForm.css">
</head>
<body>
    <form method="post" action="login" id="login-form" class="login-form" autocomplete="off" role="main">
  <h1 class="a11y-hidden">Login Form</h1>
  <div>
    <label class="label-email">
      <input type="text" class="text" name="name" placeholder="User name" tabindex="1" required />
      <span class="required">User name</span>
    </label>
  </div>
  <input type="checkbox" name="password" class="show-password a11y-hidden" id="show-password" tabindex="3" onclick="myFunction()" />
  <label class="label-show-password" for="show-password">
    <span>Show Password</span>
  </label>
 
  <div>
    <label class="label-password">
      <input type="password" class="text" name="password" id="password" placeholder="Password" tabindex="2" required />
      <span class="required">Password</span>
    </label>
  </div>
  
   <script>
  function myFunction() {
	  var x = document.getElementById("password");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	} 
  
  </script>
  
  <input type="submit" value="Log In" />
  <div class="email">
    
  </div>
  <figure aria-hidden="true">
    <div class="person-body"></div>
    <div class="neck skin"></div>
    <div class="head skin">
      <div class="eyes"></div>
      <div class="mouth"></div>
    </div>
    <div class="hair"></div>
    <div class="ears"></div>
    <div class="shirt-1"></div>
    <div class="shirt-2"></div>
  </figure>
</form>
</body>
</html>