package com.orderCraft.gestionCommande.entities;

public class Utilisateur {
	private int id;
	private String nom_utilisateur;
	private String password;
	
	
	public Utilisateur(String nom_utilisateur, String password) {
		this.nom_utilisateur = nom_utilisateur;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	
	public String getNom_utilisateur() {
		return nom_utilisateur;
	}
	public void setNom_utilisateur(String nom_utilisateur) {
		this.nom_utilisateur = nom_utilisateur;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Utilisateur [nom_utilisateur=" + nom_utilisateur + ", password=" + password + "]";
	}
	
	
	

}
