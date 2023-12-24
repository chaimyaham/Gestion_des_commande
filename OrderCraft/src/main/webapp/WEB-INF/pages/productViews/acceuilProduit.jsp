<%@ page import="java.util.List" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Produit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Produits</title>
    <!-- Ajoutez le lien vers Bootstrap CSS ici -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Liste des Produits</h1>
    
    
 <div class="row">
 	      <form action="${pageContext.request.contextPath}/Login" method="get">
    	<input class="btn btn-outline-dark mx-2" type="submit"  value="<=" >
    </form>
    <form action="${pageContext.request.contextPath}/AddProduct" method="get">
    	<input class="btn btn-outline-info mx-2" type="submit"  value="Ajouter produit" >
    </form>
     <form action="${pageContext.request.contextPath}/AcceuilClient" method="get">
    	<input class="btn btn-outline-secondary mx-2" type="submit"  value="voir la liste des clients" >
    </form>
 
 </div>

    <% List<Produit> produits = (List<Produit>) request.getAttribute("produits"); %>

    <% if (produits != null && !produits.isEmpty()) { %>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID Produit</th>
                    <th>Nom du Produit</th>
                    <th>Label</th>
                    <th>Quantité en Stock</th>
                    <th>Prix Unitaire</th>
                     <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for (Produit produit : produits) { %>
                    <tr>
                        <td><%= produit.getProductId() %></td>
                        <td><%= produit.getProduct_name() %></td>
                        <td><%= produit.getLabel() %></td>
                        <td><%= produit.getQuantite_in_stock() %></td>
                        <td><%= produit.getPrix_unitaire() %> MAD</td>
                        
				            <td class="d-flex justify-content-between">
							    <div class="col-md-6">
							        <form action="${pageContext.request.contextPath}/UpdateProduct" method="get">
							            <input type="hidden" name="productId" value="<%= produit.getProductId() %>">
							            <button type="submit" class="btn btn-outline-dark">Update</button>
							        </form>
							    </div>
							    <div class="col-md-6">
							        <form id="deleteForm" action="${pageContext.request.contextPath}/DeleteProduct" method="post">
							            <input type="hidden" name="productId" value="<%= produit.getProductId() %>">
							            <button type="submit" class="btn btn-outline-danger" onclick="confirmDelete()">Delete</button>
							        </form>
							    </div>
				</td>
                          
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>Aucun produit à afficher.</p>
    <% } %>

</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
	function confirmDelete() {
	    var confirmation = confirm("Êtes-vous sûr de vouloir supprimer ce Produit ?");
	    if (confirmation) {
	        document.getElementById('deleteForm').submit();
	    }
}
</script>
</body>
</html>
