package ru.nbakaev.cityguide.backend.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nikita on 10/14/2016.
 */
@RestController
@RequestMapping(value = "/api/v1/city")
public class CityController {

    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<City> getAllCity() {
        return cityRepository.getAllCities();
    }

    @RequestMapping(value = "id/{id}", method = RequestMethod.GET)
    public City getCityById(@PathVariable("id") String id) {
        return cityRepository.getCityById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public City update(@RequestBody City city) {
        return cityRepository.updateCity(city);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public City add(@RequestBody City city) {
        return cityRepository.addCity(city);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        cityRepository.deleteCityById(id);
    }

}
