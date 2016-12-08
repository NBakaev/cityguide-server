package ru.nbakaev.poi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 10/14/2016.
 */
@Document
public class DbPoi {

    private String name;
    private String description;
    private String imageUrl;

    private List<String> imageUrls = new ArrayList<>();
    private String videoUrl;

    @Id
    private String id;

    @GeoSpatialIndexed(name="index", type = GeoSpatialIndexType.GEO_2DSPHERE)
    private double[] point;
//    private Point point;

    public DbPoi() {
    }


    public DbPoi(String name, String description, String id, double[] point, String imageUrl, List<String> imageUrls,String videoUrl) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.point = point;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.imageUrls = imageUrls;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
