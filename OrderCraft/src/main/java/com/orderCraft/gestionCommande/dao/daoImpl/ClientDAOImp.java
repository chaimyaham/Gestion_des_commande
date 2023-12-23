package com.orderCraft.gestionCommande.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.orderCraft.gestionCommande.dao.ClientDAO;
import com.orderCraft.gestionCommande.dao.ConnexionBDD;
import com.orderCraft.gestionCommande.entities.Client;

public class ClientDAOImp implements ClientDAO  {
	private static final Logger logger = Logger.getLogger(ClientDAOImp.class.getName());
	
	   Connection connection;
	   
	   public ClientDAOImp() {
		   this.connection = ConnexionBDD.getConnectionDB();
	       
	    }

	@Override
	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<>();

        try {
    
            String sql = "SELECT * FROM client";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int clientId = resultSet.getInt("client_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");

                Client client = new Client(clientId, firstName, lastName, address);
                clients.add(client);
            
            }

            resultSet.close();
            statement.close();
      
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clients;
	}

	@Override
	public Client update(int clientId,Client client) {
		  String sql = "UPDATE client SET first_name = ?, last_name = ?, address = ? WHERE client_id = ?";
		  
          try  {
        	  PreparedStatement prepareStatement=connection.prepareStatement(sql);
        	  prepareStatement.setString(1, client.getFirstName());
        	  prepareStatement.setString(2, client.getLastName());
        	  prepareStatement.setString(3, client.getAddress());
        	  prepareStatement.setInt(4, clientId);

        	  prepareStatement.executeUpdate();
        		int i = prepareStatement.executeUpdate();
    			if(i == 1) {
    				 logger.info("Client mis à jour avec succès : " + client);
    				return getClientByID(clientId);
    				
    			}else {
    				  logger.warning("Un problème est survenu lors de la mise à jour du client.");
    				
    			}
          
      } catch (SQLException e) {
    	  logger.severe("Erreur lors de la mise à jour du client.");
          throw new RuntimeException("Erreur lors de la mise à jour du client.", e);
      }

      return null;
	}

	@Override
	public Client addClient(Client client) {
		 String sql = "INSERT INTO client (first_name, last_name, address) VALUES (?, ?, ?)";

         try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
             preparedStatement.setString(1, client.getFirstName());
             preparedStatement.setString(2, client.getLastName());
             preparedStatement.setString(3, client.getAddress());

             preparedStatement.executeUpdate();

             try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                 if (generatedKeys.next()) {
                     int generatedClientId = generatedKeys.getInt(1);
                     client.setClientId(generatedClientId);
                     logger.info("Client ajouter avec succès : " + client);
                     return client;
                     
                 } else {
                     throw new SQLException("Échec de la création du client, aucun ID généré.");
                 }
             }
         }
      catch (SQLException e) {
         throw new RuntimeException("Erreur lors de l'ajout du client.", e);
     }

  
	}

	@Override
	public void delete(int clientId) {
        String sql = "DELETE FROM client WHERE client_id = ?";

        try {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setInt(1, clientId);
            preparedStatement.executeUpdate();
            logger.info("Client deleted Successfully");
        
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la suppression du client.", e);
    }
		
	}

	@Override
	public Client getClientByID(int clientId) {
		  String sql = "SELECT * FROM client WHERE client_id = ?";

          try {
        	  PreparedStatement preparedStatement = connection.prepareStatement(sql);
              preparedStatement.setInt(1, clientId);

              try (ResultSet resultSet = preparedStatement.executeQuery()) {
                  if (resultSet.next()) {
                      String firstName = resultSet.getString("first_name");
                      String lastName = resultSet.getString("last_name");
                      String address = resultSet.getString("address");

                      return new Client(clientId, firstName, lastName, address);
                  }
              }
          
      } catch (SQLException e) {
    	  
          throw new RuntimeException("Erreur lors de la récupération du client par ID.", e);
      }

      return null;
	}

}
