package sda.projects.travelagencybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sda.projects.travelagencybackend.model.Trip;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByDepartureDateGreaterThan(LocalDate localDate);
    List<Trip> findAllByPromotedAndDepartureDateGreaterThan(boolean promoted, LocalDate localDate);

    @Query(value = "SELECT * FROM trip WHERE to_city_id IN (SELECT id FROM city WHERE country_id IN (SELECT id FROM country WHERE continent_id = :id)) AND departure_date > :date", nativeQuery = true)
    List<Trip> findAllByContinent(@Param("id") Long id, @Param("date") LocalDate date);

    @Query(value = "SELECT * FROM trip WHERE to_city_id IN (SELECT id FROM city WHERE country_id = :id) AND departure_date > :date", nativeQuery = true)
    List<Trip> findAllByCountry(@Param("id") Long id, @Param("date") LocalDate date);

    @Query(value = "SELECT * FROM trip WHERE from_city_id = :id AND departure_date > :date", nativeQuery = true)
    List<Trip> findAllByFromCity(@Param("id") Long id, @Param("date") LocalDate date);

    @Query(value = "SELECT * FROM trip WHERE to_city_id = :id AND departure_date > :date", nativeQuery = true)
    List<Trip> findAllByToCity(@Param("id") Long id, @Param("date") LocalDate date);

//    @Query(value = "SELECT * FROM trip WHERE board_basis_type_id = :id AND departure_date > :date", nativeQuery = true)
    @Query(value = "SELECT t FROM Trip t WHERE t.boardBasisType.id = :id AND t.departureDate > :date")
    List<Trip> findAllByBbt(@Param("id") Long id, @Param("date") LocalDate date);
};
