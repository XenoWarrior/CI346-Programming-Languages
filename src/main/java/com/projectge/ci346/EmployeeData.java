package com.projectge.ci346;

public class EmployeeData {

	private int employeeID;
	private String employeeFName;
	private String employeeSName;
	private String employeeShiftStart;
	private String employeeShiftEnd;
	
	public EmployeeData(int id, String fname, String sname, String shiftstart, String shiftend) {
		this.employeeID = id;
		this.employeeFName = fname;
		this.employeeSName = sname;
		this.employeeShiftStart = shiftstart;
		this.employeeShiftEnd = shiftend;
	}
	
	public int getID() {
		return this.employeeID;
	}

	public String getFullName() {
		return this.employeeFName + " " + this.employeeSName;
	}

	public String getFullShift() {
		return this.employeeShiftStart + " " + this.employeeShiftEnd;
	}
	
	public String getFirstName() {
		return this.employeeFName;
	}
	
	public String getLastName() {
		return this.employeeSName;
	}

	public String getShiftStart() {
		return this.employeeShiftStart;
	}

	public String getShiftEnd() {
		return this.employeeShiftEnd;
	}
	
}
