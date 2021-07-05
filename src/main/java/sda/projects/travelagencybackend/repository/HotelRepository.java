package sda.projects.travelagencybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.projects.travelagencybackend.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
