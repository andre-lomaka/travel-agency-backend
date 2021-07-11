package sda.projects.travelagencybackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

   @Column(nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private LocalDateTime createdAt;

   @ManyToOne
   @JoinColumn
   private Trip trip;
}
