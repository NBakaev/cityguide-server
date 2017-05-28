package com.nbakaev.cityguide.backend.entity;

/**
 * BaseEntity that can be rated
 *
 * Created by ya on 5/28/2017.
 */
public class RatedBaseEntity extends BaseEntity {

    protected double rating;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
