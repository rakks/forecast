package com.forecast.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.forecast.dao.ForecastDao;
import com.forecast.model.Forecast;

@Repository
public class ForecastDaoImpl implements ForecastDao{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<Forecast> findAll() {
		List<Forecast> forecasts = mongoTemplate.findAll(Forecast.class);
		return forecasts;
	}
	
	@Override
	public void save(Forecast forecast) {
		mongoTemplate.save(forecast);
	}

	@Override
	public List<Forecast> find(Forecast forecast) {
		List<Forecast> forecasts = new ArrayList<Forecast>();
		Double longitude = forecast.getLongitude().value();
		Double latitude = forecast.getLatitude().value();
		if(longitude != null && latitude !=null) { 
		Query query = new Query();
		
		query.addCriteria(Criteria.where("longitude.value").is(longitude)
				.and("latitude.value").is(latitude)
				.and("currently.time").lte(new Date()));
		forecasts = mongoTemplate.find(query, Forecast.class);
		}
		else { 
			return null;
		}
		return forecasts;
	}
	
	
}