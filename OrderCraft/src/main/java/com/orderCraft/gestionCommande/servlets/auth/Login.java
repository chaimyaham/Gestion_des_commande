package com.orderCraft.gestionCommande.servlets.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.orderCraft.gestionCommande.dao.CommandeDAO;
import com.orderCraft.gestionCommande.dao.ConnexionBDD;
import com.orderCraft.gestionCommande.dao.daoImpl.CommandeDAOImp;
import com.orderCraft.gestionCommande.entities.Commande;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
     	CommandeDAO commandeDAO = new CommandeDAOImp();

    	List<Commande> listeDesCommande=commandeDAO.getAllCommandes(); 
       request.setAttribute("commandes", listeDesCommande);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/acceuil.jsp");
        dispatcher.forward(request, response);
    }
   
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String username = request.getParameter("username");
        String password = request.getParameter("password");
        
       
        if(userValidation(username,password)) {
        	
         	CommandeDAO commandeDAO = new CommandeDAOImp();

        	List<Commande> listeDesCommande=commandeDAO.getAllCommandes(); 
           request.setAttribute("commandes", listeDesCommande);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/acceuil.jsp");
            dispatcher.forward(request, response);

        	
        	 
        }
        else {
        	
            response.sendRedirect(request.getContextPath() + "?erreur=1");
            
        }
		
	}
	
	
	protected boolean userValidation (String username,String password) {
			
		 Connection connexion = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = ConnexionBDD.getConnectionDB();
	            String query = "SELECT * FROM `utilisateur` WHERE nom_utilisateur=? AND mot_de_passe=?";
	            preparedStatement = connexion.prepareStatement(query);
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);

	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                return rs.next(); 
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } 
	}

}
