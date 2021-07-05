package sda.projects.travelagencybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.projects.travelagencybackend.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
