package day_3_lab_2;

public class WeatherData {
    private final double temperature;
    private final double humidity;
    private final double windSpeed;

    public WeatherData(double temperature, double humidity, double windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String toString() {
        return String.format("Temp=%.2f°C, Humidity=%.2f%%, Wind=%.2f km/h",
                temperature, humidity, windSpeed);
    }
}
