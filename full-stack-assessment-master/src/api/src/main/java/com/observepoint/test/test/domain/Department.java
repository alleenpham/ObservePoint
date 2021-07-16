package com.observepoint.test.test.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="departments")
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  Long id;
	
	private String name;
	
	public Department() {

	}
	public Department(String name) {
		this.name = name;
	}
//	
//	public Department(long id, String name) {
//		this.id = id;
//		this.name = name;
//	}
//	

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	
	
}
