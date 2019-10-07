package examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBConnection;

public class UpdateModel {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBConnection.getConnection();
			
			String sql = "UPDATE coursejdbc.seller " + 
						 "SET BaseSalary = BaseSalary + ? " + 
						 "WHERE DepartmentId = ? " ;
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, 200.0);
			preparedStatement.setInt(2, 4);
			
			int rowsUpdated = preparedStatement.executeUpdate();
			
			System.out.println("Done! Rows updated: " + rowsUpdated);
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBConnection.closeStatement(preparedStatement);
			DBConnection.closeConnection();
		}

	}

}
