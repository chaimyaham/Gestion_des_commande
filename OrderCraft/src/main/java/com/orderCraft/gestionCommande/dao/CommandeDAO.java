package com.orderCraft.gestionCommande.dao;

import java.util.List;

import com.orderCraft.gestionCommande.entities.Commande;

public interface CommandeDAO {
	List<Commande> getAllCommandes();
	Commande getCommandeById(String commandeId);
	Commande updateCommande(String commandeId,Commande commande);
	void deleteCommande(String commandeId);
	Commande addCommande(Commande commande);
	

}
