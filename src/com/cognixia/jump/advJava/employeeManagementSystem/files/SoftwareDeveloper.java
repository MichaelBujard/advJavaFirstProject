package com.cognixia.jump.advJava.employeeManagementSystem.files;

import java.io.Serializable;

public class SoftwareDeveloper extends Employee implements Serializable {

	/**
	 * default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	DepartmentType department = DepartmentType.IT;
	int salary = 52000;

	public SoftwareDeveloper(String name, DepartmentType department, int salary) {
		
		super(name, department, salary);
		
	}

}
