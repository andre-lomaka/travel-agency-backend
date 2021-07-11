package sda.projects.travelagencybackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Trip {
   @Id
   @GeneratedValue
   private Long id;

   @Column(nullable=false)
   @JsonFormat(pattern="yyyy-MM-dd")
   private LocalDate departureDate;

   @Column(nullable=false)
   @JsonFormat(pattern="yyyy-MM-dd")
   private LocalDate returnDate;

   @Column(nullable=false)
   private BigDecimal adultPrice;

   @Column(nullable=false)
   private BigDecimal childPrice;

   @Column(nullable=false)
   private boolean promoted;

   @Column(nullable=false)
   private Integer numberOfAdultBeds;

   @Column(nullable=false)
   private Integer numberOfChildBeds;

   @Column(nullable=false)
   private Integer vacancies;

   @ManyToOne
   @JoinColumn
   private BoardBasisType boardBasisType;

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
