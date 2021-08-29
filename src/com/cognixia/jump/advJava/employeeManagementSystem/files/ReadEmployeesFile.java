package com.cognixia.jump.advJava.employeeManagementSystem.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.Scanner;

import com.cognixia.jump.advJava.employeeManagementSystem.files.Employee.DepartmentType;
import com.cognixia.jump.advJava.employeeManagementSystem.files.InvalidDepartmentException;

public class ReadEmployeesFile {
	
	
	/* employees list reduces file data as a list of employees */
	public static List<Employee> employees = new ArrayList<Employee>();

	public static void main(String[] args) {

		File file = new File("companyResources/employees.txt");
		FileReader fileReader = null;
		BufferedReader reader = null;
		
		try {
			
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			
			getEmployeesFromLines(reader);
			
		} catch (FileNotFoundException e) {
			System.out.println("Exception: Could not find file "
					+ file.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Exception: Could not read!");
		} finally {
			
			try {
				fileReader.close();
				reader.close();
			} catch (IOException e) {
				System.out.println("Could not close reader objects.");
			}
			
		}
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter one of the following:\n"
				+ "1. enter \"ADD\"\n"
				+ "2. enter \"UPDATE\"\n"
				+ "3. enter \"REMOVE\"\n"
				+ "4. enter \"PRINT\"\n");
		
		String command = scan.nextLine();
		boolean addFlag = command.toLowerCase().equals("add") ;
		boolean updateFlag = command.toLowerCase().equals("update");
		boolean removeFlag = command.toLowerCase().equals("remove");
		boolean printFlag = command.toLowerCase().equals("print");
		boolean flag = addFlag || updateFlag || removeFlag || printFlag;
		
		while (flag) {
			if (addFlag) {
				addFlag = !addFlag;
				/*  TODO: allow user to add new employees */
				System.out.println("Please input name, department, and "
						+ "salary of new employee to add, \n"
						+ "separated by comma, space [\", \"].");
				String params = scan.nextLine();
				
				String[] paramsArr = params.split(", ");
				String newName = paramsArr[0];
				DepartmentType newDepartment = null;
				try {
					newDepartment = getDepartmentType(paramsArr[1]);
				} catch (InvalidDepartmentException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				int newSalary = Integer.parseInt(paramsArr[2]);
				Employee newEmployee = (newDepartment != null) ? new Employee(newName, newDepartment, newSalary) : null;
				System.out.println("You put: " + newEmployee.toString());
				/* write to file. */
				FileWriter fileWriter = null;
				BufferedWriter buffWriter = null;
				try {
					
					fileWriter = new FileWriter(file, true);
					buffWriter = new BufferedWriter(fileWriter);
					
					System.out.println(newName);
					System.out.println(newDepartment.toString());
					System.out.println(Integer.toString(newSalary));
					appendToFile(buffWriter, newName);
					appendToFile(buffWriter, newDepartment.toString());
					appendToFile(buffWriter, Integer.toString(newSalary));
					printEmployees(employees);
					
				} catch (IOException e) {
					System.out.println("'fileWriter' threw 'IOException'");
					e.printStackTrace();
				}
				
			}
			if (updateFlag) {
				updateFlag = !updateFlag;
				/* TODO: allow user to update employee information */
				System.out.println("Please input name, department, and "
						+ "salary of new employee to add, \n"
						+ "separated by comma, space [\", \"].");
				String params = scan.nextLine();
				
				String[] paramsArr = params.split(", ");
				String newName = paramsArr[0];
				DepartmentType newDepartment = null;
				try {
					newDepartment = getDepartmentType(paramsArr[1]);
				} catch (InvalidDepartmentException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				int newSalary = Integer.parseInt(paramsArr[2]);
				Employee newEmployee = (newDepartment != null) ? new Employee(newName, newDepartment, newSalary) : null;
				System.out.println("You put: " + newEmployee.toString());
				
				
			}
			if (removeFlag) {
				removeFlag = !removeFlag;
				/* TODO: allow user to remove employees */
				System.out.println("Remove the employee! "
						+ "\nWhich employee "
						+ "do you want to remove? "
						+ "\nPlease input their name, department, and salary.");
			}
			if (printFlag) {
				printFlag = !printFlag;
				/* allow user to list employee information */
				printEmployees(employees);
				
			}
			
			System.out.println("Done with command. Please exit or enter "
					+ "another command.");
			command = scan.next();
			addFlag = updateFlagBoolean(command, "add");
			updateFlag = updateFlagBoolean(command, "update");
			removeFlag = updateFlagBoolean(command, "remove");
			printFlag = updateFlagBoolean(command, "print");
			flag = addFlag || updateFlag || removeFlag || printFlag;
		}
		
		
		
		
		
		
	}
	
	public static DepartmentType getDepartmentType(String str) throws InvalidDepartmentException {
		
		DepartmentType returnValue;
		
		switch (str) {
		
			case "FINANCE" :
				returnValue = DepartmentType.valueOf(str);
				break;
			case "PROCUREMENT" :
				returnValue = DepartmentType.valueOf(str);
				break;
			case "IT" :
				returnValue = DepartmentType.valueOf(str);
				break;
			case "DEMAND_PLANNING" :
				returnValue = DepartmentType.valueOf(str);
				break;
			case "HUMAN_RESOURCES" :
				returnValue = DepartmentType.valueOf(str);
				break;
			default: 
				returnValue = null;
				break;
				
		}
		
		if (returnValue == null) {
			throw new InvalidDepartmentException(str);
		}
		
		return returnValue;
	
	}
	
	public static void printEmployees(List<Employee> list) {
		Stream<Employee> stream = list.stream();
		System.out.println("<== Employees list. print method implements Stream: ==>");
		stream.forEach(result -> System.out.print(result + " "));
		System.out.println("");
	}
	
	public static void getEmployeesFromLines(BufferedReader reader) throws FileNotFoundException, IOException {
		
		String name = null;
		DepartmentType department = null;
		int salary = -1;
		String line;
		
		int ruler = 1;
		int modulus = 3;
		
		Employee employee = null;
		
		while ((line = reader.readLine()) != null) {
			switch (ruler) {
				case 1 :
					name = line;
					ruler += 1;
					break;
				
				case 2 :
					
					try {
						department = getDepartmentType(line);
					} catch (InvalidDepartmentException e){
						department = null;  //enforce department name later
						System.out.println("InvalidDepartmentException\n");
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					
					ruler += 1;
					ruler %= modulus;
					break;
				
				case 0 :
					salary = Integer.parseInt(line);
					ruler += 1;
					break;
				
				default :
					System.out.println("ERROR: in switch statement, ruler "
							+ "not in range [0, modulus], modulus=3.");
					break;
			}
			
			//create employee from file input and continue
			employee = (ruler == 1 && department != null) ? new Employee(name, department, salary) : null;
			
			/* add employee to list. If null, indicates bad department */
			if (ruler == 1) {
				employees.add(employee);
			}
		
		}
		
	}
	
	/* Helper method, where Option is one of ADD, UPDATE, REMOVE, or PRINT */
	public static boolean updateFlagBoolean(String command, String option) {
		return command.toLowerCase().equals(option);
	}
	
	public static void appendToFile(BufferedWriter writer, String str) throws IOException{
		
		writer.newLine();
		writer.append(str);
		
	}

}
