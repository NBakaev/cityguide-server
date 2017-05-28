package com.nbakaev.cityguide.backend.city;

import com.nbakaev.cityguide.backend.entity.GuideContent;
import com.nbakaev.cityguide.backend.entity.RatedBaseEntity;
import com.nbakaev.cityguide.backend.poi.model.PoiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ya on 12/20/2016.
 */
@ApiModel("Город")
public class City extends RatedBaseEntity {

    @ApiModelProperty("Число POI в данном городе")
    private long pois = 0;

    private GuideContent content = new GuideContent();

    // используется чтобы определить принадлежность POI к городу
    @ApiModelProperty("Примерный радиус города в метрах")
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

    public GuideContent getContent() {
        return content;
    }

    public void setContent(GuideContent content) {
        this.content = content;
    }

    public long getPois() {
        return pois;
    }

    public void setPois(long pois) {
        this.pois = pois;
    }
}
