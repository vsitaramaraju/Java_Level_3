package day_3_lab_2;

public class WeatherReport {
    private final double avgTemperature;
    private final double avgHumidity;
    private final double avgWindSpeed;

    public WeatherReport(double avgTemperature, double avgHumidity, double avgWindSpeed) {
        this.avgTemperature = avgTemperature;
        this.avgHumidity = avgHumidity;
        this.avgWindSpeed = avgWindSpeed;
    }

    @Override
    public String toString() {
        return String.format("Weather Report -> Avg Temp: %.2f°C, Avg Humidity: %.2f%%, Avg Wind: %.2f km/h",
                avgTemperature, avgHumidity, avgWindSpeed);
    }
}
