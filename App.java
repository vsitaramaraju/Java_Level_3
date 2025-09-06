package day_3_lab_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class App {
	   private static final Logger logger = LoggerFactory.getLogger(App.class);

	    public static void main(String[] args) {
	        List<WeatherService> services = Arrays.asList(
	                new WeatherService("WeatherAPI-1"),
	                new WeatherService("WeatherAPI-2"),
	                new WeatherService("WeatherAPI-3")
	        );

	        WeatherAggregator aggregator = new WeatherAggregator();

	        try {
	            WeatherReport report = aggregator.aggregate(services);
	            logger.info("Final Weather Report generated successfully.");
	            System.out.println(report);
	        } catch (WeatherDataUnavailableException e) {
	            logger.error("Failed to generate Weather Report: {}", e.getMessage());
	            System.err.println("Error: " + e.getMessage());
	        }
	    }
}
