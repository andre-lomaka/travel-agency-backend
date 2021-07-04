package sda.projects.travelagencybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.projects.travelagencybackend.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
