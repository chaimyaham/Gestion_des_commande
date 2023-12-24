package com.orderCraft.gestionCommande.servlets.clientController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.ClientDAO;
import com.orderCraft.gestionCommande.dao.daoImpl.ClientDAOImp;
import com.orderCraft.gestionCommande.entities.Client;
import com.orderCraft.gestionCommande.entities.Produit;
import com.orderCraft.gestionCommande.servlets.commandeController.AddCommande;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateClient
 */
public class UpdateClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddCommande.class.getName());
	ClientDAO clientDAO = new ClientDAOImp();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientId= request.getParameter("clientId");
		Client client=clientDAO.getClientByID(Integer.parseInt(clientId));
		request.setAttribute("client", client);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/clientViews/updateClient.jsp");
		     dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int clientId = Integer.parseInt(request.getParameter("clientId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
	    Client newClient = new Client(firstName, lastName, address);
	    Client updateclient = clientDAO.update(clientId, newClient);
	    if(updateclient != null) {
	    	  logger.info("client modifier avec succès : " + updateclient);
	    	  response.sendRedirect(request.getContextPath() + "/AcceuilClient");
	    }else {
	        
	    	 response.sendRedirect(request.getContextPath() + "?erreur=1");
	    }
	}

}
