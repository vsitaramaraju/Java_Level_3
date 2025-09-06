package day_3_lab_2;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherService {
	 private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	    private static final Random random = new Random();

	    private final String serviceName;

	    public WeatherService(String serviceName) {
	        this.serviceName = serviceName;
	    }

	    public CompletableFuture<WeatherData> fetchWeatherAsync() {
	        return CompletableFuture.supplyAsync(() -> {
	            try {
	                long delay = 500 + random.nextInt(1500);
	                Thread.sleep(delay);

	                if (random.nextDouble() < 0.3) {
	                    throw new RuntimeException("Service " + serviceName + " failed due to network error.");
	                }

	                WeatherData data = new WeatherData(
	                        15 + random.nextDouble() * 15,  
	                        40 + random.nextDouble() * 30, 
	                        5 + random.nextDouble() * 20    
	                );

	                logger.info("{} responded successfully: {}", serviceName, data);
	                return data;

	            } catch (Exception e) {
	                logger.error("{} failed: {}", serviceName, e.getMessage());
	                throw new RuntimeException(e);
	            }
	        });
	    }
}
