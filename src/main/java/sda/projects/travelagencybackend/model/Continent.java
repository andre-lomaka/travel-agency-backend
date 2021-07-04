package sda.projects.travelagencybackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Continent {
   @Id
   @GeneratedValue
   private Long id;

   @Column(nullable=false, unique=true)
   private String name;
}
