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

    @Query(value = "SELECT t FROM Trip t WHERE t.toCity.id IN (SELECT ci.id FROM City ci WHERE ci.country.id IN (SELECT co.id FROM Country co WHERE co.continent.id = :id)) AND t.departureDate > :date")
    List<Trip> findAllByContinent(@Param("id") Long id, @Param("date") LocalDate date);

    @Query(value = "SELECT t FROM Trip t WHERE t.toCity.id IN (SELECT ci.id FROM City ci WHERE ci.country.id = :id) AND t.departureDate > :date")
    List<Trip> findAllByCountry(@Param("id") Long id, @Param("date") LocalDate date);

    @Query(value = "SELECT t FROM Trip t WHERE t.fromCity.id = :id AND t.departureDate > :date")
    List<Trip> findAllByFromCity(@Param("id") Long id, @Param("date") LocalDate date);

    @Query(value = "SELECT t FROM Trip t WHERE t.toCity.id = :id AND t.departureDate > :date")
    List<Trip> findAllByToCity(@Param("id") Long id, @Param("date") LocalDate date);

    @Query(value = "SELECT t FROM Trip t WHERE t.boardBasisType.id = :id AND t.departureDate > :date")
    List<Trip> findAllByBbt(@Param("id") Long id, @Param("date") LocalDate date);
};
