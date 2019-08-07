package com.forecast.util;

import java.util.HashMap;
import java.util.Map;

import com.forecast.model.GeoCoordinates;
import com.forecast.model.Latitude;
import com.forecast.model.Longitude;

public class LocationMap {

	private static Map<String, GeoCoordinates> locationMap;
	
	private LocationMap() { 
	}

	public static Map<String, GeoCoordinates> getLocationMap() { 
		if(locationMap == null) {
			locationMap = new HashMap<String, GeoCoordinates>();
			locationMap.put("Campbell,CA", new GeoCoordinates(new Longitude(-121.9488889), new Latitude(37.2872222)));
			locationMap.put("Omaha, NE", new GeoCoordinates(new Longitude(-95.995102), new Latitude(41.257160)));
			locationMap.put("Austin, TX", new GeoCoordinates(new Longitude(-97.750519), new Latitude(30.266926)));
			locationMap.put("Niseko, Japan", new GeoCoordinates(new Longitude(140.6690300), new Latitude(42.7787100)));
			locationMap.put("Nara, Japan", new GeoCoordinates(new Longitude(135.8048500), new Latitude(34.6850500)));
			locationMap.put("Jakarta, Indonesia", new GeoCoordinates(new Longitude(106.8451300), new Latitude(-6.2146200)));
		}
		return locationMap;
	}
}
