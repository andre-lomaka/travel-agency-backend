package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.projects.travelagencybackend.model.City;
import sda.projects.travelagencybackend.repository.CityRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
   private final CityRepository cityRepository;

   @Autowired
   public CityController(CityRepository cityRepository) {
      this.cityRepository = cityRepository;
   }

   @GetMapping
   public List<City> getAllCities() {
      return cityRepository.findAll();
   }
}
