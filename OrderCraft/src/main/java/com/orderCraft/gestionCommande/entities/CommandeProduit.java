package com.orderCraft.gestionCommande.entities;

public class CommandeProduit {
	
	    private String commandeId;
	    private int productId;
	    private int quantite;
	    
	    
		public CommandeProduit(String commandeId, int productId, int quantite) {
			
			this.commandeId = commandeId;
			this.productId = productId;
			this.quantite = quantite;
		}
		public String getCommandeId() {
			return commandeId;
		}
		public void setCommandeId(String commandeId) {
			this.commandeId = commandeId;
		}
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public int getQuantite() {
			return quantite;
		}
		public void setQuantite(int quantite) {
			this.quantite = quantite;
		}
	    
	    

}
