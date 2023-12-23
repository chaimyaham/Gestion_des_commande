package com.orderCraft.gestionCommande.dao.daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.ConnexionBDD;
import com.orderCraft.gestionCommande.dao.ProduitDAO;
import com.orderCraft.gestionCommande.entities.Produit;

public class ProduitDAOImp implements ProduitDAO {
	
	private static final Logger logger = Logger.getLogger(ClientDAOImp.class.getName());
	
	   Connection connection;
	   
	   public ProduitDAOImp() {
		   this.connection = ConnexionBDD.getConnectionDB();
	       
	    }

	@Override
	public List<Produit> getAllProductt() {
		 List<Produit> produits = new ArrayList<>();

	        try {
	    
	            String sql = "SELECT * FROM `Produit`";

	            Statement statement = connection.createStatement();

	            ResultSet resultSet = statement.executeQuery(sql);

	            while (resultSet.next()) {
            		 	
	            	int product_id=resultSet.getInt("product_id");
	            	String product_name=resultSet.getString("product_name");
	            	String label=resultSet.getString("label");
	            	int quantite_in_stock=resultSet.getInt("quantite_in_stock");
	            	BigDecimal prix_unitaire=resultSet.getBigDecimal("prix_unitaire");
	               Produit produit=new Produit(product_id,product_name,label,quantite_in_stock,prix_unitaire);
	                produits.add(produit);
	            }

	            resultSet.close();
	            statement.close();
	      
	        }catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

	        return produits;
	}

	@Override
	public Produit addProduct(Produit produit) {
		String sql="INSERT INTO produit (product_name,label,quantite_in_stock,prix_unitaire) VALUES (?,?,?,?)";
		
		 try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
             preparedStatement.setString(1,produit.getProduct_name());
             preparedStatement.setString(2,produit.getLabel());
             preparedStatement.setInt(3,produit.getQuantite_in_stock());
             preparedStatement.setBigDecimal(4, produit.getPrix_unitaire());

             preparedStatement.executeUpdate();

             try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                 if (generatedKeys.next()) {
                     int generatedClientId = generatedKeys.getInt(1);
                     produit.setProductId(generatedClientId);;
                     logger.info("Produit ajouter avec succès : " + produit);
                     return produit;
                     
                 } else {
                     throw new SQLException("Échec de la création du Produit, aucune ID generee.");
                 }
             }
         }
      catch (SQLException e) {
         throw new RuntimeException("Erreur lors de l'ajout du Produit.", e);
     }
	
	
	}

	@Override
	public Produit updaeProduct(int productID, Produit produit) {
		  String sql = "UPDATE produit SET product_name = ?, label = ?, quantite_in_stock = ?, prix_unitaire=? WHERE product_id = ?";
		  
          try  {
        	  PreparedStatement prepareStatement=connection.prepareStatement(sql);
        	  prepareStatement.setString(1, produit.getProduct_name());
        	  prepareStatement.setString(2, produit.getLabel());
        	  prepareStatement.setInt(3, produit.getQuantite_in_stock());
        	  prepareStatement.setBigDecimal(4, produit.getPrix_unitaire());
        	  prepareStatement.setInt(5, productID);

        	  prepareStatement.executeUpdate();
        		int i = prepareStatement.executeUpdate();
    			if(i == 1) {
    				 logger.info("Produit mis à jour avec succès : " + produit);
    				return getProduitById(productID);
    				
    			}else {
    				  logger.warning("Un problème est survenu lors de la mise à jour du produit.");
    				
    			}
          
      } catch (SQLException e) {
    	  logger.severe("Erreur lors de la mise à jour du produit.");
          throw new RuntimeException("Erreur lors de la mise à jour du produit.", e);
      }

      return null;
	}

	@Override
	public Produit getProduitById(int productID) {
		  String sql = "SELECT * FROM produit WHERE product_id = ?";

          try {
        	  PreparedStatement preparedStatement = connection.prepareStatement(sql);
              preparedStatement.setInt(1, productID);

              try (ResultSet resultSet = preparedStatement.executeQuery()) {
                  if (resultSet.next()) {
                   	int product_id=resultSet.getInt("product_id");
	            	String product_name=resultSet.getString("product_name");
	            	String label=resultSet.getString("label");
	            	int quantite_in_stock=resultSet.getInt("quantite_in_stock");
	            	BigDecimal prix_unitaire=resultSet.getBigDecimal("prix_unitaire");

                      return new Produit(product_id, product_name, label, quantite_in_stock,prix_unitaire);
                  }
              }
          
      } catch (SQLException e) {
    	  
          throw new RuntimeException("Erreur lors de la récupération du Produit par ID.", e);
      }

      return null;
	}
	

	@Override
	public void deleteProduit(int productID) {
        String sql = "DELETE FROM produit WHERE product_id = ?";

        try {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setInt(1, productID);
            preparedStatement.executeUpdate();
            logger.info("Produit deleted Successfully");
        
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la suppression du produit.", e);
    }
		
	}

}
