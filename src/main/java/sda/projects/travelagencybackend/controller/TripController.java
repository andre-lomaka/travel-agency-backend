package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sda.projects.travelagencybackend.model.Trip;
import sda.projects.travelagencybackend.repository.TripRepository;

import java.time.LocalDate;
import java.util.HashMap;
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
                                 @RequestParam(name="bbt", required = false) final Long bbtId,
                                 @RequestParam(name="hotelStars", required = false) final Integer numberOfStars,
                                 @RequestParam(name="departureDate", required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) final LocalDate departureDate,
                                 @RequestParam(name="returnDate", required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) final LocalDate returnDate) {
      HashMap<String, Object> data = new HashMap<>();
      if (fromCityId != null)
         data.put("fromCity", fromCityId);
      if (toCityId != null)
         data.put("toCity", toCityId);
      if (bbtId != null)
         data.put("boardBasisType", bbtId);
      if (numberOfStars != null)
         data.put("toHotel", numberOfStars);
      if (departureDate != null)
         data.put("departureDate", departureDate);
      if (returnDate != null)
         data.put("returnDate", returnDate);
      if (data.size() > 0) return tripRepository.findAllByConditions(data);
      if (continentId != null)
         return tripRepository.findAllByContinent(continentId, LocalDate.now());
      else if (countryId != null)
         return tripRepository.findAllByCountry(countryId, LocalDate.now());
      return tripRepository.findAllByDepartureDateGreaterThan(LocalDate.now());
   }
}
