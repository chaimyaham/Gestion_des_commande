package com.orderCraft.gestionCommande.dao;

import java.util.List;

import com.orderCraft.gestionCommande.entities.Produit;

public interface ProduitDAO {
	
	List<Produit> getAllProductt();
	Produit addProduct(Produit produit);
	Produit updaeProduct(int productID,Produit produit);
	Produit getProduitById(int productID);
	void deleteProduit(int productID);
	
	
	

}
