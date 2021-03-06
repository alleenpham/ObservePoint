package com.observepoint.test.test.service;

import java.util.List;
import java.util.Set;

import com.observepoint.test.test.domain.Department;
import com.observepoint.test.test.domain.Employee;

public interface DepartmentService {

	public Department getDepartmentById(long deptId);
	public List<Department> getAllDepartments();
	public Department saveDepartment(Department dept);
	public Department updateDepartment(Department dept);
	public void deleteDepartment(long deptId);
	public Department getDepartmentByName(String name);
}
