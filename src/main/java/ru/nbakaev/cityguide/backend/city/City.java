package ru.nbakaev.cityguide.backend.city;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.nbakaev.cityguide.backend.entity.BaseEntity;
import ru.nbakaev.cityguide.backend.poi.model.PoiDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ya on 12/20/2016.
 */
@Document
public class City extends BaseEntity {

    private String imageUrl;

    private List<String> imageUrls = new ArrayList<>();
    private String videoUrl;

    private long pois = 0;

    private double approximateRadius = 0;
    private PoiDto.PoiLocation location = new PoiDto.PoiLocation();


    public double getApproximateRadius() {
        return approximateRadius;
    }

    public void setApproximateRadius(double approximateRadius) {
        this.approximateRadius = approximateRadius;
    }

    public PoiDto.PoiLocation getLocation() {
        return location;
    }

    public void setLocation(PoiDto.PoiLocation location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public long getPois() {
        return pois;
    }

    public void setPois(long pois) {
        this.pois = pois;
    }
}
