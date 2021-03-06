package com.observepoint.test.test.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.observepoint.test.test.domain.Department;
import com.observepoint.test.test.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public Department getDepartmentById(long deptId) {
		return departmentRepository.findById(deptId).orElse(null);
	}



	@Override
	public List<Department> getAllDepartments() {
		
		return departmentRepository.findAll();
	}

	@Override
	public Department saveDepartment(Department dept) {
		Department newDept = new Department(dept.getName());
		Department savedDept = departmentRepository.save(newDept);
		return savedDept;
	}

	@Override
	public Department updateDepartment(Department dept) {
		Department deptDB = departmentRepository.findById(dept.getId()).orElse(null);
		if (deptDB!=null) {
			deptDB.setName(dept.getName());
			System.out.println("deptDB:"+deptDB);
			return departmentRepository.save(deptDB);
		}
		return null;
	}

	@Override
	public void deleteDepartment(long deptId) {
		Department deptDB = departmentRepository.findById(deptId).orElse(null);
		if (deptDB!=null) {
			departmentRepository.deleteById(deptId);
		}

	}



	@Override
	public Department getDepartmentByName(String name) {
		// TODO Auto-generated method stub
		return departmentRepository.findDepartmentByName(name);
	}

}
