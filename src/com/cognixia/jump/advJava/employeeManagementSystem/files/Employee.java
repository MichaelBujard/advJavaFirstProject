package com.cognixia.jump.advJava.employeeManagementSystem.files;

import java.io.Serializable;

import com.cognixia.jump.advJava.employeeManagementSystem.files.Employee.DepartmentType;

public class Employee implements Serializable {
	
	public static final long serialVersionUID = 1L; //default
	
	public static enum DepartmentType{
		FINANCE, PROCUREMENT, IT, DEMAND_PLANNING, HUMAN_RESOURCES
	}
	
	private String name;
	private DepartmentType department;
	private int salary;
	
	public Employee(String name, DepartmentType department, int salary) {
		super();
		this.setName(name);
		this.setDepartment(department);
		this.setSalary(salary);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DepartmentType getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentType department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
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

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", salary=" + salary + "]";
	}

}
