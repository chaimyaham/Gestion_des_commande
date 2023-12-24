package com.orderCraft.gestionCommande.dao.daoImpl;

import java.text.ParseException;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.ClientDAO;
import com.orderCraft.gestionCommande.dao.CommandeDAO;
import com.orderCraft.gestionCommande.dao.ProduitDAO;

public class TestDao {
	private static final Logger logger = Logger.getLogger(TestDao.class.getName());


	public static void main(String[] args) throws ParseException {
	  CommandeDAO commandeDAO = new CommandeDAOImp();
		ClientDAO cm=new ClientDAOImp();
//		
		ProduitDAO pm= new ProduitDAOImp();
//
//        List<Commande> commandes = commandeDAO.getAllCommandes();
//

//
//        for (Commande commande : commandes) {
//	            System.out.println(commande);
//     }
//	  Client client=new Client(1,"new firstname","new lasteame ","new address");
//		  HashMap<Integer, Integer> produitsEtQuantites = new HashMap<>();
//		  produitsEtQuantites.put(1, 5); 
//		  produitsEtQuantites.put(2, 3);
//		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//		     String dateStr = "2024-01-01";
//
//	            // Conversion de la chaîne en objet Date
//	            Date date = dateFormat.parse(dateStr);
//	
//       Commande commande=new Commande(client,date, StatutCommande.EN_COURS,"test updateeed", produitsEtQuantites);
//
//	       
//	       System.out.println( commandeDAO.updateCommande("CMD2", commande));
//	    
//
//	  commandeDAO.deleteCommande("CMD1");
//		  	        List<Client> clients = cm.getAllClients();
//		  
//
//		  		          for (Client cl : clients) {
//		  	            System.out.println(cl);
//	         }
		
//		Client client =new Client("testing","testing","address Testing");
//	cm.delete(6);
		  
//		   List<Produit> products = pm.getAllProductt();
//	          for (Produit cl : products) {
//	  	            System.out.println(cl);
//       }
//		   
//		Produit produit=new Produit("hi again","hello",195);
	System.out.print(pm.getProduitById(3));
		
//		pm.deleteProduit(2);
		
	}
	
	
		

}
