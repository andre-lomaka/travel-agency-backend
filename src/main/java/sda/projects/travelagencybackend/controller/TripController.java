package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

   @GetMapping("/promoted")
   public List<Trip> getPromotedTrips() {
      return tripRepository.findAllByPromotedAndDepartureDateGreaterThan(true, LocalDate.now());
   }

   @GetMapping
   public List<Trip> getAllTrips(@RequestParam(name="continent", required = false) final Long continentId,
                                 @RequestParam(name="country", required = false) final Long countryId,
                                 @RequestParam(name="fromCity", required = false) final Long fromCityId,
                                 @RequestParam(name="toCity", required = false) final Long toCityId,
                                 @RequestParam(name="bbt", required = false) final Long bbtId) {
      if (continentId != null)
         return tripRepository.findAllByContinent(continentId, LocalDate.now());
      else if (countryId != null)
         return tripRepository.findAllByCountry(countryId, LocalDate.now());
      else if (fromCityId != null)
         return tripRepository.findAllByFromCity(fromCityId, LocalDate.now());
      else if (toCityId != null)
         return tripRepository.findAllByToCity(toCityId, LocalDate.now());
      else if (bbtId != null)
         return tripRepository.findAllByBbt(bbtId, LocalDate.now());
      return tripRepository.findAllByDepartureDateGreaterThan(LocalDate.now());
   }
}
