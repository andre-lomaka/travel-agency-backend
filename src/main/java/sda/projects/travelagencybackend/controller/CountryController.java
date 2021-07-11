package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sda.projects.travelagencybackend.model.Country;
import sda.projects.travelagencybackend.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "http://localhost:8080")
public class CountryController {
   private final CountryRepository countryRepository;

   @Autowired
   public CountryController(CountryRepository countryRepository) {
      this.countryRepository = countryRepository;
   }

   @GetMapping
   public List<Country> getAllCountries() {
      return countryRepository.findAll();
   }

   @ResponseStatus(HttpStatus.CREATED)
   @PostMapping
   public void createCountry(@RequestBody final Country country) {
      Optional<Country> c = countryRepository.findByName(country.getName());
      if (c.isPresent()) throw new ControllerConflictException("Country already exists");
      country.setId(null);
      countryRepository.save(country);
   }

   @ResponseStatus(HttpStatus.CONFLICT)
   @ExceptionHandler(ControllerConflictException.class)
   public ControllerError handleSpecificControllerException(final ControllerConflictException exception) {
      return new ControllerError(exception.getMessage());
   }
}
