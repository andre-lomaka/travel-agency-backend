package sda.projects.travelagencybackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Airport {
   @Id
   @GeneratedValue
   private Long id;

   @Column(nullable=false)
   private String name;

   @ManyToOne
   @JoinColumn
   private City city;
}
