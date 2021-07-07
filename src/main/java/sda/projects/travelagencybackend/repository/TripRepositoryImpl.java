package sda.projects.travelagencybackend.repository;

import sda.projects.travelagencybackend.model.Trip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TripRepositoryImpl {

   @PersistenceContext
   private EntityManager entityManager;

   public List<Trip> findAllByConditions(HashMap<String, Object> conditions) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<Trip> query = cb.createQuery(Trip.class);
      Root<Trip> root = query.from(Trip.class);
      List<Predicate> predicates = new ArrayList<>();
      conditions.forEach((field, value) -> {
         switch (field) {
            case "fromCity":
            case "toCity":
            case "boardBasisType":
               predicates.add(cb.equal(root.get(field).get("id"), (Long) value));
               break;
            case "toHotel":
               predicates.add(cb.equal(root.get(field).get("numberOfStars"), (Integer) value));
               break;
         }
      });
      predicates.add(cb.greaterThan(root.get("departureDate"), cb.currentDate()));
      query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
      return entityManager.createQuery(query).getResultList();
   }
}
