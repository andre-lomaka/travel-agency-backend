package sda.projects.travelagencybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.projects.travelagencybackend.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
