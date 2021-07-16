package com.observepoint.test.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.observepoint.test.test.domain.Employee;
import com.observepoint.test.test.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping(value="getEmployeeById")
	public ResponseEntity<?> getEmployeeById(@RequestParam long empId){
		Employee employee = employeeService.getEmployeeById(empId);
		if (employee!=null) {
			return new ResponseEntity<Employee> (employee, HttpStatus.OK);
		} else {
			return new ResponseEntity<String> ("employee(id="+empId+") is not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="getAllEmpployees")
	public ResponseEntity<?> getEmployees(){
		System.out.println("getEmployees gets invoked");
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>> (employees, HttpStatus.OK);
	}
	
	@PostMapping(value="saveEmployee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee emp){
		System.out.println("saveEmployee gets invoked");
		Employee employee = employeeService.saveEmployee(emp);
		if (employee!=null) {
			return new ResponseEntity<Employee> (employee, HttpStatus.OK);
		} else {
		    return new ResponseEntity<String> ("Cannot save", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping(value="updateEmpployee")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee emp){
		Employee employee = employeeService.updateEmployee(emp);
		return new ResponseEntity<Employee> (employee, HttpStatus.OK);
	}
	
	@DeleteMapping(value="deleteEmpployee")
	public ResponseEntity<?> deleteEmployee(@RequestParam long empId){
		employeeService.deleteEmpployee(empId);
		return new ResponseEntity<String> ("employee(id="+empId+") was deleted", HttpStatus.OK);
	}
	
	@GetMapping(value="getEmployeeByDept")
	public ResponseEntity<?> getEmployeeByDept(@RequestParam long deptId){
		List<Employee> employees = employeeService.getEmployeeByDept(deptId);
		return new ResponseEntity<List<Employee>> (employees, HttpStatus.NOT_FOUND);

	}
	
	
}
