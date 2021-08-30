package com.cognixia.jump.advJava.employeeManagementSystem.files;

import java.io.Serializable;
import java.util.Objects;

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
		
	

	@Override
	public int hashCode() {
		return Objects.hash(department, name, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return department == other.department && Objects.equals(name, other.name) && salary == other.salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", salary=" + salary + "]";
	}

}
