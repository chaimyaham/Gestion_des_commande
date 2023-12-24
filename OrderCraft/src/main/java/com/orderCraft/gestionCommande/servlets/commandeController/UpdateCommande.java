package com.orderCraft.gestionCommande.servlets.commandeController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.ClientDAO;
import com.orderCraft.gestionCommande.dao.CommandeDAO;
import com.orderCraft.gestionCommande.dao.ProduitDAO;
import com.orderCraft.gestionCommande.dao.daoImpl.ClientDAOImp;
import com.orderCraft.gestionCommande.dao.daoImpl.CommandeDAOImp;
import com.orderCraft.gestionCommande.dao.daoImpl.ProduitDAOImp;
import com.orderCraft.gestionCommande.entities.Client;
import com.orderCraft.gestionCommande.entities.Commande;
import com.orderCraft.gestionCommande.entities.Produit;
import com.orderCraft.gestionCommande.entities.StatutCommande;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateCommande
 */
public class UpdateCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClientDAO clientDAO = new ClientDAOImp();
	CommandeDAO commandeDAO =new CommandeDAOImp();
	private static final Logger logger = Logger.getLogger(AddCommande.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandeID= request.getParameter("commandeId");
		Commande commande=commandeDAO.getCommandeById(commandeID);
		
	     List<Client> clients = clientDAO.getAllClients();
	        
	        ProduitDAO produitDAO = new ProduitDAOImp();
	        List<Produit> produits = produitDAO.getAllProductt();
	        request.setAttribute("commande", commande);
	        request.setAttribute("clients", clients);
	        request.setAttribute("produits", produits);
		
		
		
	     RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/updateCommande.jsp");
	     dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    
	    
	    String commandeIDToUpdate = request.getParameter("commandeIdToUpdate");
	    String[] produits = request.getParameterValues("produit");
	    String[] quantites = request.getParameterValues("quantite");
	    HashMap<Produit, Integer> produitsEtQuantites = new HashMap<>();
	    ProduitDAO produitDAO = new ProduitDAOImp();

	    if (produits != null && quantites != null && produits.length > 0 && quantites.length > 0) {
	    	  
	    	  produitsEtQuantites = new HashMap<>();
	        for (int i = 0; i < produits.length; i++) {
	        
	            int produitId = Integer.parseInt(produits[i]);
	            Produit nouveauProduit = produitDAO.getProduitById(produitId);
	            int quantite = Integer.parseInt(quantites[i]);
	            produitsEtQuantites.put(nouveauProduit, quantite);
	        }
	    } else {
	    	  logger.info("on the else");
	        Commande commandeExistante = commandeDAO.getCommandeById(commandeIDToUpdate);
	        produitsEtQuantites = commandeExistante.getListeDesProduitsEtLeursQuantite();
	    }

	    String selectedClient = request.getParameter("client");
	    String selectedStatus = request.getParameter("status");
	    String commande_description = request.getParameter("commande_description");
	    String dateDeCommande = request.getParameter("dateDeCommande");

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String dateStr = dateDeCommande;
	    Date date;

	    try {
	        date = dateFormat.parse(dateStr);
	    } catch (ParseException e) {
	        date = null;
	        e.printStackTrace();
	    }

	    Client myClient = clientDAO.getClientByID(Integer.parseInt(selectedClient));

	    Commande newCommande = new Commande(myClient, date, StatutCommande.valueOf(selectedStatus.toUpperCase()), commande_description, produitsEtQuantites);

	    Commande newc = commandeDAO.updateCommande(commandeIDToUpdate, newCommande);
	    logger.info("Commande mise à jour avec succès : " + newc);

	    List<Commande> listeDesCommande = commandeDAO.getAllCommandes();
	    request.setAttribute("commandes", listeDesCommande);
	    logger.info("Liste des commandes récupérée avec succès : " + listeDesCommande);
	    response.sendRedirect(request.getContextPath() + "/Login");

		
	}

}
