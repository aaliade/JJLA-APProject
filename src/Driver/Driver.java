package Driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import models.Customer;
import models.Employee;
import models.User;
import java.util.Date;
public class Driver {
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = "1111-01-01"; // Format: yyyy-MM-dd
			Date specificDate = null;
			
			try {
				specificDate = dateFormat.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
//			User user = new User("Lascelle12", "Lascelle68659", "Lascelle", "Mckenzie",
//					"18768857845745", "4uiegkitg@gmail.com",
//					"Somewhere", "Employee");
			Employee emp = new Employee(1234, "supervisor",specificDate);
			Customer customer = new Customer("RichieB", "rich123", "Richard", "Hendricks","193984","Somewhere", "richH@gmail","Customer", 123443, 0.0f);
			
			emp.create();
			//customer.create();
		}

	
}

