package edu.garyMooradian.advancedJava;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.junit.Test;

public class BasicStockServiceTest {
	
	/******************** Tests for getQuote method *************************/
	
	/*
	 * Tests the getQuote method in BasicStockService
	 */
	@Test
	public void testGetQuote() {
		//Create a Date
		Date date = new Date(2019,05,05);
		//Pass stock symbol and date.
		StockQuote quote = new BasicStockService().getQuote("OLED", date);
		/*
		 * Provide same price as we hard coded in BasicStockService's getQuote method
		 */
		BigDecimal price = new BigDecimal(5.00);
		
		/*
		 * Verify that dateRecorded is correct for the StockQuote object
		 * Note: Just trying assertTrue for the hell of it; i.e. instead of assertEquals
		 */
		assertTrue("The dateRecorded is not correct", date == quote.getDateRecorded());
		
		/*
		 * Verify that the stockPrice is correct for the StockQuote object
		 */
		assertTrue("The stockPrice is not correct", price.equals(quote.getStockPrice()));
		
		/*
		 * Verify the stockSymbol is correct for the stockQuote object
		 */
		assertEquals("The stockSymbol is not correct", "OLED", quote.getStockSymbol());
		
		
	}
	
	/********************* Tests for getQuoteHistory method *************************/
	
	/*
	 * Testing the GetQuoteHistory method; i.e.
	 * Testing that the List is populated with two StockQuote objects
	 * We test for two quotes, not just one, because of the OR condition
	 * in the while loop; i.e. while(date.before(endDate) || date.equals(endDate))
	 */
	@Test
	public void testGetQuoteHistoryPositive() throws ParseException {
		BasicStockService basicStockService = new BasicStockService();
		
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/20/2019"));
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/21/2019"));
		
		IntervalEnum interval = IntervalEnum.DAILY;
		
		/*
		 * Call the method we are testing
		 */
		List<StockQuote> listOfQuotes = basicStockService.getQuoteHistory("OLED", calendarStartDate, calendarEndDate, interval);
	
		/*
		 * Testing the StockQuote for only the stock symbol is good enough since
		 * we just want to make sure the StockQuote object was created. How well that
		 * object is created is tested elsewhere; i.e. tested when we tested the getQuote method
		 */
		assertEquals("The first stock quote in the list does not contain the proper stock symbol", "OLED", listOfQuotes.get(0).getStockSymbol());
		assertEquals("The second stock quote in the list does not contain the proper stock symbol", "OLED", listOfQuotes.get(1).getStockSymbol());
	}
	
	
	/*
	 * This test passes an end date that is earlier than the start date.
	 * This should result in no StockQuotes being created, thus the List returned
	 * should be empty.
	 */
	@Test
	public void testGetQuoteHistoryNegative() throws ParseException {
		BasicStockService basicStockService = new BasicStockService();
		
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/20/2019"));
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/19/2019"));
		
		IntervalEnum interval = IntervalEnum.DAILY;
		
		/*
		 * Call the method we are testing
		 */
		List<StockQuote> listOfQuotes = basicStockService.getQuoteHistory("OLED", calendarStartDate, calendarEndDate, interval);
	
		/*
		 * Verifying that the List of StockQutoes is empty, as expected
		 */
		assertTrue("No StockQuote objects should have been created; i.e. List of StockQuotes should be empty but was not", listOfQuotes.isEmpty());
	}
	
	
	/*
	 * Testing based on setting the interval to HOURLY; i.e.
	 * Testing that the second StockQuote in the List is 1 hour later than the
	 * first StockQuote in the List.
	 */
	@Test
	public void testGetQuoteHistoryPositive2() throws ParseException {
		BasicStockService basicStockService = new BasicStockService();
		
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/20/2019"));
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/21/2019"));
		
		IntervalEnum interval = IntervalEnum.HOURLY;
		
		/*
		 * Call the method we are testing
		 */
		List<StockQuote> listOfQuotes = basicStockService.getQuoteHistory("OLED", calendarStartDate, calendarEndDate, interval);
	
		/*
		 * Get the first date and the next date, We will be comparing them
		 */
		Date startDate = listOfQuotes.get(0).getDateRecorded();
		Date nextDate = listOfQuotes.get(1).getDateRecorded();
		
		/*
		 * Convert java Date type to Joda DateTime type
		 */
		DateTime startDateTime = new DateTime(startDate);
		DateTime nextDateTime = new DateTime(nextDate);
		
		/*
		 * Create a Period object, passing it the two DateTime objects.
		 * 
		 * Essentially, the Period object will represent the time difference/period
		 * when we subtract the first DateTime object from the second DateTime object.
		 * The difference should be a positive 1; i.e. the second DateTime object is
		 * 1 hour later than the first.
		 * 
		 * Note that had we reversed the order of the two DateTime objects, the Period
		 * would be negative 1. In that case we would check for -1 in our assert statement
		 * instead of positive 1.
		 */
		Period diff = new Period(startDateTime, nextDateTime);
			
		/*
		 * With the Period object containing the difference in hours, we call
		 * it's getHours method to get that difference. That difference should be 1 (hour)
		 */
		assertTrue("The second StockQuote should be 1 hour later than the first StockQuote, but is " + diff.getHours() + " later", diff.getHours() == 1);
		
	}
	
	
	/*
	 * Testing based on setting the interval to DAILY; i.e.
	 * Testing that the second StockQuote in the List is 1 day later than the
	 * first StockQuote in the List.
	 */
	@Test
	public void testGetQuoteHistoryPositive3() throws ParseException {
		BasicStockService basicStockService = new BasicStockService();
		
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/20/2019"));
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/21/2019"));
		
		IntervalEnum interval = IntervalEnum.DAILY;
		
		/*
		 * Call the method we are testing
		 */
		List<StockQuote> listOfQuotes = basicStockService.getQuoteHistory("OLED", calendarStartDate, calendarEndDate, interval);
	
		/*
		 * Get the first date and the next date. We will be comparing them
		 */
		Date startDate = listOfQuotes.get(0).getDateRecorded();
		Date nextDate = listOfQuotes.get(1).getDateRecorded();
		
		/*
		 * Convert java Date type to Joda DateTime type
		 */
		DateTime startDateTime = new DateTime(startDate);
		DateTime nextDateTime = new DateTime(nextDate);
		
		/*
		 * Create a Period object, passing it the two DateTime objects.
		 * 
		 * Essentially, the Period object will represent the time difference/period
		 * when we subtract the first DateTime object from the second DateTime object.
		 * The difference should be a positive 1; i.e. the second DateTime object is
		 * 1 day later than the first.
		 * 
		 * Note that had we reversed the order of the two DateTime objects, the Period
		 * would be negative 1. In that case we would check for -1 in our assert statement
		 * instead of positive 1.
		 */
		Period diff = new Period(startDateTime, nextDateTime);
			
		/*
		 * With the Period object containing the difference in days, we need to call
		 * it's getDays method to get the difference in days. That difference should be 1 (day)
		 */
		assertTrue("The second StockQuote should be 1 day later than the first StockQuote, but is " + diff.getDays() + " later", diff.getDays() == 1);
		
	}
	
	
	
