package com.observepoint.test.test.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.observepoint.test.test.domain.Department;
import com.observepoint.test.test.domain.Employee;
import com.observepoint.test.test.repository.DepartmentRepository;
import com.observepoint.test.test.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public Employee getEmployeeById(long empId) {
		employeeRepository.findById(empId);
		return employeeRepository.findById(empId).orElse(null);
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee saveEmployee(Employee emp) {
		Employee newEmp = new Employee(emp.getFirstName(), emp.getLastName(), emp.getDepartment());
		Department dept = departmentRepository.findById(emp.getDepartment().getId()).orElse(null);
		if (dept!=null) {
			newEmp.setDepartment(dept);
			Employee savedEmp = employeeRepository.save(newEmp);
			System.out.println("saving is done");
			return savedEmp;
		} else {
			return null;
		}

	}

	@Override
	public Employee updateEmployee(Employee emp) {
		Optional<Employee> optEmp = employeeRepository.findById(emp.getEmpId());
		Employee empDB=null;
		if (optEmp.isPresent()) {
			Department dept = departmentRepository.findById(emp.getDepartment().getId()).orElse(null);
			if (dept!=null) {
				empDB = optEmp.get();
				empDB.setFirstName(emp.getFirstName());
				empDB.setLastName(emp.getLastName());
				empDB.setDepartment(emp.getDepartment());
			    employeeRepository.save(empDB);
			    return empDB;
			}
		}
		return null;
	}

	@Override
	public void deleteEmpployee(long empId) {
		Optional<Employee> optEmp = employeeRepository.findById(empId);
		if (optEmp.isPresent()) {
			employeeRepository.deleteById(empId);
		}
	}

	@Override
	public List<Employee> getEmployeeByDept(long deptId) {
		Department dept = departmentRepository.findById(deptId).orElse(null);
		List<Employee> employees = null;
		if (dept!=null) {
		  return employeeRepository.getEmployeesByDept(dept);
		}
		return employees;
	}

	@Override
	public Employee getEmployeeByName(String lastName) {
		return employeeRepository.findByLastName(lastName);
	}

}
