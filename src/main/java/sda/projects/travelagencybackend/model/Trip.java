package sda.projects.travelagencybackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trip {
   @Id
   @GeneratedValue
   private Long id;

   @Column(nullable=false)
   private LocalDate departureDate;

   @Column(nullable=false)
   private LocalDate returnDate;

   @Column(nullable=false)
   private BigDecimal adultPrice;

   @Column(nullable=false)
   private BigDecimal childPrice;

   @Column(nullable=false)
   private boolean promoted;

   @Column(nullable=false)
   private boolean available;

   @Column(nullable=false)
   private Integer numberOfAdultBeds;

   @Column(nullable=false)
   private Integer numberOfChildBeds;

   @ManyToOne
   @JoinColumn
   private BoardBasisTypes boardBasisType;

   @ManyToOne
   @JoinColumn
   private City fromCity;

   @ManyToOne
   @JoinColumn
   private Airport fromAirport;

   @ManyToOne
   @JoinColumn
   private City toCity;

   @ManyToOne
   @JoinColumn
   private Airport toAirport;

   @ManyToOne
   @JoinColumn
   private Hotel toHotel;
}
