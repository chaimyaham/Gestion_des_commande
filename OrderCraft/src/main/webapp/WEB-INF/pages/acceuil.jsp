<%@ page import="java.util.List" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Commande" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Produit" %>

<%@ page import="java.util.HashMap" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des commandes</title>
    <!-- Ajoutez le lien vers Bootstrap CSS ici -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Liste des commandes</h1>

    <% List<Commande> commandes = (List<Commande>) request.getAttribute("commandes"); %>
    
    
  <div class="row">
  	  
    <form action="${pageContext.request.contextPath}/AddCommande" method="get">
    	<input class="btn btn-outline-success mx-2" type="submit"  value="add commande" >
    </form>
    <form action="${pageContext.request.contextPath}/AddProduct" method="get">
    	<input class="btn btn-outline-info mx-2" type="submit"  value="add product" >
    </form>
     <form action="${pageContext.request.contextPath}/AddClient" method="get">
    	<input class="btn btn-outline-dark mx-2" type="submit"  value="add client" >
    </form>
  
  </div>
    <% if (commandes != null && !commandes.isEmpty()) { %>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Commande ID</th>
                    <th>Date de commande</th>
                    <th>Status</th>
                    <th>Commande Description</th>
                    <th>Liste des Produits</th>
                     <th>Prix totale de la commande</th>
                      <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <% for (Commande commande : commandes) { %>
                    <tr>
                        <td><%= commande.getCommandeId() %></td>
                        <td><%= commande.getDateDeCommande() %></td>
                        <td><%= commande.getStatut() %></td>
                        <td><%= commande.getCommande_description() %></td>
                        <td>
                            
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#produitsModal<%= commande.getCommandeId() %>">
                                Voir Produits
                            </button>

                           
                            <div class="modal fade" id="produitsModal<%= commande.getCommandeId() %>" tabindex="-1" role="dialog" aria-labelledby="produitsModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="produitsModalLabel">Liste des Produits</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <ul>
		                                           <% HashMap<Produit, Integer> produitsQuantites = commande.getListeDesProduitsEtLeursQuantite(); %>
		<% if (produitsQuantites != null && !produitsQuantites.isEmpty()) { %>
		    <ul>
		        <% for (Produit produit : produitsQuantites.keySet()) { %>
		            <li><strong>Produit :</strong> <%= produit.getProduct_name() %>,<strong>Prix:</strong> <%= produit.getPrix_unitaire() %>,, <strong>Quantité:</strong> <%= produitsQuantites.get(produit) %></li>
		        <% } %>
		    </ul>
		<% } else { %>
		    <p>Aucun produit associé à cette commande.</p>
		<% } %>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                          <td class="text-success"><%= commande.getPrixTotal() %>  MAD </td>
                            <td class="flex ">
			            <!-- Formulaire pour le bouton Update -->
			            <form action="${pageContext.request.contextPath}/UpdateCommande" method="post">
			                <input type="hidden" name="commandeId" value="<%= commande.getCommandeId() %>">
			                <button type="submit" class="btn btn-outline-dark">Update</button>
			            </form>
			
			            <!-- Formulaire pour le bouton Delete -->
			            <form action="${pageContext.request.contextPath}/DeleteCommande" method="post">
			                <input type="hidden" name="commandeId" value="<%= commande.getCommandeId() %>">
			                <button type="submit" class="btn btn-outline-danger">Delete</button>
			            </form>
			        </td>
                          
                        
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>Aucune commande à afficher.</p>
    <% } %>

</div>

<!-- Ajoutez le lien vers Bootstrap JS et jQuery ici -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>