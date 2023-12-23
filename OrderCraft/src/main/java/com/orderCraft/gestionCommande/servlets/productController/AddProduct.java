package com.orderCraft.gestionCommande.servlets.productController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.ProduitDAO;
import com.orderCraft.gestionCommande.dao.daoImpl.ProduitDAOImp;
import com.orderCraft.gestionCommande.entities.Produit;
import com.orderCraft.gestionCommande.servlets.commandeController.AddCommande;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddProduct
 */
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddCommande.class.getName());
	ProduitDAO produitDAO = new ProduitDAOImp();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	     RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/productViews/ajouterProduit.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("productName");
		String label = request.getParameter("label");
		int quantityInStock = Integer.parseInt(request.getParameter("quantityInStock"));
		BigDecimal unitPrice = new BigDecimal(request.getParameter("unitPrice"));
	    Produit newProduct = new Produit(productName, label, quantityInStock, unitPrice);
	    Produit addedProduct = produitDAO.addProduct(newProduct);
	    if(addedProduct != null) {
	    	  logger.info("Produit ajouter avec succès : " + addedProduct);
	    	  response.sendRedirect(request.getContextPath() + "/AcceuilProduct");
	    }else {
	        
	    	 response.sendRedirect(request.getContextPath() + "?erreur=1");
	    }
		
		
	}

}
