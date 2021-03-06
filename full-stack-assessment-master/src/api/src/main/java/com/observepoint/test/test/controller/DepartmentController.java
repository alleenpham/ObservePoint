package com.observepoint.test.test.controller;

import java.util.List;
import java.util.Set;

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

import com.observepoint.test.test.domain.Department;
import com.observepoint.test.test.service.DepartmentService;
import com.observepoint.test.test.service.EmployeeService;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@GetMapping(value="getDepartments")
	public ResponseEntity<?> getDepartments(){
		
		List<Department> departments = departmentService.getAllDepartments();
		return new ResponseEntity<List<Department>> (departments, HttpStatus.OK);
	}
	
	
	@GetMapping(value="saveDepartment")
	public ResponseEntity<?> saveDepartment(@RequestParam String deptName){
		Department department = new Department(deptName);
		Department departmentDB = departmentService.saveDepartment(department);
		return new ResponseEntity<Department> (departmentDB, HttpStatus.OK);
	}
	
	@PutMapping(value="updateDepartment")
	public ResponseEntity<?> updateDepartment(@RequestBody Department dept){
			Department department   = departmentService.updateDepartment(dept);
			if(department!=null)  return new ResponseEntity<Department> (department, HttpStatus.OK);
		    return new ResponseEntity<String> ("not found", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value="deleteDepartment")
	public ResponseEntity<?> deleteDepartment(@RequestParam long deptId ){
		Department department = departmentService.getDepartmentById(deptId);
		if(department!=null) {
			departmentService.deleteDepartment(deptId);
			return new ResponseEntity<String> ("department (" + deptId + ") was deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String> ("not found", HttpStatus.NOT_FOUND);
	}
	
	
}
