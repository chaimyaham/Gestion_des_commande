package com.orderCraft.gestionCommande.dao;

import java.util.List;

import com.orderCraft.gestionCommande.entities.Client;

public interface ClientDAO {
	
	List<Client> getAllClients();
	Client update(int clientId,Client client);
	Client getClientByID(int clientId);
	Client addClient(Client client);
	void delete(int clientId);

}
