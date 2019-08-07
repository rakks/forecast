package com.forecast.service;

import java.util.List;
import java.util.Map;

import com.forecast.model.Forecast;

public interface ForecastService {
	
	public List<Forecast> findAll();
	
	public Map<String, Forecast> getForecastData();

	public Forecast create (Forecast forecast);
}