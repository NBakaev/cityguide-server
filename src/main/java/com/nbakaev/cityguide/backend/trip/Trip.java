package com.nbakaev.cityguide.backend.trip;

import com.nbakaev.cityguide.backend.entity.RatedBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ya on 5/28/2017.
 */
@ApiModel("Маршрут путешествия")
public class Trip extends RatedBaseEntity {

    @ApiModelProperty("Протяженность, время путушествия маршрута для людей, машин")
    private TripRoute route = new TripRoute();

    @ApiModelProperty("ID POI в порядке маршрута")
    private List<String> poisIds = new ArrayList<>();



    public TripRoute getRoute() {
        return route;
    }

    public void setRoute(TripRoute route) {
        this.route = route;
    }

    public List<String> getPoisIds() {
        return poisIds;
    }

    public void setPoisIds(List<String> poisIds) {
        this.poisIds = poisIds;
    }
}
