package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sda.projects.travelagencybackend.model.Continent;
import sda.projects.travelagencybackend.repository.ContinentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/continents")
public class ContinentController {
   private final ContinentRepository continentRepository;

   @Autowired
   public ContinentController(ContinentRepository continentRepository) {
      this.continentRepository = continentRepository;
   }

   @GetMapping
   public List<Continent> getAllContinents() {
      return continentRepository.findAll();
   }

   @GetMapping("/{id}")
   public Continent getContinentById(@PathVariable(name="id") final Long id) {
      return continentRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Continent not found"));
   }

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler(ControllerNotFoundException.class)
   public ControllerError handleSpecificControllerException(final ControllerNotFoundException exception) {
      return new ControllerError(exception.getMessage());
   }
}
