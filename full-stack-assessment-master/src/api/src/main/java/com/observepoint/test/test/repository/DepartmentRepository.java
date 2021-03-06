package com.observepoint.test.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.observepoint.test.test.domain.Department;


public interface DepartmentRepository extends JpaRepository<Department,Long> {

	  public Department findDepartmentByName(String name);
}
