package edu.garyMooradian.advancedJava;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.http.annotation.Immutable;


/*
 * The interface that this class implements, was set to Immutable.
 * Therefore I assume that that establishes this class as Immutable.
 * That said, another class that implements the interface, may have
 * additional methods that make it mutable, so is it a good idea to
 * set Immutable in the interface, or simply do it on a class by class basis?
 * 
 * That said, setting an interface to Immutable is something that is done.
 * So perhaps that indicates that a class that implements the Immutable interface
 * should be designed in a way where it would not become mutable. If that's
 * not do-able, then perhaps it is a sign that the class should not have implemented
 * that interface in the first place. In other words, bad design
 */
public class BasicStockService implements StockService {
	
	//NOTE: Shouldn't the methods in this class be static? i.e. There are no class variables
 
	/*
	 * This method receives a stock symbol and date. It uses that information to
	 * obtain a stock price. With that information, it creates (and returns) a
	 * StockQuote object.
	 * 
	 * Because we are not actually going out to some third party to obtain the 
	 * actual price, we will hard code a price.
	 * NOTE: The symbol and date arguments will be provided via JUnit test
	 */
	@Override
	public StockQuote getQuote(String symbol, Date date) {
		/*
		 * returning StockQuote
		 * Note: new BigDecimal(5.00) is the price
		 */
		return new StockQuote(date, new BigDecimal(5.00), symbol);
	}
	
	
	
	/*
	 * Get a historical list of stock quotes for the provided (stock) symbol
	 * 
	 * @param ​symbol - ​the stock symbol to search for
	 * @param ​from ​- the date of the first stock quote
	 * @param ​until - the date of the last stock quote
	 * @param ​interval ​­ establishes the frequency (e.g. weekly, daily, hourly) of obtaining a stock quote,
	 * within the context of a specific date range.
	 * 
	 * Therefore, the total number of stock quotes obtained will be a combination of the interval
	 * frequency and the date range (i.e. start date -> end date)
	 * 
	 * @return ​ a List of StockQuote instances (i.e. stock quotes) based on the Interval and date range
	 */
	@Override
	public List<StockQuote> getQuoteHistory(String symbol, Calendar from, Calendar until, IntervalEnum interval){
		
		Date date = from.getTime();
		Date endDate = until.getTime();
		List<StockQuote> listOfQuotes= new ArrayList<>();
		
		/*
		 * While the date is before or equal to the end date, keep looping.
		 * Note that the date is initially the start date, but is incremented according
		 * to the interval (per iteration), where interval could be per day, per week, or  per hour
		 * When the date is no longer before or equal to the end date
		 * we terminate the loop
		 * 
		 */
		while(date.before(endDate) || date.equals(endDate)) {
			listOfQuotes.add(getQuote(symbol,date));//Add StockQuote object to list
			
			/*
			 * Determine interval
			 */
			if(interval == IntervalEnum.DAILY) {
				from.add(Calendar.DAY_OF_YEAR, 1);//Add a day to the date
			} else if (interval == IntervalEnum.WEEKLY) {
				from.add(Calendar.WEEK_OF_YEAR, 1);//Add a week to the date
			} else {//IntervalEnum.HOURLY
				from.add(Calendar.HOUR_OF_DAY, 1);//Add an hour to the date (i.e. time)
			}
            date = from.getTime();
		}
		return listOfQuotes;
	}
	
	
}
