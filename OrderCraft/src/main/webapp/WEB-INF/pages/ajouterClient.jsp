<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un client</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Ajouter un client</h1>

    <form action="${pageContext.request.contextPath}/AjouterClient" method="post">

        <div class="form-group">
            <label for="firstName">Prénom :</label>
            <input type="text" class="form-control" id="firstName" name="firstName" required>
        </div>

        <div class="form-group">
            <label for="lastName">Nom :</label>
            <input type="text" class="form-control" id="lastName" name="lastName" required>
        </div>

        <div class="form-group">
            <label for="address">Adresse :</label>
            <input type="text" class="form-control" id="address" name="address" required>
        </div>

        <button type="submit" class="btn btn-primary">Ajouter Client</button>
    </form>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
