package sda.projects.travelagencybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.projects.travelagencybackend.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
