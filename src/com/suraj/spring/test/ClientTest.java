package com.suraj.spring.test;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suraj.spring.model.Employee;
import com.suraj.spring.service.EmployeeService;
import com.suraj.spring.service.impl.EmployeeServiceImpl;

public class ClientTest {

	public static void main(String[] args) {

		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		EmployeeService employeeService = context.getBean("employeeService", EmployeeServiceImpl.class);
		//createEmployee(employeeService);
		
		//employeeService.deleteEmployeeById(2);
		
		//employeeService.updateEmployeeEmailById("surajtech003@gmail.com", 3);
		
		//Employee employee  = employeeService.getEmployeeById(3);
		
		//System.out.println(employee.getEmployeeId()+"\t"+employee.getEmpoyeeName()+"\t"+employee.getSalary()+"\t"+employee.getEmail()+"\t"+employee.getDeptNum());
		
		List<Employee> list = employeeService.getAllEmployees();
		for (Employee employee : list) {
			System.out.println(employee.getEmployeeId()+"\t"+employee.getEmpoyeeName()+"\t"+employee.getSalary()+"\t"+employee.getEmail()+"\t"+employee.getDeptNum());
		}
		context.close();
	}

	private static void createEmployee(EmployeeService employeeService) {
		Employee employee = new Employee();
		employee.setEmpoyeeName("Raja");
		employee.setEmail("raj33@gmail.com");
		employee.setSalary(36000);
		employee.setDeptNum(10);
		employeeService.createEmployee(employee);
	}

}
