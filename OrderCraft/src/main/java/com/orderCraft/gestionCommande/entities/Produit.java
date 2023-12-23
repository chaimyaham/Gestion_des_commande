package com.orderCraft.gestionCommande.entities;

import java.math.BigDecimal;

public class Produit {
	private int productId;
	private String product_name;
	private String label;
	private int quantite_in_stock;
	private BigDecimal prix_unitaire;
	
	
	
	
	public Produit(int productId, String product_name, String label, int quantite_in_stock, BigDecimal prix_unitaire) {
		super();
		this.productId = productId;
		this.product_name = product_name;
		this.label = label;
		this.quantite_in_stock = quantite_in_stock;
		this.prix_unitaire = prix_unitaire;
	}
	public Produit(String product_name, String label, int quantite_in_stock, BigDecimal prix_unitaire) {
		super();
		this.product_name = product_name;
		this.label = label;
		this.quantite_in_stock = quantite_in_stock;
		this.prix_unitaire = prix_unitaire;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getQuantite_in_stock() {
		return quantite_in_stock;
	}
	public void setQuantite_in_stock(int quantite_in_stock) {
		this.quantite_in_stock = quantite_in_stock;
	}
	public BigDecimal getPrix_unitaire() {
		return prix_unitaire;
	}
	public void setPrix_unitaire(BigDecimal prix_unitaire) {
		this.prix_unitaire = prix_unitaire;
	}
	@Override
	public String toString() {
		return "Produit [productId=" + productId + ", product_name=" + product_name + ", label=" + label
				+ ", quantite_in_stock=" + quantite_in_stock + ", prix_unitaire=" + prix_unitaire + "]";
	}
	
	
	
	
	

}
