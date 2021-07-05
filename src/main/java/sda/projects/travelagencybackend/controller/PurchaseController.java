package sda.projects.travelagencybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.projects.travelagencybackend.model.Purchase;
import sda.projects.travelagencybackend.repository.PurchaseRepository;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
   private final PurchaseRepository purchaseRepository;

   @Autowired
   public PurchaseController(PurchaseRepository purchaseRepository) {
      this.purchaseRepository = purchaseRepository;
   }

   @GetMapping
   public List<Purchase> getAllPurchases() {
      return purchaseRepository.findAll();
   }
}
