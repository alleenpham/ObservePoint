package com.observepoint.test.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.observepoint.test.test.domain.Department;
import com.observepoint.test.test.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value="select e from Employee e where e.department = :dept")
	public List<Employee> getEmployeesByDept(@Param("dept") Department dept);
	
	public Employee findByLastName(String lastName);
}
