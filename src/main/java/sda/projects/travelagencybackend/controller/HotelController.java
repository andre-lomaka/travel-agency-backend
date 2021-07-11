package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.projects.travelagencybackend.model.Hotel;
import sda.projects.travelagencybackend.repository.HotelRepository;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "http://localhost:8080")
public class HotelController {
   private final HotelRepository hotelRepository;

   @Autowired
   public HotelController(HotelRepository hotelRepository) {
      this.hotelRepository = hotelRepository;
   }

   @GetMapping
   public List<Hotel> getAllHotels() {
      return hotelRepository.findAll();
   }
}
