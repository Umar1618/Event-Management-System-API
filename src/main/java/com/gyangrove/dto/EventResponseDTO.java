package com.gyangrove.dto;

import java.time.LocalDate;

public class EventResponseDTO {
	
	private String event_name;
	private String city_name;
	private LocalDate date;
	private String weather;
	private double distance_km;
	
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public double getDistance_km() {
		return distance_km;
	}
	public void setDistance_km(double distance_km) {
		this.distance_km = distance_km;
	}
}
