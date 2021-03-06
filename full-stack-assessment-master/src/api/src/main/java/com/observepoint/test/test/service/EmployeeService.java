package com.observepoint.test.test.service;

import java.util.List;

import com.observepoint.test.test.domain.Employee;

public interface EmployeeService {
	public Employee getEmployeeById(long empId);
	public List<Employee> getAllEmployees();
	public List<Employee> getEmployeeByDept(long deptId);
	public Employee saveEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public void deleteEmpployee(long empId);
	public Employee getEmployeeByName(String lastName);
	
}
