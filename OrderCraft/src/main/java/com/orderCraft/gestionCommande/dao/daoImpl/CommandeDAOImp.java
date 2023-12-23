package com.orderCraft.gestionCommande.dao.daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.CommandeDAO;
import com.orderCraft.gestionCommande.dao.ConnexionBDD;
import com.orderCraft.gestionCommande.entities.Client;
import com.orderCraft.gestionCommande.entities.Commande;
import com.orderCraft.gestionCommande.entities.Produit;
import com.orderCraft.gestionCommande.entities.StatutCommande;

public class CommandeDAOImp implements CommandeDAO{

	Connection connection;
	private static final Logger logger = Logger.getLogger(CommandeDAOImp.class.getName());

       public CommandeDAOImp() {
        this.connection = ConnexionBDD.getConnectionDB();
       
    }


	@Override
	public List<Commande> getAllCommandes() {
        List<Commande> commandes = new ArrayList<>();

        try {
    
            String sql = "SELECT c.commande_id, c.client_id, c.commande_description, cl.first_name AS client_first_name, cl.last_name AS client_last_name, cl.address AS client_address, c.date_de_commande, c.status, SUM(cp.quantite * p.prix_unitaire) AS prix_final FROM commande c JOIN client cl ON c.client_id = cl.client_id JOIN commande_produit cp ON c.commande_id = cp.commande_id JOIN produit p ON cp.product_id = p.product_id GROUP BY c.commande_id, c.client_id, c.commande_description, cl.first_name, cl.last_name, cl.address, c.date_de_commande, c.status;";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
               Commande commande=generateComandeFormResult(resultSet);
                commandes.add(commande);
            }

            resultSet.close();
            statement.close();
      
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return commandes;
    }
	
	private HashMap<Produit, Integer> getProduitsEtQuantitesPourCommande(String commandeId) {
	        HashMap<Produit, Integer> produitsQuantites = new HashMap<>();

	        try {
	          

	            String sql = "SELECT p.product_id, p.product_name, p.label, p.quantite_in_stock,p.prix_unitaire, cp.quantite FROM commande_produit cp JOIN produit p ON cp.product_id = p.product_id WHERE cp.commande_id = ?;";	           
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, commandeId);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                int productId = resultSet.getInt("product_id");
	                String product_name=resultSet.getString("product_name");
	                String label=resultSet.getString("label");
	                int quantite_in_stock = resultSet.getInt("quantite_in_stock");
	                BigDecimal prix_unitaire=resultSet.getBigDecimal("prix_unitaire");
	                int quantite = resultSet.getInt("quantite");
	                Produit newProduit= new Produit(productId,product_name,label,quantite_in_stock,prix_unitaire);
	                produitsQuantites.put(newProduit, quantite);
	            }

