package main;

import models.Date;
import models.Employee;
import models.User;

public class driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		User user = new User("Lascelle12", "Lascelle68659", "Lascelle", "Mckenzie",
//				"18768857845745", "4uiegkitg@gmail.com",
//				"Somewhere", "Employee");
		Employee emp = new Employee(1234, "supervisor", new Date(1,1,1), "Lascelle12", "Lascelle68659", "Lascelle", "Mckenzie",
				"18768857845745", "4uiegkitg@gmail.com",
				"Somewhere", "Employee");
		
		emp.create();
	}

}
