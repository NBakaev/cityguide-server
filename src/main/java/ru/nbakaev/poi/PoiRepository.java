package ru.nbakaev.poi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.nbakaev.poi.model.DbPoi;
import ru.nbakaev.poi.model.PoiDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nikita on 10/14/2016.
 */
@Service
public class PoiRepository {

    private final MongoOperations mongoTemplate;

    @Autowired
    public PoiRepository(MongoOperations mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private PoiDto transform(DbPoi dbPoi){
        return new PoiDto(dbPoi.getImage(), dbPoi.getName(), dbPoi.getDescription(), new PoiDto.PoiLocation(dbPoi.getPoint()[0], dbPoi.getPoint()[1]), dbPoi.getId());
    }

    private List<PoiDto> transform(List<DbPoi> dbPois){
        return dbPois.stream().map(this::transform).collect(Collectors.toList());
    }

    private DbPoi transformDB(PoiDto poiDto){
        return new DbPoi(poiDto.getImage(), poiDto.getName(), poiDto.getDescription(), poiDto.getId(), new double[]{poiDto.getLocation().getLatitude(), poiDto.getLocation().getLongitude()});
    }

    private List<DbPoi> transformDB(List<PoiDto> dbPoiDtos){
        return dbPoiDtos.stream().map(this::transformDB).collect(Collectors.toList());
    }

    public List<PoiDto> getAllPoi(){
        return transform(mongoTemplate.findAll(DbPoi.class));
    }

    public PoiDto getPoiById(String id){
        Criteria criteria = new Criteria();
        criteria.and("id").is(id);
        Query query = new Query(criteria);

        return transform(mongoTemplate.findOne(query, DbPoi.class));
    }

//    public GeoResults<DbPoi> getGeoPoiInRadius(PoiDto.PoiLocation location, double radius){
//        NearQuery nearQuery = NearQuery.near(location.getLatitude(), location.getLongitude());
//        nearQuery.maxDistance(new Distance(radius, Metrics.KILOMETERS));
//
//        GeoResults<DbPoi> geoResults = transform(mongoTemplate.geoNear(nearQuery, DbPoi.class));
//
//        return geoResults;
//    }

    public List<PoiDto> getPoiInRadius(PoiDto.PoiLocation location, double radius ){
        NearQuery nearQuery = NearQuery.near(location.getLatitude(), location.getLongitude());
        nearQuery.maxDistance(new Distance(radius, Metrics.KILOMETERS));

        GeoResults<DbPoi> geoResults = mongoTemplate.geoNear(nearQuery, DbPoi.class);

        List<PoiDto> poiDtos = new ArrayList<>();
        geoResults.forEach(x -> poiDtos.add( transform( x.getContent())));

        return poiDtos;
    }

    public PoiDto addPoi(PoiDto poiDto){
        mongoTemplate.insert( transformDB(poiDto));
        return poiDto;
    }

    public PoiDto updatePoi(PoiDto poiDto){
        mongoTemplate.save(transformDB(poiDto));
        return poiDto;
    }

    public void deltePoiById(String id){
        DbPoi poi = new DbPoi();
        poi.setId(id);
        mongoTemplate.remove(poi);
    }

}
