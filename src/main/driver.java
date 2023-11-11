package main;

import models.Date;
import models.Employee;
import models.User;

public class driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Employee user1 = new Employee(1234,"Supervisor", new Date(2,2,2019), "John234", "nfrijfn", "John","Richardson", "1-876-234-4543", "JohnRi@gmail.com","Portmore", "Employee");
		Employee user1 = new Employee( "John234", 1234,"Supervisor", new Date(2,2,2019));
		
		//System.out.print(user1.getUsername());
		
		user1.createEmp();
	}

}
