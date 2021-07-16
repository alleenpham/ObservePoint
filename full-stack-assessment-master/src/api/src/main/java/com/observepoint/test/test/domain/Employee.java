package com.observepoint.test.test.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="employees")
public class Employee {

	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long empId;
	 private  String firstName;
	 private  String lastName;
	 
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name="id")
	
	 private @Getter @Setter Department department;
	
	 public Employee() {
	}

	public Employee( String firstName, String lastName, Department department) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
	}
	
	public Employee(Long empId, String firstName, String lastName, Department department) {
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
	}


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName+ "]";
	}




	 
	 
}
