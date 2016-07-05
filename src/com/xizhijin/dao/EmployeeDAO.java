package com.xizhijin.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xizhijin.db.DBConnFactory;
import com.xizhijin.po.Employee;

public class EmployeeDAO {
	Connection connection;
	Statement stmt;
	private int numOfRecords;

	public EmployeeDAO() {
	}

	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = DBConnFactory.getInstance().getConnection();
		return con;
	}

	public List<Employee> viewAllEmployees(int offset, int numOfRecords) {
		String query = "select SQL_CALC_FOUND_ROWS * from employee limit " + offset + ", " + numOfRecords;
		List<Employee> list = new ArrayList<Employee>();
		Employee employee = null;
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				employee = new Employee();
				employee.setEmployeeId(rs.getInt("emp_id"));
				employee.setEmployeeName(rs.getString("emp_name"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setDeptName(rs.getString("dept_name"));
				list.add(employee);
			}
			rs.close();

			rs = stmt.executeQuery("SELECT FOUND_ROWS()");
			if (rs.next()) {
				this.numOfRecords = rs.getInt(1);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int getNumOfRecords() {
		return numOfRecords;
	}
}