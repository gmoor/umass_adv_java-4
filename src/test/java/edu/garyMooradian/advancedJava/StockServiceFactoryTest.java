package edu.garyMooradian.advancedJava;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class StockServiceFactoryTest {

	@Test
	public void testGetStockService() {
		//Call the method statically
		StockService stockService = StockServiceFactory.getStockService();
		assertNotNull("The object reference value is NULL", stockService);
	}

}
