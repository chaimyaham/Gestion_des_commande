<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un Produit</title>
    <!-- Ajoutez le lien vers Bootstrap CSS ici -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Ajouter un Produit</h1>

    <form action="${pageContext.request.contextPath}/AddProduct" method="post">
        <div class="form-group">
            <label for="productName">Nom du Produit</label>
            <input type="text" class="form-control" id="productName" name="productName" required>
        </div>

        <div class="form-group">
            <label for="label">Label</label>
            <input type="text" class="form-control" id="label" name="label" required>
        </div>

        <div class="form-group">
            <label for="quantityInStock">Quantité en Stock</label>
            <input type="number" class="form-control" id="quantityInStock" name="quantityInStock" required>
        </div>

        <div class="form-group">
            <label for="unitPrice">Prix Unitaire</label>
            <input type="number" class="form-control" id="unitPrice" name="unitPrice" step="0.01" required>
        </div>

        <button type="submit" class="btn btn-primary">Ajouter le Produit</button>
    </form>
</div>

<!-- Ajoutez le lien vers Bootstrap JS et jQuery ici -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
