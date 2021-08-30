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
			/* ADD */
			if (addFlag) {
				addFlag = !addFlag;
				/*  allow user to add new employees */
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
				PrintWriter printWriter = null;
				try {
					
					fileWriter = new FileWriter(file, true);
					buffWriter = new BufferedWriter(fileWriter);
					printWriter = new PrintWriter(buffWriter);
					
					printWriter.println(newName);
					printWriter.println(newDepartment.toString());
					printWriter.println(Integer.toString(newSalary));
					
					String n = newName;
					String d = newDepartment.toString();
					String s = Integer.toString(newSalary);
					System.out.println("n = " + n + 
							", d = " + d +
							", s = " + s);
					
				} catch (IOException e) {
					System.out.println("'fileWriter' threw 'IOException'");
					e.printStackTrace();
				} finally {
					
					if ( buffWriter != null ) {
						
						try {
							buffWriter.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					
					if (fileWriter != null ) {
						
						try {
							fileWriter.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					if (printWriter != null) {
						printWriter.close();
					}
					
				}
				
			}
			
			/* UPDATE */
			if (updateFlag) {
				updateFlag = !updateFlag;
				/* allow user to update employee information.
				 * Assume that duplicate employees are the same employee. */
				
				/*
				 * 1. Ask user if select employee by regex= or index= --check.
				 * 
				 * 2.
				 * if regex=, then ask for regex input to compare to NAME only,
				 *  for now.  --check.
				 *  
				 * If index= then ask for input in range [1, n] where n is the 
				 *  number of employees in the employee file.
				 * 
				 * 3. If invalid input, throw exception. Else, 
				 * if regex=, search  for string match and select index where 
				 * name at index i is equal to regex.
				 * 
				 * 4. Then get the user input. name, department, salary.
				 * 
				 * 5. Replace name, dept., salary at proper locations.
				 * 
				 * 6. Close file.
				 */
				System.out.println("How do you want to select which employee "
						+ "to update? Enter a number:"
						+ "\n1. By name match."
						+ "\n2. By index match.\n\t"
						+ "i. e. Employees are indexed as list entries "
						+ "in range [1, n], "
						+ "\n\twhere n is the index of the last employee.");
				int option = Integer.valueOf(scan.nextLine());
				
				while (!(option == 1 ^ option == 2)) {
					System.out.println("Please enter \"1\" or "
							+ "\"2.\"");
					option = Integer.valueOf(scan.nextLine());
				}
				
				
				switch (option) {
				
					case 1:
						System.out.println("names of all employees:");
						printEmployeeNames(employees);
						System.out.println("---------------------------------"
								+ "----------------------");
						System.out.println("Enter the name of an employee:");
						String selectEmployee = scan.nextLine();
						while (!isValidEmployeeSelection(employees, selectEmployee)) {
							System.out.println("Please enter a valid employee name.");
							selectEmployee = scan.nextLine();
						}
						System.out.println("Please input name, department, and "
								+ "salary of " + selectEmployee + " to update, \n"
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
						
						
						System.out.println("n=" + newName +
								", d=" + newDepartment.toString() +
								", s=" + Integer.valueOf(newSalary));
						
						
			
						break;
					case 2:
						int employeeIndex = getNumberOfEmployees(employees, file);
						System.out.println("Please input a number between 1 and " + employeeIndex);
						int employeeIndexInput = scan.nextInt();
						while (employeeIndexInput <=0 || employeeIndexInput > employeeIndex) {
							System.out.println("Please enter a valid value.");
							employeeIndexInput = scan.nextInt();
						}
						break;
					default:
						System.out.println("Uh oh. Something went wrong on line 235.");
						break;
				
				}			
					
			}
			
			/* REMOVE */
			
			if (removeFlag) {
				removeFlag = !removeFlag;
				/* allow user to remove employees */
				System.out.println("Remove the employee! "
						+ "\nWhich employee "
						+ "do you want to remove? "
						+ "\nPlease input their name, department, and salary.");
			}
			
			/* PRINT */
			
			if (printFlag) {
				printFlag = !printFlag;
				/* allow user to list employee information */
				printEmployees(employees);
				
			}
			
			System.out.println("Done with command. Please exit or enter "
					+ "another command.");
			command = scan.nextLine();
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
	
	public static void printEmployeeNames(List<Employee> list) {
		Stream<Employee> stream = list.stream();
		System.out.println("<== Employees list. print method implements Stream: ==>");
		stream.forEach(result -> System.out.println(result.getName()));
		System.out.println("");
	}
	
	public static boolean isValidEmployeeSelection(List<Employee> list, String input) {
		Stream<Employee> stream = list.stream();
		boolean match = stream.anyMatch(result -> result.getName().equals(input));
		return match;
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
	
	public static int getNumberOfEmployees(List<Employee> list, File file) {
		System.out.println("called 'getNumberOfEmployees'");
		
		int count = 0;
		
		try {
			//create a scanner associated with the file.
			Scanner sc = new Scanner(file);
			
			//read each line and 
			//count the number of lines
			while (sc.hasNextLine()) {
				sc.nextLine();
				count++;
			}
			sc.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return count / 3;
	}

}
