package ru.nbakaev.cityguide.backend.poi.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikita on 10/14/2016.
 */

public class PoiDto {

    private String name;
    private String description;
    private String imageUrl;
    private List<String> imageUrls = new ArrayList<>();
    private String videoUrl;

    private PoiLocation location = new PoiLocation();
    private String id;

    @ApiModelProperty("Id of city. Can be null")
    private String cityId;
    private Date lastUpdate;
    private double rating;


    public PoiDto() {
    }

    public PoiDto(String name, String description, PoiLocation location, String id, String imageUrl, List<String> imageUrls, String videoUrl, String cityId, Date lastUpdate, double rating) {
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.location = location;
        this.id = id;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.imageUrls = imageUrls;
        this.cityId = cityId;
        this.lastUpdate = lastUpdate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PoiLocation getLocation() {
        return location;
    }

    public void setLocation(PoiLocation location) {
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    static public class PoiLocation {

        public PoiLocation() {
        }

        public PoiLocation(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        protected double latitude = 0.0;
        protected double longitude = 0.0;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PoiLocation that = (PoiLocation) o;

            if (Double.compare(that.latitude, latitude) != 0) return false;
            if (Double.compare(that.longitude, longitude) != 0) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(latitude);
            result = (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(longitude);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }

}
