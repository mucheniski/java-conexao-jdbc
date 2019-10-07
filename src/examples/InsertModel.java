package examples;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DBConnection;

public class InsertModel {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBConnection.getConnection();
			
			String sql = " INSERT INTO coursejdbc.seller " + 
						 " (Name, Email, BirthDate, BaseSalary, DepartmentId) " + 
						 " VALUES(?, ?, ?, ?, ?) ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, "Nome JDBC");
			preparedStatement.setString(2, "jdbc@teste.com");
			preparedStatement.setDate(3, new Date(sdf.parse("27/12/1986").getTime()));
			preparedStatement.setDouble(4, 10000.0);
			preparedStatement.setInt(5, 4);
			
			int rowsUpdated = preparedStatement.executeUpdate();
			
			System.out.println("Done! Rows Updated: " + rowsUpdated);			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}		
		finally {
			DBConnection.closeStatement(preparedStatement);
			DBConnection.closeConnection();
		}

	}

}
