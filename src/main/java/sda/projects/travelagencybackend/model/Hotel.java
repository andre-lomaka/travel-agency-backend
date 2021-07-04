package sda.projects.travelagencybackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hotel {
   @Id
   @GeneratedValue
   private Long id;

   @Column(nullable=false)
   private Integer numberOfStars;

   @Column(nullable=false)
   private String name;

   @Column(nullable=false)
   private String description;

   @ManyToOne
   @JoinColumn
   private City city;
}
