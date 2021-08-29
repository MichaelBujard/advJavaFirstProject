package com.cognixia.jump.advJava.employeeManagementSystem.files;

public class InvalidDepartmentException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidDepartmentException(String str) {
		
		super("------------------------------------------\n"
				+ "Departments must be one of the following:\n"
				+ "\"FINANCE\", \"PROCUREMENT\", \"IT\", "
				+ "\"DEMAND_PLANNING\", or \"HUMAN_RESOURCES.\"\n"
				+ "Actual value : " + str + "\n"
				+ "------------------------------------------\n");
	
	}
	
}
