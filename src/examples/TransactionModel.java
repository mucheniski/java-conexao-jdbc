package examples;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;
import db.DBException;

public class TransactionModel {

	public static void main(String[] args) {
		
		Connection connection = null;		
		Statement statement = null;
		
		try {
			connection = DBConnection.getConnection();	
			connection.setAutoCommit(false); // Para garantir que a transação seja atomica
			
			statement = connection.createStatement();	
			
			int rowsUpdated1 = statement.executeUpdate("UPDATE seller SET BaseSalary = 3000.0 WHERE DepartmentId = 1");
			
			// Erro intencional para validar se a transacao efetua o Rollback
//			int test = 1;
//			if (test < 2) {
//				throw new SQLException("Fake Error!");
//			}
			
			int rowsUpdated2 = statement.executeUpdate("UPDATE seller SET BaseSalary = 4000.0 WHERE DepartmentId = 2");
			
			connection.commit();
			
			System.out.println("Done! rowsUpdated1: " + rowsUpdated1);
			System.out.println("Done! rowsUpdated2: " + rowsUpdated2);
			
		} 
		catch (SQLException e) {
			try {
				connection.rollback();
				throw new DBException("Transaction Rolled Back! Cause By: " + e.getMessage());
			} 
			catch (SQLException e1) {
				throw new DBException("Error tryung to Rollback! Cause By: " + e1.getMessage());
			}
		}
		finally {
			DBConnection.closeStatement(statement);
			DBConnection.closeConnection();
		}

	}

}
