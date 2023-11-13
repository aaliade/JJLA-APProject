package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import models.Customer;
import models.Employee;
import models.Equipment;
import models.User;
import networking.EmployeeClient;
import networking.Server;
import multiThreading.MultiThreadServer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
public class driver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		System.out.println("config loaded");
		Employee emp = (Employee)context.getBean("employee");
		System.out.println(emp.toString());

/*
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
		*/
//		User user = new User("Lascelle12", "Lascelle68659", "Lascelle", "Mckenzie",
//				"18768857845745", "4uiegkitg@gmail.com",
//				"Somewhere", "Employee");
		/*Employee emp = new Employee(1234, "supervisor",specificDate , "Lascelle12", "Lascelle68659", "Lascelle", "Mckenzie",
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
		emp.create();
		customer.create();
		
		//use to run the multi-threading 
		//new MultiThreadServer();
		//MultiThreadServer multiThreadServer = new MultiThreadServer();
		emp.create();*/
		//customer.create();
		
		 // Test equipment creation and update
//        Equipment equipment = new Equipment();
//        equipment.insert(1, "Stage Light", "High-intensity stage light", true, "Lighting", 50);
//        equipment.insert(2, "Microphone", "Professional microphone for events", true, "Audio", 30);
//        equipment.insert(3, "Power Generator", "High-capacity power generator", true, "Power", 70);
//        equipment.insert(4, "Sound System", "Professional sound system for events", false, "Sound", 100);
//        equipment.insert(5, "Microphone Stand", "Adjustable microphone stand", true, "Sound", 20);
//        equipment.insert(6, "Spotlight", "Focused spotlight for stage events", false, "Lighting", 40);
//        equipment.insert(7, "Extension Cables", "Long power extension cables", true, "Power", 15);
//        equipment.insert(8, "Stage Monitor", "Monitor speaker for stage performances", true, "Sound", 60);
//        equipment.insert(9, "Stage Platform", "Adjustable stage platform for events", false, "Staging", 50);
//        equipment.insert(10, "LED Stage Lights", "Colorful LED lights for stage illumination", true, "Lighting", 30);
	}
}
