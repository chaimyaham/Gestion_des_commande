package com.orderCraft.gestionCommande.servlets.clientController;

import java.io.IOException;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.ClientDAO;
import com.orderCraft.gestionCommande.dao.daoImpl.ClientDAOImp;
import com.orderCraft.gestionCommande.entities.Client;
import com.orderCraft.gestionCommande.servlets.commandeController.AddCommande;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddClient
 */
public class AddClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddCommande.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/clientViews/ajouterClient.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address=request.getParameter("address");
		ClientDAO clientDAO = new ClientDAOImp();
		Client newClient = new Client(firstName,lastName,address);
		Client addedClient = clientDAO.addClient(newClient);
		
		
		
	    if(addedClient != null) {
	    	  logger.info("Client ajouter avec succès : " + addedClient);
	    	  response.sendRedirect(request.getContextPath() + "/AcceuilClient");
	    }else {
	        
	    	 response.sendRedirect(request.getContextPath() + "?erreur=1");
	    }
	}

}
