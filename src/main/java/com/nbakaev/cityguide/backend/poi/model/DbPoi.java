package com.nbakaev.cityguide.backend.poi.model;

import com.nbakaev.cityguide.backend.entity.ExternalResourceLink;
import com.nbakaev.cityguide.backend.entity.GuideContent;
import com.nbakaev.cityguide.backend.entity.RatedBaseEntity;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.util.Date;

/**
 * internal Poi representation for database store
 *
 * Created by Nikita on 10/14/2016.
 */
public class DbPoi extends RatedBaseEntity{

    private String cityId;

    private GuideContent content = new GuideContent();

    private ExternalResourceLink externalResourceLink = new ExternalResourceLink();

    @GeoSpatialIndexed(name="index", type = GeoSpatialIndexType.GEO_2DSPHERE)
    private double[] point;
//    private Point point;

    public DbPoi() {
    }

    public DbPoi(String name, String description, String id, double[] point, GuideContent guideContent, String cityId, Date lastUpdate,
                 double rating, ExternalResourceLink externalResourceLink) {
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.id = id;
        this.point = point;
        this.cityId = cityId;
        this.lastUpdate = lastUpdate;
        this.externalResourceLink = externalResourceLink;
        this.content = guideContent;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public GuideContent getContent() {
        return content;
    }

    public void setContent(GuideContent content) {
        this.content = content;
    }

    public ExternalResourceLink getExternalResourceLink() {
        return externalResourceLink;
    }

    public void setExternalResourceLink(ExternalResourceLink externalResourceLink) {
        this.externalResourceLink = externalResourceLink;
    }

    public double[] getPoint() {
        return point;
    }

    public void setPoint(double[] point) {
        this.point = point;
    }
}
