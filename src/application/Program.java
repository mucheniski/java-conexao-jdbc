package application;

import java.sql.Connection;

import db.DBConnection;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = DBConnection.getConnection();
		DBConnection.closeConnection();
		
	}
	
}
