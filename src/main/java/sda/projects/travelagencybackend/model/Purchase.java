package sda.projects.travelagencybackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Purchase {
   @Id
   @GeneratedValue
   private Long id;

   @Column(nullable=false)
   private int numberOfAdults;

   @Column(nullable=false)
   private int numberOfChildren;

   @Column(nullable=false)
   private BigDecimal price;

   @ManyToOne
   @JoinColumn
   private Trip trip;
}
