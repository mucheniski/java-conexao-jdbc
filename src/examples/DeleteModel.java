package examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBConnection;
import db.DBIntegrityException;

public class DeleteModel {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBConnection.getConnection();
			
			String sql = "DELETE FROM coursejdbc.department " + 
						 "WHERE Id = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, 2);
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAffected);
			
		} 
		catch (SQLException e) {
			throw new DBIntegrityException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(preparedStatement);
			DBConnection.closeConnection();
		}

	}

}
