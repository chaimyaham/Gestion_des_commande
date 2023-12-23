package com.orderCraft.gestionCommande.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexionBDD {
	
	    private static final String URL = "jdbc:mysql://localhost:3306/gestion_commande";
	    private static final String UTILISATEUR = "root";
	    private static final String PASSWORD = "";
	    private static Connection connection = null;

	    public static Connection getConnectionDB() {
	        if(connection == null){
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                connection = DriverManager.getConnection(URL,UTILISATEUR,PASSWORD	);
	            } catch (ClassNotFoundException | SQLException e) {
	                System.out.println("Error In DB");
	                throw new RuntimeException(e);

	            }

	        }
	        return  connection;
	    }

	    public static void fermerConnexion(Connection connexion) {
	        try {
	            if (connexion != null && !connexion.isClosed()) {
	                connexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }
	    }
	
	

}
