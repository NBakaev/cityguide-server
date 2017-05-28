package com.nbakaev.cityguide.backend.poi;

import com.nbakaev.cityguide.backend.poi.model.DbPoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.nbakaev.cityguide.backend.city.CityRepository;
import com.nbakaev.cityguide.backend.poi.model.PoiDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nikita on 10/14/2016.
 */
@Service
public class PoiRepository {

    private final MongoOperations mongoTemplate;
    private CityRepository cityRepository;

    @Autowired
    public PoiRepository(MongoOperations mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private PoiDto transform(DbPoi dbPoi) {
        return new PoiDto(dbPoi.getName(), dbPoi.getDescription(),
                new PoiDto.PoiLocation(dbPoi.getPoint()[0], dbPoi.getPoint()[1]),
                dbPoi.getId(), dbPoi.getCityId(), dbPoi.getLastUpdate(), dbPoi.getRating(), dbPoi.getContent(), dbPoi.getExternalResourceLink());
    }

    private List<PoiDto> transform(List<DbPoi> dbPois) {
        return dbPois.stream().map(this::transform).collect(Collectors.toList());
    }

    private DbPoi transformDB(PoiDto poiDto) {
        return new DbPoi(poiDto.getName(), poiDto.getDescription(), poiDto.getId(), new double[]{poiDto.getLocation().getLatitude(), poiDto.getLocation().getLongitude()}, poiDto.getContent(),
                 poiDto.getCityId(), poiDto.getLastUpdate(), poiDto.getRating(), poiDto.getExternalResourceLink());
    }

    private List<DbPoi> transformDB(List<PoiDto> dbPoiDtos) {
        return dbPoiDtos.stream().map(this::transformDB).collect(Collectors.toList());
    }

    public List<PoiDto> getAllPoi() {
        return transform(mongoTemplate.findAll(DbPoi.class));
    }

    public PoiDto getPoiById(String id) {
        Criteria criteria = new Criteria();
        criteria.and("id").is(id);
        Query query = new Query(criteria);

        return transform(mongoTemplate.findOne(query, DbPoi.class));
    }

    public List<PoiDto> getPoisInCity(String id) {
        Criteria criteria = new Criteria();
        criteria.and("cityId").is(id);
        Query query = new Query(criteria);

        return transform(mongoTemplate.find(query, DbPoi.class));
    }

    public List<PoiDto> getPoiInRadius(PoiDto.PoiLocation location, double radius) {
        NearQuery nearQuery = NearQuery.near(location.getLatitude(), location.getLongitude());

        radius = radius * 0.001; // convert from metres to KILOMETERS
        nearQuery.maxDistance(new Distance(radius, Metrics.KILOMETERS));

        GeoResults<DbPoi> geoResults = mongoTemplate.geoNear(nearQuery, DbPoi.class);

        List<PoiDto> poiDtos = new ArrayList<>();
        geoResults.forEach(x -> poiDtos.add(transform(x.getContent())));

        return poiDtos;
    }

    public PoiDto addPoi(PoiDto poiDto) {
        poiDto.setLastUpdate(new Date());
        DbPoi o = transformDB(poiDto);
        mongoTemplate.insert(o);
        cityRepository.recountAndSaveAllCitiesPoisNumbers();
        // transform to get created db id etc
        return transform(o);
    }

    public PoiDto updatePoi(PoiDto poiDto) {
        return updatePoi(poiDto, true);
    }

    public PoiDto updatePoi(PoiDto poiDto, boolean recount) {
        poiDto.setLastUpdate(new Date());
        mongoTemplate.save(transformDB(poiDto));

        if (recount){
            cityRepository.recountAndSaveAllCitiesPoisNumbers();
        }
        return poiDto;
    }

    public void deletePoiById(String id) {
        DbPoi poi = new DbPoi();
        poi.setId(id);
        mongoTemplate.remove(poi);
        cityRepository.recountAndSaveAllCitiesPoisNumbers();
    }

    @Autowired
    public void setCityRepository(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
}
