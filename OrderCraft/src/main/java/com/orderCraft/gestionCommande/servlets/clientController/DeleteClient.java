package com.orderCraft.gestionCommande.servlets.clientController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.ClientDAO;
import com.orderCraft.gestionCommande.dao.daoImpl.ClientDAOImp;
import com.orderCraft.gestionCommande.entities.Client;
import com.orderCraft.gestionCommande.entities.Produit;
import com.orderCraft.gestionCommande.servlets.commandeController.AddCommande;

/**
 * Servlet implementation class DeleteClient
 */
public class DeleteClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddCommande.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientDAO clientDAO = new ClientDAOImp();
		int clientId = Integer.parseInt(request.getParameter("clientId"));
		clientDAO.delete(clientId);
		   logger.info("Client supprimée");

	    	List<Client> listesDesClient=clientDAO.getAllClients(); 
	    	request.setAttribute("clients", listesDesClient);
		 response.sendRedirect(request.getContextPath() + "/AcceuilClient");
	}

}
