package com.observepoint.test.test.all;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

import com.observepoint.test.test.domain.Department;
import com.observepoint.test.test.domain.Employee;
import com.observepoint.test.test.service.DepartmentService;
import com.observepoint.test.test.service.EmployeeService;
import static org.junit.jupiter.api.Assertions.assertEquals;


import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestEmployeeService {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	DepartmentService departmentService;
	
	
	@Test
	
	@Order(1)   
	public void t1_shouldSaveDeparments() {
		Department dept = new Department("accounting");
		Department deptDB = departmentService.saveDepartment(dept);
		assertEquals(dept.getName(), deptDB.getName());
		assertEquals(1,departmentService.getAllDepartments().size());

	}
	
	
	
	@Transactional()
	@Commit
	@Test
	@Order(2)   
	public void t2_shouldSaveEmployee1() {
		Department dept = new Department("business");
		System.out.println(">>>>>>>>>>>>>>dept:"+dept);
		Department deptDB = departmentService.saveDepartment(dept);
		assertEquals(dept.getName(), deptDB.getName());
		assertEquals(2,departmentService.getAllDepartments().size());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>dept:"+dept);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>deptDB:"+ deptDB);
		System.out.println(">>>>>>>>>>>>>>>>>>All department:"+ departmentService.getAllDepartments());
		Employee emp = new Employee("Alleen", "Pham", deptDB );
		System.out.println("emp:"+ emp);
		Employee empDB =  employeeService.saveEmployee(emp);
	
		System.out.println("empDB:"+ empDB);
		assertEquals(emp.getFirstName(), empDB.getFirstName());
		assertEquals(emp.getLastName(), empDB.getLastName());
		assertEquals(emp.getDepartment().getId(), empDB.getDepartment().getId());
		assertEquals(emp.getDepartment().getName(), empDB.getDepartment().getName());
		assertEquals(1,employeeService.getAllEmployees().size());
	}
	

	
	@Transactional()
	@Commit
	@Test
	@Order(3)   
	public void t3_shouldSaveEmployee2() {
		Department dept = new Department("engineer");
		Department deptDB = departmentService.saveDepartment(dept);
		assertEquals(dept.getName(), deptDB.getName());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>dept:"+dept);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>deptDB:"+ deptDB);
		System.out.println(">>>>>>>>>>>>>>>>>>All department:"+ departmentService.getAllDepartments());
		assertEquals(3,departmentService.getAllDepartments().size());
		Employee emp = new Employee("Blleen", "Kham", deptDB );
		Employee empDB =  employeeService.saveEmployee(emp);
		assertEquals(emp.getFirstName(), empDB.getFirstName());
		assertEquals(emp.getLastName(), empDB.getLastName());
		assertEquals(emp.getDepartment().getId(), empDB.getDepartment().getId());
		assertEquals(emp.getDepartment().getName(), empDB.getDepartment().getName());
		assertEquals(2, employeeService.getAllEmployees().size());
	}
	
	
	@Transactional()
	@Commit
	@Order(4)   
	@Test
	public void t4_shouldSaveEmployee3() {
		Department dept = departmentService.getDepartmentByName("engineer");
		Employee emp = new Employee("Clleen", "Tham", dept );
		Employee empDB =  employeeService.saveEmployee(emp);
		assertEquals(emp.getFirstName(), empDB.getFirstName());
		assertEquals(emp.getLastName(), empDB.getLastName());
		assertEquals(emp.getDepartment().getId(), empDB.getDepartment().getId());
		assertEquals(emp.getDepartment().getName(), empDB.getDepartment().getName());
		assertEquals(3,employeeService.getAllEmployees().size());
	}
	
	@Transactional()
	@Commit
	@Order(5) 
	@Test
	public void t5_shouldSaveEmployee4() {
		Department dept = departmentService.getDepartmentById(1);
		Employee emp = new Employee("Dlleen", "Zham", dept );
		Employee empDB =  employeeService.saveEmployee(emp);
		assertEquals(emp.getFirstName(), empDB.getFirstName());
		assertEquals(emp.getLastName(), empDB.getLastName());
		assertEquals(emp.getDepartment().getId(), empDB.getDepartment().getId());
		assertEquals(emp.getDepartment().getName(), empDB.getDepartment().getName());
		assertEquals(4,employeeService.getAllEmployees().size());
	}
	
	
	@Transactional()
	@Commit
	@Order(6) 
	@Test
	public void t6_shouldNotSaveEmployeeWithInvalidDeptId() {
		Department dept = new Department("invalid department ID");
		dept.setId(10L);
		Employee emp = new Employee("Dlleen", "Zham", dept );
		Employee empDB =  employeeService.saveEmployee(emp);
		assertEquals(empDB, null);
		assertEquals(4,employeeService.getAllEmployees().size());
		System.out.println(">>>>>>>>>>>>>6>>>>AllEmployees():"+employeeService.getAllEmployees());
	}

	@Transactional()
	@Commit
	@Order(7) 
	@Test
	public void t7_shouldUpdateEmployee() {
		System.out.println(">>>>>>>>>>>7>>AllEmployees():"+employeeService.getAllEmployees());
		// ypdate department "accounting" to "business";
		Department dept = departmentService.getDepartmentByName("business");
		Employee emp = employeeService.getEmployeeByName("Pham");
		emp.setDepartment(dept);
		//update lastName to "Ham"
		emp.setLastName("Ham");
		Employee empDB =  employeeService.updateEmployee(emp);
		assertEquals(empDB.getEmpId(), emp.getEmpId());
		assertEquals(empDB.getFirstName(), "Alleen");
		assertEquals(empDB.getLastName(), "Ham");
		assertEquals(dept.getId(),empDB.getDepartment().getId());
		assertEquals(empDB.getDepartment().getName(), dept.getName());
		assertEquals(4,employeeService.getAllEmployees().size());
	}
	
	@Transactional()
	@Commit
	@Order(8) 
	@Test
	public void t8_shouldNotUpdateInvalidEmployee() {
		Department dept = departmentService.getDepartmentByName("accounting");
		Employee emp = new Employee((long)10, "Dummy", "Ham", dept );
		Employee empDB =  employeeService.updateEmployee(emp);
		assertEquals(empDB,null);
		assertEquals(4,employeeService.getAllEmployees().size());
	}
	
}
