package com.orderCraft.gestionCommande.servlets.commandeController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.CommandeDAO;
import com.orderCraft.gestionCommande.dao.daoImpl.CommandeDAOImp;
import com.orderCraft.gestionCommande.entities.Commande;


/**
 * Servlet implementation class DeleteCommande
 */
public class DeleteCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommandeDAO commandeDAO =new CommandeDAOImp();
	private static final Logger logger = Logger.getLogger(DeleteCommande.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommande() {
        super();
        // TODO Auto-generated constructor stub
    }
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at delete: ").append(request.getContextPath());
//	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String commandeID= request.getParameter("commandeId");
		commandeDAO.deleteCommande(commandeID);
//		 response.sendRedirect(request.getContextPath() + "/Acceuil");
		   logger.info("Commande supprimée");
		   List<Commande> listeDesCommande = commandeDAO.getAllCommandes();
		    request.setAttribute("commandes", listeDesCommande);
		 response.sendRedirect(request.getContextPath() + "/Login");
	}

}
