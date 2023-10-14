package groupWork;

public class Date {
	private int day;
	private int month;
	private int year;
	
	
	public Date() { //default  constructor
		this.day = 0;
		this.month = 0;
		this.year = 0;
	}
	
	public Date(int day, int month, int year) { //primary constuctor 
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	//getters and setters 
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Day: " + day + "\nMonth=" + month + "\nYear: " + year;
	} 
	
	
	
	
}
