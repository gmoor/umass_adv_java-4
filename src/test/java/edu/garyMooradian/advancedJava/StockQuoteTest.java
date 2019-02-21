package edu.garyMooradian.advancedJava;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

public class StockQuoteTest {

	/*
	 * Testing the constructor and the getter methods
	 * getRecordedDate(), getStockPrice(), getStockSymbol() 
	 */
	@Test
	public void testStockQuote() {
		//Establish test values
		Date dateRecorded = new Date(2018, 01, 11);
		BigDecimal stockPrice = new BigDecimal(4.11);
		String stockSymbol = "OLED";
		
		//Call StockQuote constructor, passing it the test values
		StockQuote stockQuote = new StockQuote(dateRecorded,stockPrice,stockSymbol);
		
		/*
		 * Verify that the dateRecorded value passed to the constructor was assigned to
		 * the corresponding class variable
		 */
		assertEquals("The class variable dateRecorded, does not equal it's assigned value",
						dateRecorded,stockQuote.getDateRecorded());
		
		/*
		 * Verify that the stockPrice value passed to the constructor was assigned to
		 * the corresponding class variable
		 */
		assertEquals("The class variable stockPrice, does not equal it's assigned value",
						stockPrice,stockQuote.getStockPrice());
		
		
		/*
		 * Verify that the stockSymbol value passed to the constructor was assigned to
		 * the corresponding class variable
		 */
		assertEquals("The class variable stockSymbol, does not equal it's assigned value",
						stockSymbol,stockQuote.getStockSymbol());
			
	}

}
