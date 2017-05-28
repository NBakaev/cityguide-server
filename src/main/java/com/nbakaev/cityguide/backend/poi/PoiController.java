package com.nbakaev.cityguide.backend.poi;

import com.nbakaev.cityguide.backend.poi.model.PoiDto;
import com.nbakaev.cityguide.backend.poi.model.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nikita on 10/14/2016.
 */
@RestController
@RequestMapping(value = "/api/v1/poi")
public class PoiController {

private final PoiRepository poiRepository;

    @Autowired
    public PoiController(PoiRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PoiDto> getAllPoi(){
        return poiRepository.getAllPoi();
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public PoiDto add(){
        return poiRepository.addPoi(new PoiDto());
    }

    @RequestMapping(value = "get/radius/{radius}",method = RequestMethod.GET)
    public List<PoiDto> findInRadius(@PathVariable("radius") String radius){
        PoiDto.PoiLocation poiLocation = new PoiDto.PoiLocation();

        return poiRepository.getPoiInRadius(poiLocation, Double.parseDouble(radius));
    }

    @RequestMapping(value = "city/id/{id}",method = RequestMethod.GET)
    public List<PoiDto> getPoiInCityId(@PathVariable("id") String id){
        return poiRepository.getPoisInCity(id);
    }

    @RequestMapping(value = "id/{id}",method = RequestMethod.GET)
    public PoiDto getPoiById(@PathVariable("id") String id){
        return poiRepository.getPoiById(id);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public PoiDto update(@RequestBody PoiDto poiDto){
        return poiRepository.updatePoi(poiDto);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public PoiDto add(@RequestBody PoiDto poiDto){
        return poiRepository.addPoi(poiDto);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id){
        poiRepository.deletePoiById(id);
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public List<PoiDto> search(@RequestBody SearchRequest searchRequest){
        return poiRepository.getPoiInRadius(searchRequest, searchRequest.getRadius());
    }

}
