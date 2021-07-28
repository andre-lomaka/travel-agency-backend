package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.projects.travelagencybackend.model.*;
import sda.projects.travelagencybackend.repository.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@RequestMapping("/api/trips")
public class TripController {
   private final TripRepository tripRepository;
   private final BoardBasisTypeRepository boardBasisTypeRepository;
   private final CityRepository cityRepository;
   private final AirportRepository airportRepository;
   private final HotelRepository hotelRepository;

   @Autowired
   public TripController(TripRepository tripRepository,
                         BoardBasisTypeRepository boardBasisTypeRepository,
                         CityRepository cityRepository,
                         AirportRepository airportRepository,
                         HotelRepository hotelRepository) {
      this.tripRepository = tripRepository;
      this.boardBasisTypeRepository = boardBasisTypeRepository;
      this.cityRepository = cityRepository;
      this.airportRepository = airportRepository;
      this.hotelRepository = hotelRepository;
   }

   @GetMapping("/promoted")
   public List<Trip> getPromotedTrips() {
      return tripRepository.findAllByPromotedAndDepartureDateGreaterThan(true, LocalDate.now());
   }

   @GetMapping("/{id}")
   public Trip getTripById(@PathVariable(name="id") final Long id) {
      return tripRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Trip not found"));
   }

   @GetMapping
   public List<Trip> getAllTrips(@RequestParam(name="continent", required = false) final Long continentId,
                                 @RequestParam(name="country", required = false) final Long countryId,
                                 @RequestParam(name="fromCity", required = false) final Long fromCityId,
                                 @RequestParam(name="toCity", required = false) final Long toCityId,
                                 @RequestParam(name="bbt", required = false) final Long bbtId,
                                 @RequestParam(name="hotelStars", required = false) final Integer numberOfStars,
                                 @RequestParam(name="vacancies", required = false) final Integer vacancies,
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
      if (vacancies != null)
         data.put("vacancies", vacancies);
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

   @PostMapping
   public ResponseEntity<Trip> createTrip(@RequestBody final Trip trip) {
      trip.setId(null);
      if (!validateDates(trip)) throw new ControllerConflictException("Invalid dates");
      Trip t = tripRepository.save(trip);
      return ResponseEntity.created(URI.create(String.format("/api/trips/%s", t.getId()))).body(t);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Trip> updateTrip(@RequestBody final Trip trip, @PathVariable(name="id") final Long id) {
      Trip t = tripRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Trip not found"));
      BoardBasisType bbt = boardBasisTypeRepository.getById(trip.getBoardBasisType().getId());
      City fc = cityRepository.getById(trip.getFromCity().getId());
      Airport fa = airportRepository.getById(trip.getFromAirport().getId());
      City tc = cityRepository.getById(trip.getToCity().getId());
      Airport ta = airportRepository.getById(trip.getToAirport().getId());
      Hotel h = hotelRepository.getById(trip.getToHotel().getId());
      t.setVacancies(trip.getVacancies());
      t.setDepartureDate(trip.getDepartureDate());
      t.setReturnDate(trip.getReturnDate());
      t.setAdultPrice(trip.getAdultPrice());
      t.setChildPrice(trip.getChildPrice());
      t.setNumberOfAdultBeds(trip.getNumberOfAdultBeds());
      t.setNumberOfChildBeds(trip.getNumberOfChildBeds());
      t.setPromoted(trip.getPromoted());
      t.setBoardBasisType(bbt);
      t.setFromCity(fc);
      t.setFromAirport(fa);
      t.setToCity(tc);
      t.setToAirport(ta);
      t.setToHotel(h);
      tripRepository.save(t);
      return ResponseEntity.noContent().build();
   }

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler(ControllerNotFoundException.class)
   public ControllerError handleSpecificControllerException(final ControllerNotFoundException exception) {
      return new ControllerError(exception.getMessage());
   }

   @ResponseStatus(HttpStatus.CONFLICT)
   @ExceptionHandler(ControllerConflictException.class)
   public ControllerError handleSpecificControllerException(final ControllerConflictException exception) {
      return new ControllerError(exception.getMessage());
   }

   private boolean validateDates(Trip trip) {
      return DAYS.between(trip.getDepartureDate(), trip.getReturnDate()) > 0;
   }
}
