<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Produit" %>
<html>
<head>
    <title>Modifier le Produit</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Modifier le Produit</h1>
    
        <form action="${pageContext.request.contextPath}/AcceuilProduct" method="get">
    	<input class="btn btn-outline-dark mx-2" type="submit"  value="<=" >
    </form>

    <form action="${pageContext.request.contextPath}/UpdateProduct" method="post">
     <% Produit produit = (Produit) request.getAttribute("produit"); %>
     
     <input type="hidden" name="productId" value="<%= produit.getProductId() %>">
        <div class="form-group">
            <label for="productName">Nom du Produit</label>
            <input type="text" class="form-control" id="productName" name="productName" value="<%= produit.getProduct_name() %>" required>
        </div>

        <div class="form-group">
            <label for="label">Label</label>
            <input type="text" class="form-control" id="label" name="label" value="<%= produit.getLabel() %>" required>
        </div>

        <div class="form-group">
            <label for="quantityInStock">Quantité en Stock</label>
            <input type="number" class="form-control" id="quantityInStock" name="quantityInStock" value="<%= produit.getQuantite_in_stock() %>" required>
        </div>

        <div class="form-group">
            <label for="unitPrice">Prix Unitaire</label>
            <input type="number" class="form-control" id="unitPrice" name="unitPrice" step="0.01" value="<%= produit.getPrix_unitaire() %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Enregistrer</button>
    </form>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
