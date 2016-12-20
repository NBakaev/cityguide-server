package ru.nbakaev.cityguide.backend.poi.model;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.nbakaev.cityguide.backend.entity.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikita on 10/14/2016.
 */
@Document
public class DbPoi extends BaseEntity {

    private String imageUrl;

    private List<String> imageUrls = new ArrayList<>();
    private String videoUrl;

    private String cityId;

    @GeoSpatialIndexed(name="index", type = GeoSpatialIndexType.GEO_2DSPHERE)
    private double[] point;
//    private Point point;

    public DbPoi() {
    }


    public DbPoi(String name, String description, String id, double[] point, String imageUrl, List<String> imageUrls,String videoUrl, String cityId, Date lastUpdate) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.point = point;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.imageUrls = imageUrls;
        this.cityId = cityId;
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

    public void setPoint(double[] point) {
        this.point = point;
    }

    public double[] getPoint() {
        return point;
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
}
