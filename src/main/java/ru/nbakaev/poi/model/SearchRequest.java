package ru.nbakaev.poi.model;

/**
 * Created by Nikita on 10/14/2016.
 */
public class SearchRequest extends PoiDto.PoiLocation {

    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
