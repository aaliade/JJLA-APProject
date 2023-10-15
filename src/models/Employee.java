package models;

public class Employee extends User{
	private int empID;
	private String empRole;
	private Date hireDate;
	
	//Default Constructor
	public Employee(){
		this.empID = 0;
		this.empRole = "";
		this.hireDate = new Date(1,1,1111);
	}
	
	//Primary Constructor
	public Employee(int empID, String empRole, Date hireDate) {
		super();
		this.empID = empID;
		this.empRole = empRole;
		this.hireDate = hireDate;
	}
	
	//Copy Constructor
	public Employee(Employee emp) {
		super();
		this.empID = emp.empID;
		this.empRole = emp.empRole;
		this.hireDate = emp.hireDate;
	}


	public int getEmpID() {
		return empID;
	}


	public void setEmpID(int empID) {
		this.empID = empID;
	}


	public String getEmpRole() {
		return empRole;
	}


	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}


	public Date getHireDate() {
		return hireDate;
	}


	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
}
