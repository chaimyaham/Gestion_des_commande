<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Login Page</title>
</head>
<body>
	
	<div class="container-sm w-50 m-auto px-3 py-5">
	<h1 class="my-2 ">Login  </h1>
	
	<hr>
		<form action="${pageContext.request.contextPath}/Login" method="post">
  <!-- Email input -->
  <div class="form-outline mb-4">
    <input type="text" id="form2Example1" class="form-control" name="username" required />
    <label class="form-label" for="form2Example1"  >UserName</label>
  </div>

  <!-- Password input -->
  <div class="form-outline mb-4">
    <input type="password" id="form2Example2" class="form-control" name="password" />
    <label class="form-label" for="form2Example2">Password</label>
  </div>


  <!-- Submit button -->
  <input type="submit" class="btn btn-primary btn-block mb-4">

 

</form>

    <% if (request.getParameter("erreur") != null && request.getParameter("erreur").equals("1")) { %>
        <p class="alert text-danger">Identifiants invalides. Veuillez réessayer.</p>
    <% } %>

		
	</div>
	


</body>
</html>