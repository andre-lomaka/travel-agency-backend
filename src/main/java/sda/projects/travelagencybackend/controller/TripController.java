package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.projects.travelagencybackend.model.Trip;
import sda.projects.travelagencybackend.repository.TripRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {
   private final TripRepository tripRepository;

   @Autowired
   public TripController(TripRepository tripRepository) {
      this.tripRepository = tripRepository;
   }

   @GetMapping
   public List<Trip> getAllTrips() {
      return tripRepository.findAllByDepartureDateGreaterThan(LocalDate.now());
   }

   @GetMapping("/promoted")
   public List<Trip> getPromotedTrips() {
      return tripRepository.findAllByPromotedAndDepartureDateGreaterThan(true, LocalDate.now());
   }

   @GetMapping("/continent/{id}")
   public List<Trip> getTripsByContinent(@PathVariable(name="id") final Long id) {
      return tripRepository.findAllByContinent(id, LocalDate.now());
   }

   @GetMapping("/country/{id}")
   public List<Trip> getTripsByCountry(@PathVariable(name="id") final Long id) {
      return tripRepository.findAllByCountry(id, LocalDate.now());
   }
}
