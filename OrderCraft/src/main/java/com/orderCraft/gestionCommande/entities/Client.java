package com.orderCraft.gestionCommande.entities;

public class Client {
	private int clientId;
	private String firstName;
	private String lastName;
	private String address;
	public Client( String firstName, String lastName, String address) {
		
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	
	public Client(int clientId, String firstName, String lastName, String address) {
	
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + "]";
	}
	
	

}
