<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Client" %>
<html>
<head>
    <title>Modifier un Client</title>
   
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Modifier un Client</h1>
        <form action="${pageContext.request.contextPath}/AcceuilClient" method="get">
    	<input class="btn btn-outline-dark mx-2" type="submit"  value="<=" >
    </form>

    <form action="${pageContext.request.contextPath}/UpdateClient" method="post">
         <% Client client = (Client) request.getAttribute("client"); %>
         
          <input type="hidden" name="clientId" value="<%= client.getClientId() %>">
        <div class="form-group">
            <label for="productName">Prenom : </label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="<%= client.getFirstName() %>" required>
        </div>

        <div class="form-group">
            <label for="label">Nom : </label>
            <input type="text" class="form-control" id="lastName" name="lastName" value="<%= client.getLastName() %>"  required>
        </div>

        <div class="form-group">
            <label for="quantityInStock">Address</label>
            <input type="text" class="form-control" id="address" name="address" value="<%= client.getAddress() %>"  required>
        </div>

    

        <button type="submit" class="btn btn-primary">Enregstrer les modifications</button>
    </form>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
