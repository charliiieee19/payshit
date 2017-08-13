package control;

import java.sql.*;
import java.util.*;


import model.Admin;

public class AdminLoginCtrl {
	private List<Admin> adminList;
	private Admin user;
	private ResultSet rs;
	private Connection connection;
	static Integer recordPointer = 0;

	private String loginUser = "";

	public AdminLoginCtrl(Connection connection, String loginUser) {

		this.loginUser = loginUser;
		this.connection = connection;
		initializeList();
	}

	public void initializeList() {
		try {
			adminList = new ArrayList<Admin>();
			PreparedStatement s = null;
			String query;
			query = "SELECT * FROM admin";

			s = connection.prepareStatement(query);
			rs = s.executeQuery();

			while (rs.next()) {
				user = new Admin();

				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));

				adminList.add(user);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public Admin getUser(Admin userLogin)
	{
		Admin userToReturn = new Admin();
		userToReturn = null;
		for (Admin user: adminList)
		{
			if(user.getUsername().equals(userLogin.getUsername()) &&
			   user.getPassword().equals(userLogin.getPassword()))
			{	
				userToReturn = user;
				break;
			}
			
		}
		
		return userToReturn;
	}

}
