package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Date {
	private int day;
	private int month;
	private int year;
	
	private static final Logger logger = LogManager.getLogger(Date.class);
	
	public Date() { //default  constructor
		this.day = 0;
		this.month = 0;
		this.year = 0;
		logger.info("Date initialized");
	}
	
	public Date(int day, int month, int year) { //primary constructor 
		this.day = day;
		this.month = month;
		this.year = year;
		logger.info("Input accepted, Date initialized");
	}
	
	//getters and setters 
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
		logger.info("Input accepted, Day set");
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
		logger.info("Input accepted, Month set");
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
		logger.info("Input accepted, Year set");
	}

	@Override
	public String toString() {
		logger.info("Date returned");
		return "Day: " + day + "\nMonth=" + month + "\nYear: " + year;
	} 

}
