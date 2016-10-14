package ru.nbakaev.poi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Nikita on 10/14/2016.
 */
@Document
public class DbPoi {

    private byte[] image;
    private String name;
    private String description;

    @Id
    private String id;

    @GeoSpatialIndexed(name="index", type = GeoSpatialIndexType.GEO_2DSPHERE)
    private double[] point;
//    private Point point;

    public DbPoi() {
    }


    public DbPoi(byte[] image, String name, String description, String id, double[] point) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.id = id;
        this.point = point;
    }

    public void setPoint(double[] point) {
        this.point = point;
    }

    public double[] getPoint() {
        return point;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

}
