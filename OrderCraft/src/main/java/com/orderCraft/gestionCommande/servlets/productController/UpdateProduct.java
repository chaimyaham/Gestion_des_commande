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
 * Servlet implementation class UpdateProduct
 */
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddCommande.class.getName());
	 ProduitDAO produitDAO=new ProduitDAOImp();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId= request.getParameter("productId");
		Produit produit=produitDAO.getProduitById(Integer.parseInt(productId));
		request.setAttribute("produit", produit);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/productViews/updateProduct.jsp");
		     dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		String productName = request.getParameter("productName");
		String label = request.getParameter("label");
		int quantityInStock = Integer.parseInt(request.getParameter("quantityInStock"));
		BigDecimal unitPrice = new BigDecimal(request.getParameter("unitPrice"));
	    Produit newProduct = new Produit(productName, label, quantityInStock, unitPrice);
	    Produit updateProduct = produitDAO.updaeProduct(productId, newProduct);
	    if(updateProduct != null) {
	    	  logger.info("Produit modifier avec succès : " + updateProduct);
	    	  response.sendRedirect(request.getContextPath() + "/AcceuilProduct");
	    }else {
	        
	    	 response.sendRedirect(request.getContextPath() + "?erreur=1");
	    }
	}

}
