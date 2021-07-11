package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.projects.travelagencybackend.model.Purchase;
import sda.projects.travelagencybackend.model.Trip;
import sda.projects.travelagencybackend.repository.PurchaseRepository;
import sda.projects.travelagencybackend.repository.TripRepository;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "http://localhost:8080")
public class PurchaseController {
   private final PurchaseRepository purchaseRepository;
   private final TripRepository tripRepository;

   @Autowired
   public PurchaseController(PurchaseRepository purchaseRepository, TripRepository tripRepository) {
      this.purchaseRepository = purchaseRepository;
      this.tripRepository = tripRepository;
   }

   @GetMapping
   public List<Purchase> getAllPurchases() {
      return purchaseRepository.findAll();
   }

   @GetMapping("/trips")
   public List<Trip> getPurchasedTrips() {
      return purchaseRepository.findTrips();
   }

   @PostMapping
   public ResponseEntity<Purchase> createPurchase(@RequestBody final Purchase purchase) {
      Trip trip = tripRepository.findById(purchase.getTrip().getId()).orElseThrow(() -> new ControllerNotFoundException("Trip not found"));
      if (!validatePersons(purchase, trip)) throw new ControllerConflictException("Invalid number of persons");
      if (!validateAvailability(trip)) throw new ControllerConflictException("Trip not available");
      trip.setVacancies(trip.getVacancies()-1);
      tripRepository.save(trip);
      purchase.setId(null);
      purchase.setCreatedAt(LocalDateTime.now());
      purchase.setTrip(trip);
      purchase.setPrice(trip.getAdultPrice().multiply(BigDecimal.valueOf(purchase.getNumberOfAdults())).
              add(trip.getChildPrice().multiply(BigDecimal.valueOf(purchase.getNumberOfChildren()))));
      Purchase p = purchaseRepository.save(purchase);
      return ResponseEntity.created(URI.create(String.format("/api/purchases/%s", p.getId()))).body(p);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Purchase> updatePurchase(@RequestBody final Purchase purchase, @PathVariable(name="id") final Long id) {
      Purchase p = purchaseRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Purchase not found"));
      if (!p.getTrip().getId().equals(purchase.getTrip().getId())) throw new ControllerConflictException("Trip cannot be changed");
      Trip trip = tripRepository.findById(purchase.getTrip().getId()).get();
      p.setNumberOfAdults(purchase.getNumberOfAdults());
      p.setNumberOfChildren(purchase.getNumberOfChildren());
      if (!validatePersons(p, trip)) throw new ControllerConflictException("Invalid number of persons");
      p.setPrice(trip.getAdultPrice().multiply(BigDecimal.valueOf(purchase.getNumberOfAdults())).
              add(trip.getChildPrice().multiply(BigDecimal.valueOf(purchase.getNumberOfChildren()))));
      purchaseRepository.save(p);
      return ResponseEntity.noContent().build();
   }

   @GetMapping("/{id}")
   public Purchase getPurchaseById(@PathVariable(name="id") final Long id) {
      return purchaseRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Purchase not found"));
   }

   @DeleteMapping("/{id}")
   public void deletePurchase(@PathVariable(name="id") final Long id) {
      try {
         purchaseRepository.deleteById(id);
      } catch (Exception e) {
         throw new ControllerConflictException(e.getMessage());
      }
   }

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler(ControllerNotFoundException.class)
   public ControllerError handleNotFoundException(final ControllerNotFoundException exception) {
      return new ControllerError(exception.getMessage());
   }

   @ResponseStatus(HttpStatus.CONFLICT)
   @ExceptionHandler(ControllerConflictException.class)
   public ControllerError handleConflictException(final ControllerConflictException exception) {
      return new ControllerError(exception.getMessage());
   }

   private boolean validatePersons(Purchase purchase, Trip trip) {
       return purchase.getNumberOfAdults() <= trip.getNumberOfAdultBeds() &&
               purchase.getNumberOfChildren() <= trip.getNumberOfChildBeds();
   }

   private boolean validateAvailability(Trip trip) {
       return trip.getVacancies() > 0;
   }
}
