package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.projects.travelagencybackend.model.Airport;
import sda.projects.travelagencybackend.repository.AirportRepository;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@CrossOrigin(origins = "http://localhost:8080")
public class AirportController {
   private final AirportRepository airportRepository;

   @Autowired
   public AirportController(AirportRepository airportRepository) {
      this.airportRepository = airportRepository;
   }

   @GetMapping
   public List<Airport> getAllAirports() {
      return airportRepository.findAll();
   }
}
