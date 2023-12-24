<%@ page import="java.util.List" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Clients</title>
    <!-- Ajoutez le lien vers Bootstrap CSS ici -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Liste des Clients</h1>
    
    
 <div class="row">
 	      <form action="${pageContext.request.contextPath}/AcceuilProduct" method="get">
    	<input class="btn btn-outline-dark mx-2" type="submit"  value="<=" >
    </form>
    <form action="${pageContext.request.contextPath}/AddClient" method="get">
    	<input class="btn btn-outline-info mx-2" type="submit"  value="Ajouter Client" >
    </form>
     <form action="${pageContext.request.contextPath}/Login" method="get">
    	<input class="btn btn-outline-secondary mx-2" type="submit"  value="voir la liste des commande" >
    </form>
 
 </div>

    <% List<Client> clients = (List<Client>) request.getAttribute("clients"); %>

    <% if (clients != null && !clients.isEmpty()) { %>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID Client</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Address</th>
                   
                     <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for (Client client : clients) { %>
                    <tr>
                        <td><%= client.getClientId() %></td>
                        <td><%= client.getFirstName() %></td>
                        <td><%= client.getLastName() %></td>
                        <td><%= client.getAddress() %></td>
                     
                        
				            <td class="d-flex justify-content-between">
							    <div class="col-md-6">
							        <form action="${pageContext.request.contextPath}/UpdateClient" method="get">
							            <input type="hidden" name="clientId" value="<%= client.getClientId() %>">
							            <button type="submit" class="btn btn-outline-dark">Update</button>
							        </form>
							    </div>
							    <div class="col-md-6">
							        <form id="deleteForm" action="${pageContext.request.contextPath}/DeleteClient" method="post">
							            <input type="hidden" name="clientId" value="<%= client.getClientId() %>">
							            <button type="submit" class="btn btn-outline-danger" onclick="confirmDelete()">Delete</button>
							        </form>
							    </div>
				</td>
                          
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>Aucun Client à afficher.</p>
    <% } %>

</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
	function confirmDelete() {
	    var confirmation = confirm("Êtes-vous sûr de vouloir supprimer ce Client ?");
	    if (confirmation) {
	        document.getElementById('deleteForm').submit();
	    }
}
</script>
</body>
</html>
