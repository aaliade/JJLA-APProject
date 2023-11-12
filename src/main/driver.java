package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import models.Customer;
import models.Employee;
import models.User;
import networking.EmployeeClient;
import networking.Server;

import java.util.Date;
public class driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//start employee client
		EmployeeClient client = new EmployeeClient();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = "1111-01-01"; // Format: yyyy-MM-dd
		Date specificDate = null;
		
		try {
			specificDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Employee emp = new Employee(9032042, "jnkfke",specificDate , "nkdqnjfk", "kjwndk", "Lascelle", "Mckenzie",
				"18768857845745", "4uiegkitg@gmail.com",
				"Somewhere", "Employee");
		
		client.sendAction("Add Employee");
		System.out.println("Action sent");
		client.sendEmployee(emp);
		System.out.println("Object sent");
		client.receiveResponse();
		System.out.println("Response recieved");
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String dateString = "1111-01-01"; // Format: yyyy-MM-dd
//		Date specificDate = null;
//		
//		try {
//			specificDate = dateFormat.parse(dateString);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
////		User user = new User("Lascelle12", "Lascelle68659", "Lascelle", "Mckenzie",
////				"18768857845745", "4uiegkitg@gmail.com",
////				"Somewhere", "Employee");
//		Employee emp = new Employee(1234, "supervisor",specificDate , "Lascelle12", "Lascelle68659", "Lascelle", "Mckenzie",
//				"18768857845745", "4uiegkitg@gmail.com",
//				"Somewhere", "Employee");
//		Customer customer = new Customer("nffgg", "ddgsg", "Richard", "Hendricks","dgsrg","Somewhere", "richH@gmail","Customer", 45434, 0.0f);
//		
////		emp.create();
//		customer.create();
	}

}
