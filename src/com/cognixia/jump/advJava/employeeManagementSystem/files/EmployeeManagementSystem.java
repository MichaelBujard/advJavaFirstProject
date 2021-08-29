package com.cognixia.jump.advJava.employeeManagementSystem.files;

import java.util.Scanner;

import com.cognixia.jump.advJava.employeeManagementSystem.files.Employee.DepartmentType;
import com.cognixia.jump.advJava.employeeManagementSystem.files.Employee;

public class EmployeeManagementSystem {
	
	public static void main(String[] args) {
		
		ReadEmployeesFile employeeReader = new ReadEmployeesFile();

		ReadEmployeesFile.main(args);
		
	}
	/* Helper method, where Option is one of ADD, UPDATE, REMOVE, or PRINT */
	public static boolean updateFlagBoolean(String command, String option) {
		return command.toLowerCase().equals(option);
	}

}
