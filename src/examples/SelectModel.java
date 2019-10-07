package examples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;

public class SelectModel {

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		try {
			connection = DBConnection.getConnection();
			statement = connection.createStatement();
			resultset = statement.executeQuery("select * from department");
			
			while (resultset.next()) {
				System.out.println(resultset.getInt("Id") + ", " + resultset.getString("Name"));
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBConnection.closeResultset(resultset);
			DBConnection.closeStatement(statement);
			DBConnection.closeConnection();
		}
		
	}
	
}
