package edu.garyMooradian.advancedJava;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.http.annotation.Immutable;


/**
 * This class contains information about a stock. It holds stock info for a given point in time.
 * This class in immutable; i.e. No setters, and class variables are private.
 * Immutable simply means that the class cannot be changed; i.e.
 * the class variables of the class cannot be changed.
 * 
 * Of course we need to initialize the class variables, and that is done via
 * the constructor. But once they are initialized, they cannot be changed because
 * the class variables are private and because there are no setter methods, thus,
 * the class is immutable.
 */
@Immutable
public class StockQuote {

    private String stockSymbol;
    private BigDecimal stockPrice;
    private Date dateRecorded;


    /**
     *  CONSTRUCTOR
     *  
     * @param dateRecorded  the date the stock info was recorded.
     * @param stockPrice 	the price of the stock for the provided date
     * @param stockSymbol 	the stock symbol e.g. APPL (for APPLE)
     * 
     * Here we indicate to the client (via @NotNull annotation), not to pass any null values
     * to the constructor.
     * This enforces the coding practice of making sure that when creating an object,
     * it's class variables should be initialized, upon creation, with valid values.
     * This ensures that the object is valid
     */
    public StockQuote(@NotNull Date dateRecorded, @NotNull BigDecimal stockPrice, @NotNull String stockSymbol) {
        this.dateRecorded = dateRecorded;
        this.stockPrice = stockPrice;
        this.stockSymbol = stockSymbol;
    }


    /**
     *
     * @return the symbol that represents the company whose stock this is.
     * e.g. APPL for APPLE
     */
    public String getStockSymbol() {
        return stockSymbol;
    }

    /**
     *
     * @return The price of one share of stock.
     */
    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    /**
     *
     * @return the date of the stock price.
     */
    public Date getDateRecorded() {
        return dateRecorded;
    }
}
