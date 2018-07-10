package com.suraj.spring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.suraj.spring.dao.EmployeeDao;
import com.suraj.spring.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void createEmployee(Employee employee) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dataSource.getConnection();
			String SQL = "INSERT INTO employee_table(employee_name,email,sal,dept_no) VALUES(?,?,?,?);";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, employee.getEmpoyeeName());
			pstmt.setString(2, employee.getEmail());
			pstmt.setDouble(3, employee.getSalary());
			pstmt.setInt(4, employee.getDeptNum());

			int update = pstmt.executeUpdate();
			if (update > 0) {
				System.out.println("records inserted successfully...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Employee employee = null;
		try {
			connection = dataSource.getConnection();
			String SQL = "SELECT * FROM employee_table where employee_id=?";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, employeeId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				employee = new Employee();
				employee.setEmployeeId(resultSet.getInt("employee_id"));
				employee.setEmpoyeeName(resultSet.getString("employee_name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setSalary(resultSet.getDouble("sal"));
				employee.setDeptNum(resultSet.getInt("dept_no"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				resultSet.close();
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		Employee employee = null;
		try {
			connection = dataSource.getConnection();
			String SQL = "SELECT * FROM employee_table";
			stmt = connection.createStatement();
			resultSet = stmt.executeQuery(SQL);
			while (resultSet.next()) {
				employee = new Employee();
				employee.setEmployeeId(resultSet.getInt("employee_id"));
				employee.setEmpoyeeName(resultSet.getString("employee_name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setSalary(resultSet.getDouble("sal"));
				employee.setDeptNum(resultSet.getInt("dept_no"));
				list.add(employee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				resultSet.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

	@Override
	public void updateEmployeeEmailById(String email, int employeeId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dataSource.getConnection();
			String SQL = "UPDATE employee_table set email = ? WHERE employee_id=?";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, email);
			pstmt.setInt(2, employeeId);
			int updateRecord = pstmt.executeUpdate();
			if (updateRecord > 0) {
				System.out.println("record updated successfully with id::"
						+ employeeId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dataSource.getConnection();
			String SQL = "DELETE FROM employee_table where employee_id=?";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, employeeId);
			int deleteRecord = pstmt.executeUpdate();
			if (deleteRecord > 0) {
				System.out.println("record deleted successfully with id::"
						+ employeeId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
