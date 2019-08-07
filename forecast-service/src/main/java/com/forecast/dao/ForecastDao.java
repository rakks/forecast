package com.forecast.dao;

import java.util.List;

import com.forecast.model.Forecast;

/**
 * 
 * interface to define Forecast operations
 *
 */
/**
 * @author rakkhura0
 *
 */
public interface ForecastDao {
	
	public List<Forecast> findAll();
	
	public void save(Forecast forecast);
	
	public List<Forecast> find(Forecast forecast);
	
}
