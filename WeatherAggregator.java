package day_3_lab_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class WeatherAggregator {
	private static final Logger logger = LoggerFactory.getLogger(WeatherAggregator.class);

    public WeatherReport aggregate(List<WeatherService> services) throws WeatherDataUnavailableException {
        List<CompletableFuture<WeatherData>> futures = services.stream()
                .map(WeatherService::fetchWeatherAsync)
                .collect(Collectors.toList());

        List<WeatherData> results = futures.stream()
                .map(future -> {
                    try {
                        return future.join(); 
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(data -> data != null)
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            throw new WeatherDataUnavailableException("All weather services failed. No data available.");
        }

        if (results.size() < services.size()) {
            logger.warn("Partial results received: {} out of {} services responded.", results.size(), services.size());
        }

        double avgTemp = results.stream().mapToDouble(WeatherData::getTemperature).average().orElse(0);
        double avgHumidity = results.stream().mapToDouble(WeatherData::getHumidity).average().orElse(0);
        double avgWind = results.stream().mapToDouble(WeatherData::getWindSpeed).average().orElse(0);

        return new WeatherReport(avgTemp, avgHumidity, avgWind);
    }
}
