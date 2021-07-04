package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
