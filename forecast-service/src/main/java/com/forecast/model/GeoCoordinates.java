
package com.forecast.model;

import java.util.Objects;

/**
 * Represents GeoCoordinates for a location.
 *
 * @author Rakshit
 */
public class GeoCoordinates {

    private final Longitude longitude;
    private final Latitude latitude;

    /**
     * @param longitude The Longitude of the place represented by this GeoCoordinate.
     * @param latitude The Latitude of the place represented by this GeoCoordinate.
     */
    public GeoCoordinates(Longitude longitude, Latitude latitude) {
	this.longitude = longitude;
	this.latitude = latitude;
    }

    /**
     * @return The Longitude of the place represented by this GeoCoordinate.
     */
    public Longitude longitude() {
	return longitude;
    }

    /**
     * @return The Latitude of the place represented by this GeoCoordinate.
     */
    public Latitude latitude() {
	return latitude;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 89 * hash + Objects.hashCode(this.longitude);
	hash = 89 * hash + Objects.hashCode(this.latitude);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final GeoCoordinates other = (GeoCoordinates) obj;
	if (!Objects.equals(this.longitude, other.longitude)) {
	    return false;
	}
	return Objects.equals(this.latitude, other.latitude);
    }

}