	            resultSet.close();
	            preparedStatement.close();
	      
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }

	        return produitsQuantites;
	    }

    private Commande generateComandeFormResult(ResultSet  resultset) throws SQLException  {
    	
    	 String commandeId = resultset.getString("commande_id");
         int clientId = resultset.getInt("client_id");
         Date dateDeCommande = resultset.getDate("date_de_commande");
         String statutString = resultset.getString("status").toUpperCase();
         StatutCommande statut = StatutCommande.valueOf(statutString);
         String commandeDescription = resultset.getString("commande_description");
    	 String client_first_name = resultset.getString("client_first_name");
    	 String client_last_name = resultset.getString("client_last_name");
    	 String client_address = resultset.getString("client_address");
    	 BigDecimal prix_final= resultset.getBigDecimal("prix_final");
         HashMap<Produit, Integer> listeDesProduitsEtLeursQuantite = getProduitsEtQuantitesPourCommande(commandeId);
         Client client=new Client(clientId,client_first_name,client_last_name,client_address);

         Commande commande = new Commande(commandeId,client , dateDeCommande, statut, commandeDescription, listeDesProduitsEtLeursQuantite,prix_final);

    	return commande;
    	
    }
    
    @Override
	public Commande getCommandeById(String commandeId) {
    	String sql="SELECT c.commande_id, c.client_id, c.commande_description, cl.first_name AS client_first_name, cl.last_name AS client_last_name, cl.address AS client_address, c.date_de_commande, c.status, SUM(cp.quantite * p.prix_unitaire) AS prix_final FROM commande c JOIN client cl ON c.client_id = cl.client_id JOIN commande_produit cp ON c.commande_id = cp.commande_id JOIN produit p ON cp.product_id = p.product_id WHERE c.commande_id = ? GROUP BY c.commande_id, c.client_id, c.commande_description, cl.first_name, cl.last_name, cl.address, c.date_de_commande, c.status;";
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, commandeId);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
            	 Commande commande= generateComandeFormResult(resultSet);
            	 return commande;

            }
   
			
		}catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return null;
	}

	@Override
	public Commande updateCommande(String commandeId,Commande commande) {
		try {
			String sql="UPDATE commande SET client_id = ?, status = ?, date_de_commande=?, commande_description = ? WHERE commande_id = ?";
			PreparedStatement prepareStatement=connection.prepareStatement(sql);
			
			prepareStatement.setInt(1, commande.getClient().getClientId());
			prepareStatement.setString(2, commande.getStatut().getLibelle());
			prepareStatement.setDate(3, new java.sql.Date(commande.getDateDeCommande().getTime()));
			prepareStatement.setString(4, commande.getCommande_description());
			prepareStatement.setString(5, commandeId);
			
			int i = prepareStatement.executeUpdate();
			if(i == 1) {
				logger.info("Commande mise à jour avec success : " );
				return getCommandeById(commandeId);
				
			}else {
				logger.warning("Un problème est survenu lors de la mise à jour de la commande.");
            
				
			}
	
			
		}catch (SQLException e) {
			 logger.severe("Erreur lors de la mise à jour de la commande.");
            throw new RuntimeException(e);
        }
	return null;
	}

	@Override
	public Commande addCommande(Commande commande) {
		

         String sql = "INSERT INTO commande (commande_id, client_id, date_de_commande, status, commande_description) VALUES (?, ?, ?, ?, ?)";

         try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
             preparedStatement.setString(1, commande.getCommandeId());
             preparedStatement.setInt(2, commande.getClient().getClientId());
             preparedStatement.setDate(3, new java.sql.Date(commande.getDateDeCommande().getTime()));
             preparedStatement.setString(4, commande.getStatut().getLibelle());
             preparedStatement.setString(5, commande.getCommande_description());
            
         
         	int i = preparedStatement.executeUpdate();
             if(i == 1) {
 				
            	 logger.info("Commande ajoute avec success : " );
            	 insererCommandeToCommadeProduct(commande);
 				return commande;
 				
 			}else {
 				logger.warning("Un problème est survenu lors de la mise à jour de la commande.");
 			
 				
 			}

         
     } catch (SQLException e) {
    	 logger.severe("Erreur lors de l ajout de la commande.");
         throw new RuntimeException("Erreur lors de l'insertion de la commande.", e);
     }
         return null;
	}


	private void insererCommandeToCommadeProduct(Commande commande) {
		String query = "INSERT INTO commande_produit (commande_id, product_id, quantite) VALUES (?, ?, ?);";
		HashMap<Produit, Integer> produitsEtQuantites =commande.getListeDesProduitsEtLeursQuantite();
		try (PreparedStatement ps = connection.prepareStatement(query)) {

		    for (HashMap.Entry<Produit, Integer> entry : produitsEtQuantites.entrySet()) {
		        Produit produit = entry.getKey();
		        Integer quantite = entry.getValue();

		        ps.setString(1, commande.getCommandeId());
		        ps.setInt(2, produit.getProductId());
		        ps.setInt(3, quantite);
		        ps.executeUpdate();
		        
		    }

		} catch (SQLException e) {
		    e.printStackTrace();
		}	
	}





	@Override
	  public void deleteCommande(String commandeId) {
        
		String sql = "DELETE FROM commande WHERE commande_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {    
        	
                preparedStatement.setString(1, commandeId);
                int i= preparedStatement.executeUpdate();
                if(i == 1) {	
                	logger.info("Commande supprimer avec success : " );
   
     			}else {
     				logger.info("un probleme est survenu" );
     			
     				
     			}
             
                
            
        } catch (SQLException e) {
        	logger.severe("Erreur lors de la mise à jour de la commande.");
            throw new RuntimeException("Erreur lors de la suppression de la commande.", e);
        }
    }


}
