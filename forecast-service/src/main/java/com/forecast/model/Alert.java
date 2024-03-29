package com.forecast.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * Represents the severe weather warnings issued for the requested location by a governmental authority (please see our data sources page for a list
 * of sources).
 *
 * @author Rakshit
 */
public class Alert implements Serializable {

    private String title;
    private String description;
    private Instant time;
    private Instant expires;
    private List<String> regions;
    private Severity severity;
    private String uri;

    /**
     * @return A brief description of the alert.
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title A brief description of the alert.
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * @return A detailed description of the alert
     */
    public String getDescription() {
	return description;
    }

    /**
     * @param description A detailed description of the alert
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * @return The UNIX time at which the alert was issued.
     */
    public Instant getTime() {
	return time;
    }

    /**
     * @param time The UNIX time at which the alert was issued.
     */
    public void setTime(Instant time) {
	this.time = time;
    }

    /**
     * @return The UNIX time at which the alert will expire. (Some alerts sources, unfortunately, do not define expiration time, and in these cases
     * this parameter will not be defined.)
     */
    public Instant getExpires() {
	return expires;
    }

    /**
     * @param expires The UNIX time at which the alert will expire. (Some alerts sources, unfortunately, do not define expiration time, and in these
     * cases this parameter will not be defined.)
     */
    public void setExpires(Instant expires) {
	this.expires = expires;
    }

    /**
     * @return An HTTP(S) URI that one may refer to for detailed information about the alert.
     */
    public String getUri() {
	return uri;
    }

    /**
     * @param uri An HTTP(S) URI that one may refer to for detailed information about the alert.
     */
    public void setUri(String uri) {
	this.uri = uri;
    }

    /**
     * @return A List of strings representing the names of the regions covered by this weather alert.
     */
    public List<String> getRegions() {
	return regions;
    }

    /**
     * @param regions A List of strings representing the names of the regions covered by this weather alert.
     */
    public void setRegions(List<String> regions) {
	this.regions = regions;
    }

    /**
     * @return The severity of the weather alert.
     */
    public Severity getSeverity() {
	return severity;
    }

    /**
     * @param severity The severity of the weather alert.
     */
    public void setSeverity(Severity severity) {
	this.severity = severity;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 61 * hash + Objects.hashCode(this.title);
	hash = 61 * hash + Objects.hashCode(this.description);
	hash = 61 * hash + Objects.hashCode(this.time);
	hash = 61 * hash + Objects.hashCode(this.expires);
	hash = 61 * hash + Objects.hashCode(this.regions);
	hash = 61 * hash + Objects.hashCode(this.severity);
	hash = 61 * hash + Objects.hashCode(this.uri);
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
	final Alert other = (Alert) obj;
	if (!Objects.equals(this.title, other.title)) {
	    return false;
	}
	if (!Objects.equals(this.description, other.description)) {
	    return false;
	}
	if (!Objects.equals(this.uri, other.uri)) {
	    return false;
	}
	if (!Objects.equals(this.time, other.time)) {
	    return false;
	}
	if (!Objects.equals(this.expires, other.expires)) {
	    return false;
	}
	if (!Objects.equals(this.regions, other.regions)) {
	    return false;
	}
	return this.severity == other.severity;
    }

    public enum Severity {
	/**
	 * an individual should be aware of potentially severe weather
	 */
	advisory,
	/**
	 * an individual should prepare for potentially severe weather
	 */
	watch,
	/**
	 * and individual should take immediate action to protect themselves and others from potentially severe weather
	 */
	warning
    }

}
