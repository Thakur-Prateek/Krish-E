package com.example.krishe;

public class WeatherRVModal {

    private String time;
    private String temperature;
    private String icon;
    private  String WindSpeed;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        WindSpeed = windSpeed;
    }

    public WeatherRVModal(String time, String temperature, String icon, String windSpeed) {
        this.time = time;
        this.temperature = temperature;
        this.icon = icon;
        WindSpeed = windSpeed;
    }
}
