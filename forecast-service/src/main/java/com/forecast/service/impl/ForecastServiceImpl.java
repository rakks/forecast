package com.forecast.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.forecast.dao.ForecastDao;
import com.forecast.model.Forecast;
import com.forecast.model.GeoCoordinates;
import com.forecast.service.ForecastService;
import com.forecast.util.LocationMap;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ForecastServiceImpl implements ForecastService{
	
	Logger log = LoggerFactory.getLogger(ForecastService.class);
	
	private String apiKey = "ccbc2275e3b82e163522a02d143a5204";

	@Autowired
	private ForecastDao forecastDao;
	
	@Override
	public List<Forecast> findAll() {
		// TODO Auto-generated method stub
		return forecastDao.findAll();
	}

	@Override
	@HystrixCommand(fallbackMethod = "create_Fallback")
	public Forecast create(Forecast forecast) {
		// TODO Auto-generated method stub
		log.info("Forecast Save Method called. Trade : ", forecast);
		forecastDao.save(forecast);
		return forecast;
	}

    @SuppressWarnings("unused")
    public Forecast create_Fallback(Forecast forecast) {
    	log.error("CIRCUIT BREAKER ENABLED!!! No Response From Forecast Service at this moment. " +
    	" Service will be back shortly - " + new Date());
    	return forecast;
    }
    

    public Map<String, Forecast> getForecastData() { 
    	Map<String, Forecast> forecastMap = new HashMap<String, Forecast>();
    	Forecast forecast = new Forecast();
    	Map<String, GeoCoordinates> locationMap = LocationMap.getLocationMap();
    	 Iterator it = locationMap.entrySet().iterator();
    	 while (it.hasNext()) {
    	        Map.Entry pair = (Map.Entry)it.next();
    	        log.info("Location : " + pair.getKey());
    	        GeoCoordinates geo = (GeoCoordinates)pair.getValue();
    	        Instant now = Instant.EPOCH;
    	       
    	        forecast.setLongitude(geo.longitude());
    	        forecast.setLatitude(geo.latitude());
    	        //Check whether the data exists in Database
    	        /*List<Forecast> forecasts = forecastDao.findByLongitudeAndLatitudeAndCurrently(geo.longitude().value(), 
    	        		geo.latitude().value(), getToday());*/
    	        List<Forecast> forecasts = forecastDao.find(forecast);
    	        // If Forecast not there in database, then fetch data using Darksky service 
    	        // and save the data in Database.
    	        if(forecasts == null || forecasts.size() == 0) { 
    	        	forecast = getForecastByDarksky(geo);
    	        	forecastDao.save(forecast);
    	        	log.info(pair.getKey() + " saved successfully");
    	        }
    	        else { 
    	        	log.info(pair.getKey() + " found in database, so not calling Darksky service");
    	        	forecast = forecasts.get(0);
    	        }
    	        //set the forecast data in map for showing it on UI
    	        forecastMap.put((String)pair.getKey(), forecast);
    	 }	
		return forecastMap;
    }
    
    private Forecast getForecastByDarksky(GeoCoordinates geo) { 
    	
    	Double longitude = geo.longitude().value();
    	Double latitude = geo.latitude().value();
    	
    	
    	RestTemplate restTemplate = new RestTemplate();
    	log.info("Calling darkskyService with parameters : APikey : "+apiKey + "\t longitude : "+longitude + "\t latitude : "+latitude);
		String baseUrl = "https://api.darksky.net/forecast";
		String restUrl = baseUrl+"/"+apiKey+"/"+latitude+","+longitude;
		Forecast forecast = restTemplate.getForObject(restUrl, Forecast.class);
    	//Forecast forecast = getForecastDummyData(geo);
		log.info("darkskyService successful response" + forecast);
    	return forecast;
    }
    
    
/*    private Forecast getForecastDummyData(GeoCoordinates geo) { 
    	Forecast forecast = new Forecast();
    	if(geo.longitude() != null) { 
    		forecast.setLongitude(geo.longitude());
    	} else { 
    		forecast.setLongitude(new Longitude(-89.00));
    	}
    	if(geo.latitude() != null) { 
    		forecast.setLatitude(geo.latitude());
    	} else {
    		forecast.setLatitude(new Latitude(60.00));
    	}
    	Currently currently = new Currently();
    	currently.setTime(Instant.now());
    	forecast.setCurrently(currently);
    	
    	Daily daily = new Daily();
    	DailyDataPoint dailyDataPoint = new DailyDataPoint();
    	dailyDataPoint.setTemperatureLow(20.00);
    	dailyDataPoint.setTemperatureHigh(29.00);

    	List<DailyDataPoint> dailyDataPoints = new ArrayList<DailyDataPoint>();
    	dailyDataPoints.add(dailyDataPoint);
    	
    	daily.setData(dailyDataPoints);
    	forecast.setDaily(daily); 
    	return forecast;
    }*/

}
