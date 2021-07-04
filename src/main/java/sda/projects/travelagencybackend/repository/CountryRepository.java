package sda.projects.travelagencybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.projects.travelagencybackend.model.Country;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);
}
