package edu.garyMooradian.advancedJava;

import org.apache.http.annotation.Immutable;

/*
 * This class will return an instantiation of a specific StockService (i.e. BasicStockService)
 * BasicStockService implements StockService, therefore, we can return
 * BasicStockService as StockService type
 */
@Immutable
public class StockServiceFactory {
	
	/*
	 * We call this method getStockService because it returns any implementation
	 * of StockService; i.e. we don't want to specify a particular implementation
	 * because there could be more than one.
	 * In this limited case we just so happen to be able to return just one
	 * implementation; i.e. BasicStockService
	 */
	public static StockService getStockService() {
		/*
		 * If there were multiple StcokService types, we would need to pass in
		 * an arg to this method that would specify which StockService to return
		 * using conditional statements
		 */
		return new BasicStockService();
	}

}
