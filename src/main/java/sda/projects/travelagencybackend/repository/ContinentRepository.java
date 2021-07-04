package sda.projects.travelagencybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.projects.travelagencybackend.model.Continent;

public interface ContinentRepository extends JpaRepository<Continent, Long> {
}
