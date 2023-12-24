package com.orderCraft.gestionCommande.servlets.clientController;

import java.io.IOException;
import java.util.List;

import com.orderCraft.gestionCommande.dao.ClientDAO;
import com.orderCraft.gestionCommande.dao.daoImpl.ClientDAOImp;
import com.orderCraft.gestionCommande.entities.Client;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AcceuilClient
 */
public class AcceuilClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceuilClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientDAO clientDAO = new ClientDAOImp();

    	List<Client> listesDesClient=clientDAO.getAllClients(); 
    	request.setAttribute("clients", listesDesClient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/clientViews/acceuilClient.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
