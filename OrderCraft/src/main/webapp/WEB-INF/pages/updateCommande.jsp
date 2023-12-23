<%@ page import="java.util.List" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Produit" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Client" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Commande" %>

<html>
<head>
    <title>Modifier la Commande</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Modifier la commande</h1>

    <form action="${pageContext.request.contextPath}/UpdateCommande" method="post">
   
  			<% List<Client> clients = (List<Client>) request.getAttribute("clients"); %>
              <% Commande commande = (Commande) request.getAttribute("commande"); %>
              
              <input type="hidden" name="commandeIdToUpdate" value="<%= commande.getCommandeId() %>">
              
      <div class="form-group">
            <label for="client">Choisir un client :</label>
            <select class="form-control" id="client" name="client">
            
              <option value="<%=commande.getClient().getClientId() %>"><%=commande.getClient().getFirstName() %> <%=commande.getClient().getLastName() %></option>
              
                    <% for (Client client : clients) { %>
                    <option value="<%= client.getClientId() %>"><%= client.getFirstName() %> <%= client.getLastName() %></option>
                <% } %>
            </select>
        </div>

     
        <div id="produitsForm">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="produit">Choisir un produit :</label>
                    <select class="form-control" name="produit">
                    <option value="-1">choisis  un Produit</option>
                    <% List<Produit> produits = (List<Produit>) request.getAttribute("produits"); %>
                        <% for (Produit produit : produits) { %>
                            <option value="<%= produit.getProductId() %>"><%= produit.getProduct_name() %>  </option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label for="quantite">Quantité :</label>
                    <input type="number" class="form-control" name="quantite" required>
                    
                </div>
                   <div class="form-group col-md-2  d-flex align-items-end ">
                    <button type="button" class="btn btn-success" onclick="ajouterProduit()">Ajouter</button>
                </div>
             
            </div>
        </div>

        <div class="form-row" id="listeProduits">
          </div>
        <div class="form-group">
				    <label for="status">Choisir le statut :</label>
				   <option value="<%=commande.getStatut().getLibelle().toUpperCase() %>"><%=commande.getStatut().getLibelle()%></option>
				    <select class="form-control" name="status">
				        <option value="EN_COURS">En cours</option>
				        <option value="TERMINEE">Terminée</option>
				         <option value="ANNULEE">Annulee</option>
				    </select>
				</div>
				        
           <div class="form-group ">
                    <label for="description">Ajouter une description :</label>
                    <input type="text" class="form-control" name="commande_description" value="<%= commande.getCommande_description()%>" required>
                    
                </div>
                				        
           <div class="form-group ">
                    <label for="dateDeCommande">Ajouter une date De Commande :</label>
                    <input class="form-control" type="date" name="dateDeCommande" value="<%= commande.getDateDeCommande() %>">
          
                    
                </div>



        <button type="submit" class="btn btn-primary">Modifier la Commande</button>
    </form>

</div>

<script>
    function ajouterProduit() {

        var produitSelect = document.querySelector('select[name="produit"]');
        var quantiteInput = document.querySelector('input[name="quantite"]');
        var produitId = produitSelect.value;
        var produitNom = produitSelect.options[produitSelect.selectedIndex].text;
        var quantite = quantiteInput.value;

        var listeProduits = document.getElementById('listeProduits');
        var inputquantite = document.createElement('input');
        var inputproduit=document.createElement('input');
        var inputProductName=document.createElement('input');
        
        //produit input attribut 
         inputProductName.type = "text";
         inputProductName.className = "form-control col-md-6";
       
         inputProductName.value = produitNom;
         inputProductName.readOnly = true;
        
         listeProduits.appendChild(inputProductName);
        
        
        
        //produit input attribut 
         inputproduit.type = "hidden";
         inputproduit.className = "form-control col-md-6";
         inputproduit.name = "produit";
         inputproduit.value = produitId;
   
        
         listeProduits.appendChild(inputproduit);
        
         
         
        //quantity input attribut
        inputquantite.type = "number";
        inputquantite.className = "form-control form-control col-md-6";
        inputquantite.name = "quantite";
        inputquantite.value = quantite;
        inputquantite.readOnly = true;
        listeProduits.appendChild(inputquantite);
    }
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>




