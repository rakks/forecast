package com.forecast.consumer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.forecast.model.Forecast;
import com.forecast.response.ForecastResponse;
import com.forecast.service.ForecastService;

@Controller
public class ForecastController {

	@Autowired
	private ForecastService forecastService;
	
	private Logger logger = LoggerFactory.getLogger(ForecastController.class);
	
	@RequestMapping(value = "/createForecast", method = RequestMethod.POST)
	public ForecastResponse createForecast(@RequestBody Forecast request)
	{
		ForecastResponse forecastResponse;
		logger.info("Received message '{}'", request);
	try {	
		forecastService.create(request);
		forecastResponse = new ForecastResponse("SUCCESS", "Forecast Successfully created"); 
	} catch(Exception e) { 
		logger.error("Error occured while Forecast Creation");
		forecastResponse = new ForecastResponse("FAILURE", "Failure in creating Forecast");
	}
		return forecastResponse;
	}
	
	@RequestMapping(value = "/getForecasts", method = RequestMethod.GET)
	public ModelAndView getForecasts()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forecast-display");
		Map<String, Forecast> forecastMap = null;
		logger.info("Received message  to get All Forecasts'{}'");
		try {	
			forecastMap = forecastService.getForecastData();
			mav.addObject("forecastMap", forecastMap);
		} catch(Exception e) { 
			logger.error("Error occured while getForecasts()");
			e.printStackTrace();
		}
		return mav;
	}
}
