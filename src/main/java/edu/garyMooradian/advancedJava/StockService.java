package edu.garyMooradian.advancedJava;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.annotation.Immutable;

@Immutable
public interface StockService {
		
	/*
	 * returns a stock quote, based on a stockSymbol and Date
	 */
	public StockQuote getQuote(String symbol, Date date);
	
	
	/*
	 * Get a historical list of stock quotes for the provide symbol
	 * This method will return one StockQuote per interval specified.
	 * 
	 * @param ​symbol - ​the stock symbol to search for
	 * @param ​from ​- the date of the first stock quote
	 * @param ​until - the date of the last stock quote
	 * @param ​interval ​­ indirectly establishes the frequency of obtaining stock quotes, within the
	 * context of a date range. The interval options (Daily, Weekly etc...) are established by 
	 * the enum called IntervalEnum in StockQuoteApllication class
	 * 
	 * @return ​a List of StockQuote instances (i.e. stock quotes) based on the Interval and date range
	 */
	public List<StockQuote> getQuoteHistory(String symbol, Calendar from, Calendar until, IntervalEnum interval);

	
}
