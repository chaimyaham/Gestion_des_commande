package com.orderCraft.gestionCommande.servlets.productController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.ProduitDAO;
import com.orderCraft.gestionCommande.dao.daoImpl.ProduitDAOImp;
import com.orderCraft.gestionCommande.entities.Commande;
import com.orderCraft.gestionCommande.entities.Produit;
import com.orderCraft.gestionCommande.servlets.commandeController.AddCommande;

/**
 * Servlet implementation class DeleteProduct
 */
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddCommande.class.getName());
	 ProduitDAO produitDAO=new ProduitDAOImp();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
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
		int productId = Integer.parseInt(request.getParameter("productId"));
		produitDAO.deleteProduit(productId);
		   logger.info("Produit supprimée");
		  	List<Produit> listesDesProduit=produitDAO.getAllProductt(); 
	    	request.setAttribute("produits", listesDesProduit);
		 response.sendRedirect(request.getContextPath() + "/AcceuilProduct");
		
		
	}

}
