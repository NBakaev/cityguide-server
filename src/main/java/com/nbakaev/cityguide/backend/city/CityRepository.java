package com.nbakaev.cityguide.backend.city;

import com.nbakaev.cityguide.backend.poi.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.nbakaev.cityguide.backend.poi.model.PoiDto;

import java.util.Date;
import java.util.List;

/**
 * Created by Nikita on 10/14/2016.
 */
@Service
public class CityRepository {

    private final MongoOperations mongoTemplate;
    private PoiRepository poiRepository;

    @Autowired
    public CityRepository(MongoOperations mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * recount number of pois in city
     */
    public void recountAndSaveAllCitiesPoisNumbers() {
        List<City> allCities = this.getAllCities();
        for (City city : allCities) {
            city.setPois(countPoisInCity(city));
            mongoTemplate.save(city);

            List<PoiDto> poisInCity = getPoisInCity(city);
            for (PoiDto poiDto : poisInCity) {
                poiDto.setCityId(city.getId());
                poiDto.setLastUpdate(new Date());
                poiRepository.updatePoi(poiDto, false);
            }
        }
    }

    public List<PoiDto> getPoisInCity(City city) {
        return poiRepository.getPoiInRadius(city.getLocation(), city.getApproximateRadius());
    }

    private long countPoisInCity(City city) {
        if (city.getApproximateRadius() == 0 || city.getLocation() == null) {
            return 0;
        }

        List<PoiDto> poiInRadius = poiRepository.getPoiInRadius(city.getLocation(), city.getApproximateRadius());
        return poiInRadius.size();
    }

    public List<City> getAllCities() {
        return mongoTemplate.findAll(City.class);
    }

    public City getCityById(String id) {
        Criteria criteria = new Criteria();
        criteria.and("id").is(id);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, City.class);
    }

    public City addCity(City poiDto) {
        poiDto.setPois(countPoisInCity(poiDto));
        poiDto.setLastUpdate(new Date());

        if (poiDto.getApproximateRadius() < 1){
            poiDto.setApproximateRadius(100);
        }

        mongoTemplate.insert(poiDto);
        recountAndSaveAllCitiesPoisNumbers();

        // to get pois number
        return getCityById(poiDto.getId());
    }

    public City updateCity(City poiDto) {
        poiDto.setPois(countPoisInCity(poiDto));
        poiDto.setLastUpdate(new Date());
        mongoTemplate.save(poiDto);
        recountAndSaveAllCitiesPoisNumbers();
        return poiDto;
    }

    public void deleteCityById(String id) {
        City city = new City();
        city.setId(id);

        List<PoiDto> poisInCity = getPoisInCity(city);

        List<PoiDto> poisInCityCache = poiRepository.getPoisInCity(id);
        for (PoiDto dto : poisInCityCache) {
            if (!poisInCity.contains(dto)){
                poisInCity.add(dto);
            }
        }

        for (PoiDto poiDto : poisInCity) {
            poiDto.setCityId(null);
            poiRepository.updatePoi(poiDto, false);
        }

        mongoTemplate.remove(city);
        recountAndSaveAllCitiesPoisNumbers();
    }

    @Autowired
    public void setPoiRepository(PoiRepository poiRepository) {
        this.poiRepository = poiRepository;
    }
}
