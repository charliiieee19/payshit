package control;

import java.sql.*;
import java.util.*;

import model.Employee;

public class EmpLoginCtrl {
	private List<Employee> empList;
	private Employee user;
	private ResultSet rs;
	private Connection connection;
	static Integer recordPointer = 0;

	private String loginUser = "";

	public EmpLoginCtrl(Connection connection, String loginUser) {

		this.loginUser = loginUser;
		this.connection = connection;
		initializeList();
	}

	public void initializeList() {
		try {
			empList = new ArrayList<Employee>();
			PreparedStatement s = null;
			String query;
			query = "SELECT * FROM Emp";

			s = connection.prepareStatement(query);
			rs = s.executeQuery();

			while (rs.next()) {
				user = new Employee();

				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));

				empList.add(user);

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

	public Employee getUser(Employee userLogin) {
		Employee userToReturn = new Employee();
		userToReturn = null;
		for (Employee user : empList) {
			if (user.getUsername().equals(userLogin.getUsername())
					&& user.getPassword().equals(userLogin.getPassword())) {
				userToReturn = user;
				break;
			}

		}

		return userToReturn;
	}

}
