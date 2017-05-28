package com.nbakaev.cityguide.backend.trip;

/**
 * Created by ya on 5/28/2017.
 */
public class TripRoute {


    private double length;
    private double humanTime;
    private double transportTime;
    private double publicTransportTime;


    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHumanTime() {
        return humanTime;
    }

    public void setHumanTime(double humanTime) {
        this.humanTime = humanTime;
    }

    public double getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(double transportTime) {
        this.transportTime = transportTime;
    }

    public double getPublicTransportTime() {
        return publicTransportTime;
    }

    public void setPublicTransportTime(double publicTransportTime) {
        this.publicTransportTime = publicTransportTime;
    }
}
