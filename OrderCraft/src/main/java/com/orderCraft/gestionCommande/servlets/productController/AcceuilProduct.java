package com.orderCraft.gestionCommande.servlets.productController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.orderCraft.gestionCommande.dao.CommandeDAO;
import com.orderCraft.gestionCommande.dao.ProduitDAO;
import com.orderCraft.gestionCommande.dao.daoImpl.CommandeDAOImp;
import com.orderCraft.gestionCommande.dao.daoImpl.ProduitDAOImp;
import com.orderCraft.gestionCommande.entities.Client;
import com.orderCraft.gestionCommande.entities.Commande;
import com.orderCraft.gestionCommande.entities.Produit;

/**
 * Servlet implementation class AcceuilProduct
 */
public class AcceuilProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceuilProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
     	ProduitDAO produitDAO = new ProduitDAOImp();

    	List<Produit> listesDesProduit=produitDAO.getAllProductt(); 
    	request.setAttribute("produits", listesDesProduit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/productViews/acceuilProduit.jsp");
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
