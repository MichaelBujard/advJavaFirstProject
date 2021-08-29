package com.cognixia.jump.advJava.employeeManagementSystem.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.cognixia.jump.advJava.employeeManagementSystem.files.Employee.DepartmentType;

public class WriteNewEmployee {
	
	public String newName;
	public DepartmentType department;
	public int salary;
	
	public WriteNewEmployee(String newName, DepartmentType department, int salary) {
		this.newName = newName;
		this.department = department;
		this.salary = salary;
	}
	
	public static void writeNewWmployeeToFile() {
		
	}

	public static void main(String[] args) {
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter buffWriter = null;
		PrintWriter printWriter = null;
		
		
		try {
			
			file = new File("companyResources/employees.txt");
			fileWriter = new FileWriter(file);
			buffWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(buffWriter);
			
			writeToFile(buffWriter, "Hello");
			System.out.println("Success: 'writeToFile()'");
			
			appendToFile(buffWriter, "HI");
			System.out.println("Success: 'appendToFile()'");
			
			
			writeUsingPrintWriter(printWriter, "Hello World");
			System.out.println("Success: 'writeToFile()'");
			
			
				
		} catch (IOException e) {
			System.out.println("Exception: 'IOException' caught.");
			e.printStackTrace();
		} finally {
			
			
			if ( buffWriter != null ) {
				try {
					buffWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if ( fileWriter != null ) {
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
	
	
	public static void writeToFile(BufferedWriter writer, String str) throws IOException{
	
		for (int idx = 0; idx < 3; idx++) {
			writer.write(str);
		}
		
	}
	
	public static void appendToFile(BufferedWriter writer, String str) throws IOException{
		
		for (int idx = 0; idx < 3; idx++) {
			writer.append(str);
		}
		
	}
	
	public static void writeUsingPrintWriter(PrintWriter printer, String str) {
		
		printer.println(str);
		printer.println();
		printer.println(str);
		printer.println(str);
		
		
	}
	
	
	
	

}
