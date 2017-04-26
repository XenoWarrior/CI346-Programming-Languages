package com.projectge.ci346;

public class EmployeeData {

	private int employeeID;
	private String employeeFName;
	private String employeeSName;
	private String employeeShift;
	
	public EmployeeData(int id, String fname, String sname, String shift) {
		this.employeeID = id;
		this.employeeFName = fname;
		this.employeeSName = sname;
		this.employeeShift = shift;
	}
	
}
