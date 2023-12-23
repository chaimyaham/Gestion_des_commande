package com.orderCraft.gestionCommande.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import com.orderCraft.gestionCommande.utils.Utils;

public class Commande {
	
	private String commandeId;
    private Client client;
    private Date dateDeCommande;
    private StatutCommande statut;
    private String commande_description ;
    private HashMap<Produit , Integer > listeDesProduitsEtLeursQuantite;
    private BigDecimal prixTotal;
    
    
    
    
    
	public Commande(Client client, Date dateDeCommande, StatutCommande statut, String commande_description,
			HashMap<Produit, Integer> listeDesProduitsEtLeursQuantite) {
		super();
		   this.commandeId = Utils.GenerateId();
		this.client = client;
		this.dateDeCommande = dateDeCommande;
		this.statut = statut;
		this.commande_description = commande_description;
		this.listeDesProduitsEtLeursQuantite = listeDesProduitsEtLeursQuantite;
	}
	public Commande(String commandeId, Client client, Date dateDeCommande, StatutCommande statut,
			String commande_description, HashMap<Produit, Integer> listeDesProduitsEtLeursQuantite,
			BigDecimal prixTotal) {
		super();
		this.commandeId = commandeId;
		this.client = client;
		this.dateDeCommande = dateDeCommande;
		this.statut = statut;
		this.commande_description = commande_description;
		this.listeDesProduitsEtLeursQuantite = listeDesProduitsEtLeursQuantite;
		this.prixTotal = prixTotal;
	}
	public Commande(Client client, Date dateDeCommande, StatutCommande statut, String commande_description,
			HashMap<Produit, Integer> listeDesProduitsEtLeursQuantite, BigDecimal prixTotal) {
		super();
		   this.commandeId = Utils.GenerateId();
		this.client = client;
		this.dateDeCommande = dateDeCommande;
		this.statut = statut;
		this.commande_description = commande_description;
		this.listeDesProduitsEtLeursQuantite = listeDesProduitsEtLeursQuantite;
		this.prixTotal = prixTotal;
	}
	public String getCommandeId() {
		return commandeId;
	}
	public void setCommandeId(String commandeId) {
		this.commandeId = commandeId;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getDateDeCommande() {
		return dateDeCommande;
	}
	public void setDateDeCommande(Date dateDeCommande) {
		this.dateDeCommande = dateDeCommande;
	}
	public StatutCommande getStatut() {
		return statut;
	}
	public void setStatut(StatutCommande statut) {
		this.statut = statut;
	}
	public String getCommande_description() {
		return commande_description;
	}
	public void setCommande_description(String commande_description) {
		this.commande_description = commande_description;
	}
	public HashMap<Produit, Integer> getListeDesProduitsEtLeursQuantite() {
		return listeDesProduitsEtLeursQuantite;
	}
	public void setListeDesProduitsEtLeursQuantite(HashMap<Produit, Integer> listeDesProduitsEtLeursQuantite) {
		this.listeDesProduitsEtLeursQuantite = listeDesProduitsEtLeursQuantite;
	}
	public BigDecimal getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(BigDecimal prixTotal) {
		this.prixTotal = prixTotal;
	}
	@Override
	public String toString() {
		return "Commande [commandeId=" + commandeId + ", client=" + client + ", dateDeCommande=" + dateDeCommande
				+ ", statut=" + statut + ", commande_description=" + commande_description
				+ ", listeDesProduitsEtLeursQuantite=" + listeDesProduitsEtLeursQuantite + ", prixTotal=" + prixTotal
				+ "]";
	}
    
    
    
    

    
 
	
	
	
	

}
