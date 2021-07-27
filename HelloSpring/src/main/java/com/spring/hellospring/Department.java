package com.spring.hellospring;

public class Department {
	private String deptName;
	private int deptCount;
	
	public Department() {}

	public Department(String deptName, int deptCount) {
		super();
		this.deptName = deptName;
		this.deptCount = deptCount;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getDeptCount() {
		return deptCount;
	}

	public void setDeptCount(int deptCount) {
		this.deptCount = deptCount;
	}

	@Override
	public String toString() {
		return "Department [deptName=" + deptName + ", deptCount=" + deptCount + "]";
	}
	
}
