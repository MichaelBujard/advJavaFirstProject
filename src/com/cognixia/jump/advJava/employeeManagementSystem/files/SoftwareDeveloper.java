package com.cognixia.jump.advJava.employeeManagementSystem.files;

import java.io.Serializable;

public class SoftwareDeveloper extends Employee implements Serializable {

	/**
	 * default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	DepartmentType department = DepartmentType.IT;
	static int salary = 52000;

	public SoftwareDeveloper(String name, DepartmentType department, int salary) {
		
		super(name, department, salary);
		
	}
	
	
	@Override
	public String elevatorPitch() {
		return "My name is " + this.getName() + ".\n"
				+ "I work in  the " + this.getDepartment() + " department, \n"
				+ "and I get paid " + this.getSalary() + "annually.\n"
				+ "I love my job. It's the best.";
	}
	
}
