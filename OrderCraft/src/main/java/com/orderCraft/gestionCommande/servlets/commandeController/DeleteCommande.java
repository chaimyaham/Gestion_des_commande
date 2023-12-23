package com.orderCraft.gestionCommande.servlets.commandeController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.CommandeDAO;
import com.orderCraft.gestionCommande.dao.daoImpl.CommandeDAOImp;


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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String commandeID= request.getParameter("commandeId");
		commandeDAO.deleteCommande(commandeID);
//		 response.sendRedirect(request.getContextPath() + "/Acceuil");
		   logger.info("Commande supprimée");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/acceuil.jsp");
		    dispatcher.forward(request, response);
	}

}