	/*
	 * Testing based on setting the interval to WEEKLY; i.e.
	 * Testing that the second StockQuote in the List is 1 week later than the
	 * first StockQuote in the List.
	 */
	@Test
	public void testGetQuoteHistoryPositive4() throws ParseException {
		BasicStockService basicStockService = new BasicStockService();
		
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/20/2019"));
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/27/2019"));
		
		IntervalEnum interval = IntervalEnum.WEEKLY;
		
		/*
		 * Call the method we are testing
		 */
		List<StockQuote> listOfQuotes = basicStockService.getQuoteHistory("OLED", calendarStartDate, calendarEndDate, interval);
	
		/*
		 * Get the first date and the next date, We will be comparing them
		 */
		Date startDate = listOfQuotes.get(0).getDateRecorded();
		Date nextDate = listOfQuotes.get(1).getDateRecorded();
		
		/*
		 * Convert java Date type to Joda DateTime type
		 */
		DateTime startDateTime = new DateTime(startDate);
		DateTime nextDateTime = new DateTime(nextDate);
		
		/*
		 * Create a Period object, passing it the two DateTime objects.
		 * 
		 * Essentially, the Period object will represent the time difference/period
		 * when we subtract the first DateTime object from the second DateTime object.
		 * The difference should be a positive 1; i.e. the second DateTime object is
		 * 1 week later than the first.
		 * 
		 * Note that had we reversed the order of the two DateTime objects, the Period
		 * would be negative 1. In that case we would check for -1 in our assert statement
		 * instead of positive 1.
		 */
		Period diff = new Period(startDateTime, nextDateTime);
			
		/*
		 * With the Period object containing the difference in weeks, we need to call
		 * it's getWeeks method to get the difference in weeks. That difference should be 1 (week)
		 */
		assertTrue("The second StockQuote should be 1 week later than the first StockQuote, but is " + diff.getWeeks() + " later", diff.getWeeks() == 1);	
	
	}
	
	
	/*
	 * Setting interval to WEEKLY and the date range to less than 1 week
	 * Testing that only one StockQuote is in the List
	 * 
	 * Note: The combination of two other test cases in this class probably 
	 * makes this test case redundant, but we'll just leave it
	 */
	@Test
	public void testGetQuoteHistoryNegative2() throws ParseException {
		BasicStockService basicStockService = new BasicStockService();
		
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/20/2019"));
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(new SimpleDateFormat("MM/dd/yyyy").parse("1/26/2019"));
		
		IntervalEnum interval = IntervalEnum.WEEKLY;
		
		/*
		 * Call the method we are testing
		 */
		List<StockQuote> listOfQuotes = basicStockService.getQuoteHistory("OLED", calendarStartDate, calendarEndDate, interval);

		/*
		 * Since the date range (i.e. period from calendarStartDate - calendarEndDate)
		 * is less than a week, only one StockQuote should exist in the List; i.e.
		 * the size of the ListOfQuotes should be 1
		 */
		assertTrue("The ListOfQuotes should contain only one StockQuote, but it contains " + listOfQuotes.size() + " StockQuotes", listOfQuotes.size() == 1);	
	
	}
}
