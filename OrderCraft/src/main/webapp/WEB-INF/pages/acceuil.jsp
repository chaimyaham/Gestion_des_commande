<%@ page import="java.util.List" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Commande" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Produit" %>
<%@ page import="com.orderCraft.gestionCommande.entities.Client" %>

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
    	<input class="btn btn-outline-success mx-2" type="submit"  value="ajouter une commande" >
    </form>
    <form action="${pageContext.request.contextPath}/AcceuilProduct" method="get">
    	<input class="btn btn-outline-info mx-2" type="submit"  value="voir liste des products" >
    </form>
     <form action="${pageContext.request.contextPath}/AcceuilClient" method="get">
    	<input class="btn btn-outline-dark mx-2" type="submit"  value="voir la liste des clients" >
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
                    <th>Client</th>
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
                        <td>
						    <% String statutClass = "";
						    switch (commande.getStatut().getLibelle()) {
						        case "En_cours":
						            statutClass = "text-success";
						            break;
						        case "Terminee":
						            statutClass = "text-info";
						            break;
						        case "Annulee":
						            statutClass = "text-danger";
						            break;
						        default:
						            statutClass = ""; // Ajustez la classe par défaut selon vos besoins
						            break;
						    } %>
						    <span class="<%= statutClass %>"><%= commande.getStatut().getLibelle() %></span>
						</td>

                        <td><%= commande.getCommande_description() %></td>
                        <td>
                        	 <button type="button" class="btn-sm btn-outline-dark" data-toggle="modal" data-target="#clientModal<%= commande.getCommandeId() %>">
                                infos du client
                            </button>

                           
                            <div class="modal fade" id="clientModal<%= commande.getCommandeId() %>" tabindex="-1" role="dialog" aria-labelledby="clientModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="clientModalLabel">Info client</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <ul>
									<% Client client = commande.getClient(); %>
											<% if (client != null) { %>
											    <ul>
											      
											            <li><strong>Nome du client :</strong> <%= client.getFirstName() %>, <%= client.getLastName() %></li>
											            
											            <li><strong>Address du client :</strong> <%= client.getAddress() %></li>
											        
											    </ul>
											<% } else { %>
											    <p>Aucun Client associé à cette commande.</p>
											<% } %>
									       </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        
                        </td>
                        
                        <td>
                            
                            <button type="button" class="btn-sm btn-outline-primary" data-toggle="modal" data-target="#produitsModal<%= commande.getCommandeId() %>">
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
                          <td class=""><%= commande.getPrixTotal() %>  MAD </td>
				            <td class="d-flex justify-content-between">
							    <div class="col-md-6">
							        <form action="${pageContext.request.contextPath}/UpdateCommande" method="get">
							            <input type="hidden" name="commandeId" value="<%= commande.getCommandeId() %>">
							            <button type="submit" class="btn-sm btn-outline-dark">Update</button>
							        </form>
							    </div>
							    <div class="col-md-6 ml-2">
							        <form id="deleteForm" action="${pageContext.request.contextPath}/DeleteCommande" method="post">
							            <input type="hidden" name="commandeId" value="<%= commande.getCommandeId() %>">
							            <button type="button" class="btn-sm btn-outline-danger" onclick="confirmDelete()">Delete</button>
							        </form>
							    </div>
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
<script>
	function confirmDelete() {
	    var confirmation = confirm("Êtes-vous sûr de vouloir supprimer cette commande ?");
	    if (confirmation) {
	        document.getElementById('deleteForm').submit();
	    }
}
</script>

</body>
</html>