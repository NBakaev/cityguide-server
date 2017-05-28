package com.nbakaev.cityguide.backend.entity;

/**
 * Created by ya on 5/28/2017.
 */
public class ExternalResourceLink {

    private String googlePlaceId;
    // TODO: foursquare, yandex maps

    public String getGooglePlaceId() {
        return googlePlaceId;
    }

    public void setGooglePlaceId(String googlePlaceId) {
        this.googlePlaceId = googlePlaceId;
    }
}
