package sda.projects.travelagencybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sda.projects.travelagencybackend.model.Purchase;
import sda.projects.travelagencybackend.model.Trip;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query(value = "SELECT DISTINCT p.trip FROM Purchase p")
    List<Trip> findTrips();
}
