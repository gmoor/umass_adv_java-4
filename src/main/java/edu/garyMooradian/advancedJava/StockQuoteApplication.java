package edu.garyMooradian.advancedJava;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.http.annotation.Immutable;


/*
 * The IntervalEnum type will be the fourth parameter for the getQuoteHistory method
 */
enum IntervalEnum {
	WEEKLY, DAILY, HOURLY
}

@Immutable
public class StockQuoteApplication {
	
	public static void main(String[] args) {

		/*
		 * Hard coding stock symbol, start date, end date and interval.
		 * In a real application we would obviously not do it this way.
		 * 
		 * Create two Calendar instances/objects; one for start date and one for end date
		 * The Calendar objects will be set to the Dates passed in via args[1] and args[2]
		 * Call getStockService from StockServiceFactory class. It will return BasicStockService
		 * object.
		 * 
		 */
		IntervalEnum interval = IntervalEnum.WEEKLY;
		String stockSymbol = "OLED";
		String startDate = "01/01/2019";
		String endDate = "01/30/2019";
		
		/*
		 * The SimpleDateFormat parse method for our startDate and endDate, has the capability
		 * of throwing a ParseException
		 * However there appears to be improperly formated date strings for this application
		 * that are not caught by the ParseException thrown by the SimpleDateFormat parse method.
		 * Perhaps we should add our own validation code for those other improperly formated
		 * date strings, and throw a ParseException
		 */
		try {
			
			/*
			 * Create Calendar object for start date
			 */
			Calendar calendarStartDate = Calendar.getInstance();
			
			/*
			 * parse the start date String to a Date object and set Calendar object with that Date object
			 */
			calendarStartDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(startDate));
			
			
			/*
			 * Create Calendar object for end date
			 */
			
			Calendar calendarEndDate = Calendar.getInstance();
			/*
			 * parse the end date String to a Date object and set Calendar object with that Date object
			 */
			calendarEndDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(endDate));
			

			/*
			 * Create a BasicStockService object and call the getQuoteHistory method.
			 */
			List<StockQuote> listOfQuotes = new BasicStockService().getQuoteHistory(stockSymbol, calendarStartDate, calendarEndDate, interval);
			
			/*
			 * Print the date/time recorded for each stock quote
			 */
			for(StockQuote stockQuote : listOfQuotes) {
				System.out.println(stockQuote.getDateRecorded());
			}
		} catch(ParseException pe) {
			/*
			 * In this application, we are simply hard coding our values. In other words, we are not
			 * prompting the user for entry nor accessing some outside source for our data. If we
			 * were we could handle the exception in a constructive way here in our catch phrase; i.e.
			 * handle in such a way to fix the issue. For example if we used user entry and their
			 * entry resulted in a ParseException, we could generate an error message and re-prompt
			 * the user.
			 * 
			 * In this application however I don't see a realistic opportunity to handle
			 * the ParseException so I just print a message indicating what is wrong.
			 * 
			 * PLEASE INFORM IF THERE IS A BETTER WAY IN THIS PARTICULAR ASSIGNMENT
			 */
			System.out.println("The date should be in the format: MM/dd/yyyy (e.g. 05/27/2019)");
			pe.printStackTrace();
		}
	}

}
